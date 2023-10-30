package com.neuralnet.maisfinancas.ui.screens.estatisticas

import java.math.BigDecimal
import java.util.Calendar

data class EstatistacaUiState(
    val periodo: Calendar = Calendar.getInstance(),
    val dados: List<FinancasChartItem> = emptyList()
)
