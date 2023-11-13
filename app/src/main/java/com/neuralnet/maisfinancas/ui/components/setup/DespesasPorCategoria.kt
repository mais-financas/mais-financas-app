package com.neuralnet.maisfinancas.ui.components.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun DespesasPorCategoria(
    categoria: Categoria,
    despesas: List<ItemDespesa>,
    onCardClick: (Categoria, ItemDespesa) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = categoria.nome,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(despesas) { despesa ->
                CardSetupDespesa(
                    despesa = despesa,
                    onCardClick = { onCardClick(categoria, despesa) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DespesasPorCategoriaPreview() {
    MaisFinancasTheme {
        val (categoria, despesas) = SugestoesDatasource.defaultDespesas.entries.first()

        DespesasPorCategoria(
            categoria = categoria,
            despesas = despesas,
            onCardClick = { _, _ -> }
        )
    }
}
