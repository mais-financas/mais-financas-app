package com.neuralnet.maisfinancas.ui.components.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

        Row(
            modifier = Modifier.padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.saldo_da_conta),
                    style = MaterialTheme.typography.headlineSmall,
                )

                Text(
                    text = BigDecimal.valueOf(valorAnim.toDouble()).toReal(),
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Surface(
                shape = MaterialTheme.shapes.medium,
                onClick = onAddClick,
                modifier = Modifier.size(40.dp),
                color = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add),
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(8.dp)
                )
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
