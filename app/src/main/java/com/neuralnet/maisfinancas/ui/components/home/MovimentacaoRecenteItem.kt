package com.neuralnet.maisfinancas.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.formattedDate
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal
import java.util.Calendar

@Composable
fun MovimentacaoRecenteItem(item: MovimentacaoItem, modifier: Modifier = Modifier) {
    val icon: ImageVector = if (item.isIncome) {
        Icons.Default.CallMade
    } else {
        Icons.Default.CallReceived
    }

    val color = if (item.isIncome) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Red
    }

    val symbol = if (item.isIncome) {
        stringResource(id = R.string.income_symbol)
    } else {
        stringResource(id = R.string.expense_symbol)
    }

    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.large,
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    tint = color,
                    contentDescription = null
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.descricao, fontWeight = FontWeight.Medium)

                Text(text = item.data.formattedDate())
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = color)) {
                        append(symbol)
                        append(item.valor.toReal())
                    }
                },
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovimentacaoRendaPreview() {
    MaisFinancasTheme {
        MovimentacaoRecenteItem(
            MovimentacaoItem(
                descricao = "Salário",
                valor = BigDecimal.valueOf(1300),
                data = Calendar.getInstance(),
                isIncome = true
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovimentacaoDespesaPreview() {
    MaisFinancasTheme {
        MovimentacaoRecenteItem(
            MovimentacaoItem(
                descricao = "Água",
                valor = BigDecimal.TEN,
                data = Calendar.getInstance(),
                isIncome = false
            )
        )
    }
}
