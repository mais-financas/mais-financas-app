package com.neuralnet.maisfinancas.ui.screens.home

import java.math.BigDecimal

data class HomeUiState(
    val saldoTotal: BigDecimal = BigDecimal.ZERO,
    val saldoMensal: BigDecimal = BigDecimal.ZERO,
    val movimentacoes: List<MovimentacaoItem> = emptyList()
)
