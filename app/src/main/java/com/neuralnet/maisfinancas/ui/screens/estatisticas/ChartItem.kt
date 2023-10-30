package com.neuralnet.maisfinancas.ui.screens.estatisticas

import androidx.compose.ui.graphics.Color
import com.neuralnet.maisfinancas.data.room.model.estatistica.ValorTotalPorCategoria
import java.math.BigDecimal

data class FinancasChartItem(
    val description: String,
    val value: BigDecimal,
    val color: Color = defaultColor(description),
) {
    companion object {
        private fun defaultColor(description: String): Color {
            val hash = description.hashCode()
            val red = (hash and 0xFF0000) shr 16
            val green = (hash and 0x00FF00) shr 8
            val blue = hash and 0x0000FF
            return Color(red, green, blue)
        }
    }
}

fun List<ValorTotalPorCategoria>.toModel() = map { valorTotalPorCategoria ->
    FinancasChartItem(
        description = valorTotalPorCategoria.categoria,
        value = valorTotalPorCategoria.valorTotal,
    )
}
