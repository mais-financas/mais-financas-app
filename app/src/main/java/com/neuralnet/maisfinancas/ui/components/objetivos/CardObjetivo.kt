package com.neuralnet.maisfinancas.ui.components.objetivos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardObjetivo(objetivo: Objetivo, onCardClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Card(onClick = { onCardClick(objetivo.id) }, modifier = modifier.height(100.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = objetivo.descricao,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun CardObjetivoAposentadoriaPreview() {
    MaisFinancasTheme {
        CardObjetivo(
            objetivo = Objetivo(descricao = "Aposentadoria", valor = BigDecimal.ZERO),
            onCardClick = {}
        )
    }
}

@Preview
@Composable
private fun CardObjetivoCasaPreview() {
    MaisFinancasTheme {
        CardObjetivo(
            objetivo = Objetivo(descricao = "Casa", valor = BigDecimal.ZERO),
            onCardClick = {},
        )
    }
}
