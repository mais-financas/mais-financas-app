package com.neuralnet.maisfinancas.ui.screens.depesas

import com.neuralnet.maisfinancas.data.model.Despesa

data class DespesasUiState(
    val despesas: List<Despesa> = listOf()
)
