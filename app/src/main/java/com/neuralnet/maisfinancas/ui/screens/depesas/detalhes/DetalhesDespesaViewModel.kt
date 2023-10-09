package com.neuralnet.maisfinancas.ui.screens.depesas.detalhes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetalhesDespesaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val despesaRepository: DespesaRepository,
) : ViewModel() {

    private val despesaId: Long = checkNotNull(savedStateHandle["despesa_id"])
    private val gestorId: UUID = UUID.fromString("00a7b810-9dad-11d1-80b4-00c04fd430c8")

    val uiState: StateFlow<DetalhesDespesaUiState> = despesaRepository
        .getDespesasAndRegistros(gestorId, despesaId)
        .map(Despesa::toDetalhesUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DetalhesDespesaUiState()
        )

    private val _registroUiState = MutableStateFlow(RegistroUiState())
    val registroUiState: StateFlow<RegistroUiState> = _registroUiState.asStateFlow()

    fun setDefinirLembrete(definirLembrete: Boolean) = viewModelScope.launch {
        val despesa: Despesa = uiState.value.copy(definirLembrete = definirLembrete).toModel()
        val categoriaId = despesaRepository.findCategoriaIdByNome(uiState.value.categoria)

        despesaRepository.updateDespesa(
            despesa = despesa,
            gestorId = gestorId,
            categoriaId = categoriaId
        )
    }

    fun updateRegistroState(registroUiState: RegistroUiState) {
        _registroUiState.update { registroUiState }
    }

    fun adicionarRegistro(dataEmEpochMillis: Long?) = viewModelScope.launch {
        val data: Calendar = Calendar.getInstance()
            .apply { timeInMillis = dataEmEpochMillis ?: Instant.now().toEpochMilli() }

        val registroDespesa = RegistroDespesaEntity(
            valor = registroUiState.value.valor.toBigDecimal(),
            data = data,
            despesaId = uiState.value.despesaId
        )

        despesaRepository.inserirRegistro(registroDespesa)
        clearRegistro()
    }

    fun isRegistroValid(): Boolean {
        if (registroUiState.value.valor.toBigDecimalOrNull() == null) {
            _registroUiState.update {
                it.copy(valorErrorField = FieldValidationError.NUMERO_INVALIDO)
            }
        }
        return registroUiState.value.isRegistroValid()
    }

    private fun clearRegistro() {
        _registroUiState.update { it.copy(valor = "") }
    }
}
