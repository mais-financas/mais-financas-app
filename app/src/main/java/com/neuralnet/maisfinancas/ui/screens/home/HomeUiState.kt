package com.neuralnet.maisfinancas.ui.screens.home

import java.math.BigDecimal

data class HomeUiState(
    val saldoMensal: BigDecimal = BigDecimal.ZERO,
    val movimentacoes: List<MovimentacaoItem> = emptyList()
)
