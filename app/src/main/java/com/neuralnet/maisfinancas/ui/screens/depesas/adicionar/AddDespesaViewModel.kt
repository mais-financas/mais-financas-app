package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddDespesaViewModel @Inject constructor(
    private val despesaRepository: DespesaRepository,
) : ViewModel() {

    private val gestorId: UUID = UUID.fromString("00a7b810-9dad-11d1-80b4-00c04fd430c8")

    val categorias: StateFlow<List<CategoriaEntity>> = despesaRepository.getCategorias()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    private val _uiState = MutableStateFlow(AddDespesaUiState())
    val uiState: StateFlow<AddDespesaUiState> = _uiState.asStateFlow()

    fun updateUiState(uiState: AddDespesaUiState) {
        _uiState.update { uiState }
    }

    fun salvarDespesa(dataEmEpochMillis: Long?) = viewModelScope.launch {
        val selectedDateInMillis = dataEmEpochMillis ?: Instant.now().toEpochMilli()
        val despesa = uiState.value.toDespesaModel(selectedDateInMillis)
        val categoria = categorias.value.find { it.nome == uiState.value.categoria }
            ?: categorias.value.first()

        despesaRepository.salvarDespesa(despesa, gestorId, categoria.id)
    }
}