package com.neuralnet.maisfinancas.ui.components.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal

@Composable
fun SaldoTotal(
    saldoTotal: BigDecimal,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        val valorAnim by animateValueAsState(
            targetValue = saldoTotal.toFloat(),
            typeConverter = Float.VectorConverter,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
            label = "Animação do saldo total disponível"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(id = R.string.saldo_da_conta),
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = BigDecimal.valueOf(valorAnim.toDouble()).toReal(),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            TextButton(onClick = onAddClick) {
                Text(text = stringResource(id = R.string.adicionar_renda))
            }
        }
    }
}

@Preview
@Composable
private fun InformacoesContaPreview() {
    MaisFinancasTheme {
        SaldoTotal(
            saldoTotal = BigDecimal.TEN,
            onAddClick = {}
        )
    }
}
