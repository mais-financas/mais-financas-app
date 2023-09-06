package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.model.Despesa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant

class AddDespesaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddDespesaUiState())
    val uiState: StateFlow<AddDespesaUiState> = _uiState.asStateFlow()

    fun updateUiState(uiState: AddDespesaUiState) {
        _uiState.value = uiState
    }

    fun salvarDespesa(dataEmEpochMillis: Long?) = viewModelScope.launch {
        uiState.value.let { despesa ->

            val despesaCompleta = Despesa(
                nome = despesa.nome,
                categoria = despesa.categoria,
                valor = despesa.valor.toDouble(),
                recorrencia = despesa.recorrencia,
                dataEmMillis = dataEmEpochMillis ?: Instant.now().toEpochMilli()
            )
        }
    }
}