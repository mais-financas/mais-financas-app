package com.neuralnet.maisfinancas.ui.screens.home

import java.math.BigDecimal

data class HomeUiState(
    val gastoMensal: BigDecimal = BigDecimal.ZERO,
    val saldoMensal: BigDecimal = BigDecimal.ZERO,
    val rendaSemanal: BigDecimal = BigDecimal.ZERO,
    val despesasSemanais: BigDecimal = BigDecimal.ZERO,
)
