package com.neuralnet.maisfinancas.ui.components.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.displayMonth
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal
import java.util.Calendar

@Composable
fun SaldoDisponivel(
    valor: BigDecimal,
    calendar: Calendar,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(all = 16.dp),
        ) {
            val valorAnim by animateValueAsState(
                targetValue = valor.toFloat(),
                typeConverter = Float.VectorConverter,
                animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
                label = "Animação do saldo disponível"
            )

            Text(
                text = stringResource(
                    id = R.string.saldo_mensal_disponivel,
                    calendar.displayMonth(),
                ),
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = BigDecimal.valueOf(valorAnim.toDouble()).toReal(),
                style = MaterialTheme.typography.headlineSmall,
            )
            /* Funcionalidade prorrogada


            TextButton(onClick = onClick) {
                Text(text = stringResource(id = R.string.ver_detalhes))
            } */
        }
    }
}

@Preview
@Composable
private fun SaldoMensalDisponivelPreview() {
    MaisFinancasTheme {
        SaldoDisponivel(
            valor = BigDecimal.TEN,
            calendar = Calendar.getInstance(),
            onClick = {}
        )
    }
}
