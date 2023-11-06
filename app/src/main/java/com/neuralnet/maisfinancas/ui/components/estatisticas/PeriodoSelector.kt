package com.neuralnet.maisfinancas.ui.components.estatisticas

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.formattedMonth
import java.util.Calendar

@Composable
fun PeriodoSelector(
    data: Calendar,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.gestao_de_gastos),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(.35f),
        )

        Row(modifier = Modifier.weight(.65f), verticalAlignment = Alignment.CenterVertically) {

            IconButton(
                onClick = onPreviousClick,
                modifier = Modifier.graphicsLayer(rotationZ = 180f)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = stringResource(R.string.mes_anterior),
                )
            }

            Text(
                text = data.formattedMonth(),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )

            IconButton(onClick = onNextClick) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = stringResource(R.string.proximo_mes),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PeriodoSelectorPreview() {
    MaisFinancasTheme {
        PeriodoSelector(
            data = Calendar.getInstance(),
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
