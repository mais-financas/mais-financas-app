package com.neuralnet.maisfinancas.ui.screens.depesas.detalhes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmScheduler
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.depesas.adicionar.dataProximoLembrete
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.formattedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.time.Instant
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DetalhesDespesaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val despesaRepository: DespesaRepository,
    private val lembreteAlarmScheduler: LembreteAlarmScheduler,
    gestorRepository: GestorRepository,
) : ViewModel() {

    private val despesaId: Long = checkNotNull(savedStateHandle["despesa_id"])
    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    val uiState: StateFlow<DetalhesDespesaUiState> = despesaRepository
        .getDespesasAndRegistros(despesaId)
        .map(Despesa::toDetalhesUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DetalhesDespesaUiState()
        )

    private val _registroUiState = MutableStateFlow(RegistroUiState())
    val registroUiState: StateFlow<RegistroUiState> = _registroUiState.asStateFlow()

    private var job: Job? = null

    fun setDefinirLembrete(definirLembrete: Boolean) {
        job?.cancel()

        job = viewModelScope.launch {
            delay(500)
            val despesa: Despesa = uiState.value.copy(definirLembrete = definirLembrete).toModel()
            val categoriaId = despesaRepository.findCategoriaIdByNome(uiState.value.categoria)
            val gestorId = checkNotNull(gestor.first()?.id)

            despesaRepository.updateDespesa(
                despesa = despesa,
                gestorId = gestorId,
                categoriaId = categoriaId
            )

            definirProximoLembrete(despesa)
        }
    }

    fun updateRegistroState(registroUiState: RegistroUiState) {
        _registroUiState.update { registroUiState }
    }

    fun adicionarRegistro(dataEmEpochMillis: Long?) = viewModelScope.launch {
        val data: Calendar = Calendar.getInstance()
            .apply { timeInMillis = dataEmEpochMillis ?: Instant.now().toEpochMilli() }

        val registroDespesa = RegistroDespesa(
            id = 0,
            valor = registroUiState.value.valor.toBigDecimal(),
            data = data,
        )

        val despesa = uiState.value.toModel()

        try {
            _connectionState.value = ConnectionState.Loading
            despesaRepository.inserirRegistro(despesaId, registroDespesa)
            clearRegistro()
            definirProximoLembrete(despesa)

            _connectionState.value = ConnectionState.Idle
        } catch (e: SocketTimeoutException) {
            _connectionState.value = ConnectionState.ServerUnavailable
        } catch (e: IOException) {
            _connectionState.value = ConnectionState.NoInternet
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.Error
        }
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
        _registroUiState.update { it.copy(valor = "", valorErrorField = null) }
    }

    private fun definirProximoLembrete(despesa: Despesa) {
        val dataUltimoLembrete = despesa.registros.maxBy { it.data.timeInMillis }.data
        val dataLembrete = dataProximoLembrete(
            selectedDateMillis = dataUltimoLembrete.timeInMillis,
            recorrencia = despesa.recorrencia
        )
        lembreteAlarmScheduler.definirAlarme(dataLembrete, despesa)
    }

}
