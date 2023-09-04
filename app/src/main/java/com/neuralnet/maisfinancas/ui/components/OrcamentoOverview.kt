package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Currency
import java.util.Locale

@Composable
fun OrcamentoOverview(
    gasto: Double,
    saldo: Double,
    modifier: Modifier = Modifier,
    data: LocalDate = LocalDate.now()
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        GastoMensal(gasto)

        SaldoDisponivel(saldo, data)
    }
}

@Composable
fun GastoMensal(
    gasto: Double,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.saldo_mensal_disponivel),
                fontWeight = FontWeight.Medium,
            )

            Text(
                text = gasto.toReal(),
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.ver_detalhes),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
fun SaldoDisponivel(
    saldo: Double,
    data: LocalDate,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.carteira_disponivel, data.mesAtual()),
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    text = stringResource(R.string.saldo_disponivel),
                    fontWeight = FontWeight.Light,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                )
            }

            Text(
                text = saldo.toReal(),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}


@Preview
@Composable
fun OrcamentoOverviewPreview() {
    MaisFinancasTheme {
        OrcamentoOverview(
            gasto = 235.4,
            saldo = 4389.0,
        )
    }
}

@Preview(widthDp = 350)
@Composable
fun SaldoDisponivelPreview() {
    MaisFinancasTheme {
        SaldoDisponivel(
            saldo = 123.12,
            data = LocalDate.now().plusMonths(2)
        )
    }
}

fun LocalDate.mesAtual(): String {
    return this.month
        .getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
        .replaceFirstChar { it.uppercase() }
}

fun Double.toReal(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance(Locale("pt", "BR"))

    return format.format(this)
}
