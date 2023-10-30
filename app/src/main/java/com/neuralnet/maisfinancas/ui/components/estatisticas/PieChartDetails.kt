package com.neuralnet.maisfinancas.ui.components.estatisticas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.ui.screens.estatisticas.FinancasChartItem
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal

@Composable
fun PieChartDetails(item: FinancasChartItem, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(modifier = Modifier
            .size(48.dp)
            .background(color = item.color, shape = MaterialTheme.shapes.medium)
        )

        Column {
            Text(text = item.description, fontWeight = FontWeight.Medium)

            Text(text = item.value.toReal())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartDetailsPreview() {
    MaisFinancasTheme {
        PieChartDetails(
            FinancasChartItem("Saúde", BigDecimal.valueOf(300L)),
        )
    }
}

