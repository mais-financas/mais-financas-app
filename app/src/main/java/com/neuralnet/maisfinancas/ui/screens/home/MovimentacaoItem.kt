package com.neuralnet.maisfinancas.ui.screens.home

import java.math.BigDecimal
import java.util.Calendar

data class MovimentacaoItem(
    val descricao: String,
    val valor: BigDecimal,
    val data: Calendar,
    val isIncome: Boolean,
)
