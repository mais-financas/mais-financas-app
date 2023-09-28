package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal

@Composable
fun TransferenciasSemana(
    rendaSemanal: BigDecimal,
    despesasSemanais: BigDecimal,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.transferencias_semanais),
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CardOrcamentoSemanal(
                descricao = stringResource(R.string.ganhos),
                valor = rendaSemanal,
                color = Color.Green,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            CardOrcamentoSemanal(
                descricao = stringResource(R.string.despesas),
                valor = despesasSemanais,
                color = Color.Red,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CardOrcamentoSemanal(
    descricao: String,
    valor: BigDecimal,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                .background(color, CircleShape)
                .size(36.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = valor.toReal(), fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(8.dp))
            
            Text(text = descricao, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun TransferenciasSemanaPreview() {
    MaisFinancasTheme {
        TransferenciasSemana(
            BigDecimal.valueOf(1237.2), BigDecimal.valueOf(292.5)
        )
    }
}
