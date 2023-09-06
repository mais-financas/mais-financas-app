package com.neuralnet.maisfinancas.ui.screens.depesas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.model.Despesa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class DespesaViewModel : ViewModel() {

    val uiState: StateFlow<DespesasUiState> = flowOf(
        DespesasUiState(
            despesas = mutableListOf(
                Despesa("Água", "Essenciais", 100.0, "Mensal", 1693577802000),
                Despesa("Energia", "Essenciais", 123.0, "Mensal", 1693577802000),
                Despesa("Almoço", "Alimentação", 30.0, "Diária", 1693064202000),
                Despesa("Cinema", "Entretenimento", 70.0, "Nenhuma", 1693564202000),
                Despesa("Jantar Restaurante", "Alimentação", 40.0, "Nenhuma", 1693064202000),
            )
        )
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = DespesasUiState()
    )

}