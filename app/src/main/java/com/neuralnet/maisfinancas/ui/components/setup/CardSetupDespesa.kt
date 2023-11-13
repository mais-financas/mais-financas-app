package com.neuralnet.maisfinancas.ui.components.setup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetupDespesa(
    despesa: ItemDespesa,
    onCardClick: (ItemDespesa) -> Unit,
    modifier: Modifier = Modifier,
) {
    val color by animateColorAsState(
        targetValue = if (despesa.selecionado) {
            MaterialTheme.colorScheme.primary
        } else
            LocalContentColor.current,
        label = "Animate selection"
    )

    Card(
        modifier = modifier,
        onClick = { onCardClick(despesa) },
        border = if (despesa.selecionado) {
            BorderStroke(2.dp, color)
        } else null
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),

            ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .border(
                        width = 1.dp,
                        color = color,
                        shape = CircleShape
                    )
            ) {

                Icon(
                    imageVector = despesa.icone,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = color
                )
            }

            Text(
                text = despesa.nome,
                color = color,
                fontWeight = if (despesa.selecionado) FontWeight.Medium else null,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun CardSetupDespesaTransportePreview() {
    MaisFinancasTheme {
        CardSetupDespesa(
            despesa = ItemDespesa(
                icone = Icons.Outlined.DirectionsBus,
                nome = "Transporte Público"
            ),
            onCardClick = {}
        )
    }
}

@Preview
@Composable
private fun CardSetupDespesaPreview() {
    MaisFinancasTheme {
        CardSetupDespesa(
            despesa = ItemDespesa(
                icone = Icons.Outlined.PhoneAndroid,
                nome = "Celular"
            ),
            onCardClick = {}
        )
    }
}

@Preview
@Composable
private fun CardSetupDespesaSelecionadoPreview() {
    MaisFinancasTheme {
        CardSetupDespesa(
            despesa = ItemDespesa(
                icone = Icons.Outlined.PhoneAndroid,
                nome = "Celular",
                selecionado = true
            ),
            onCardClick = {},
        )
    }
}
