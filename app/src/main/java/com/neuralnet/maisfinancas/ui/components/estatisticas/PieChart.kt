package com.neuralnet.maisfinancas.ui.components.estatisticas

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.ui.screens.estatisticas.FinancasChartItem
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import java.math.BigDecimal

@Composable
fun PieChart(
    dados: List<FinancasChartItem>,
    modifier: Modifier = Modifier,
    outerRadius: Dp = 90.dp,
    chartBarWidth: Dp = 35.dp,
    animationDuration: Int = 1000,
) {
    val totalSum = dados.sumOf { it.value }

    var hasAnimationPlayed by remember { mutableStateOf(false) }
    var startAngle = 0f
    val animateSize by animateFloatAsState(
        targetValue = if (hasAnimationPlayed) outerRadius.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        label = "Animate Size"
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (hasAnimationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        label = "Animate Rotation"
    )

    LaunchedEffect(key1 = Unit) { hasAnimationPlayed = true }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = modifier
                    .size(outerRadius * 2f)
                    .rotate(animateRotation),
            ) {
                dados.forEach { item ->
                    val floatValue = item.value.toFloat() * 360 / totalSum.toFloat()

                    drawArc(
                        color = item.color,
                        startAngle = startAngle,
                        sweepAngle = floatValue,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    startAngle += floatValue
                }
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
private fun PieChartPreview() {
    MaisFinancasTheme {
        PieChart(
            dados = listOf(
                FinancasChartItem("Essenciais", BigDecimal.valueOf(300)),
            ),
        )
    }
}
