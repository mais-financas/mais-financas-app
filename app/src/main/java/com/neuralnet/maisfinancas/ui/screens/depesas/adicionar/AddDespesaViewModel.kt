package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmScheduler
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Frequencia.ANUAL
import com.neuralnet.maisfinancas.model.despesa.Frequencia.DIARIA
import com.neuralnet.maisfinancas.model.despesa.Frequencia.MENSAL
import com.neuralnet.maisfinancas.model.despesa.Frequencia.SEMANAL
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddDespesaViewModel @Inject constructor(
    private val lembreteAlarmScheduler: LembreteAlarmScheduler,
    private val despesaRepository: DespesaRepository,
    gestorRepository: GestorRepository,
) : ViewModel() {

    private val gestorId: Flow<GestorEntity?> = gestorRepository.getGestor()

    val categorias: StateFlow<List<Categoria>> = despesaRepository.getCategorias()
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
        val categoria = categorias.value.find { it.nome == uiState.value.categoria }
            ?: categorias.value.first()

        val gestorId = checkNotNull(gestorId.first()?.id)

        val despesaInput = uiState.value.toDespesaInput(
            gestorId = gestorId,
            categoriaId = categoria.id,
            dataInMillis = selectedDateInMillis
        )

        val despesaId = despesaRepository.registrarDespesa(despesaInput)
        val despesa = despesaInput.despesa.copy(id = despesaId)

        if (despesa.definirLembrete) {
            val dataLembrete = definirProximoLembrete(selectedDateInMillis, despesa.recorrencia)
            lembreteAlarmScheduler.definirAlarme(dataLembrete, despesa)
        }
    }

    fun isFormValid(): Boolean = with(uiState.value) {
        if (nome.isBlank()) {
            _uiState.value = _uiState.value.copy(nomeErrorField = FieldValidationError.VAZIO)
        }
        if (valor.toBigDecimalOrNull() == null) {
            _uiState.value =
                _uiState.value.copy(valorErrorField = FieldValidationError.NUMERO_INVALIDO)
        }
        if (categoria.isBlank()) {
            _uiState.value = _uiState.value.copy(categoriaErrorField = FieldValidationError.VAZIO)
        }
        return _uiState.value.isFormValid()
    }
}

fun definirProximoLembrete(selectedDateMillis: Long, recorrencia: Recorrencia): Calendar {
    val dataAtual = System.currentTimeMillis()
    val calendarDataLembrete = Calendar.getInstance()
    calendarDataLembrete.timeInMillis = selectedDateMillis

    while (calendarDataLembrete.timeInMillis < dataAtual) {
        when (recorrencia.frequencia) {
            DIARIA -> calendarDataLembrete.add(Calendar.DATE, recorrencia.quantidade)
            SEMANAL -> calendarDataLembrete.add(Calendar.DATE, 7 * recorrencia.quantidade)
            MENSAL -> calendarDataLembrete.add(Calendar.MONTH, recorrencia.quantidade)
            ANUAL -> calendarDataLembrete.add(Calendar.YEAR, recorrencia.quantidade)
            else -> throw Exception("Impossível definir lembrete para ${Frequencia.NENHUMA}")
        }
    }

    return calendarDataLembrete
}
