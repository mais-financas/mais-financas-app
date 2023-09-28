package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun OrcamentoOverview(
    gasto: BigDecimal,
    saldo: BigDecimal,
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
    gasto: BigDecimal,
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
    saldo: BigDecimal,
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
            gasto = BigDecimal.valueOf(235.4),
            saldo = BigDecimal.valueOf(4389.0),
        )
    }
}

@Preview(widthDp = 350)
@Composable
fun SaldoDisponivelPreview() {
    MaisFinancasTheme {
        SaldoDisponivel(
            saldo = BigDecimal.valueOf(123.12),
            data = LocalDate.now().plusMonths(2)
        )
    }
}

fun LocalDate.mesAtual(): String {
    return this.month
        .getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
        .replaceFirstChar { it.uppercase() }
}

