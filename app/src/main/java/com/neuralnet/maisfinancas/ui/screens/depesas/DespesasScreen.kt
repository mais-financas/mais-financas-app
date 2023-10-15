package com.neuralnet.maisfinancas.ui.screens.depesas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.ui.components.ItemDespesa
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal

@Composable
fun DespesasScreen(
    viewModel: DespesaViewModel,
    onDetailsClick: (Long) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    DespesasScreen(uiState = uiState.value, onDetailsClick = onDetailsClick)
}

@Composable
fun DespesasScreen(
    uiState: DespesasUiState,
    onDetailsClick: (Long) -> Unit,
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.DespesasGraph.title),
                canNavigateBack = false,
            )
        }
    ) { paddingValues ->
        if (uiState.despesas.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.lista_despesas_vazia))
            }
        } else {
            DespesasListContent(
                uiState = uiState,
                onDetailsClick = onDetailsClick,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun DespesasListContent(
    uiState: DespesasUiState,
    onDetailsClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {

        for (grupos in uiState.despesas.groupBy { it.categoria }) {
            item(key = grupos.key) {
                Row {
                    Text(
                        text = grupos.key,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    )
                }
            }
            items(items = grupos.value, key = { it.id }) { despesa ->
                ItemDespesa(
                    nome = despesa.nome,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    onClick = { onDetailsClick(despesa.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DespesasScreenPreview() {
    MaisFinancasTheme {
        DespesasScreen(
            uiState = DespesasUiState(
                listOf(
                    Despesa(
                        id = 0,
                        nome = "Água",
                        categoria = "Essenciais",
                        Recorrencia(Frequencia.MENSAL, 1),
                        definirLembrete = false,
                    ),
                    Despesa(
                        id = 0,
                        nome = "Energia",
                        categoria = "Essenciais",
                        Recorrencia(Frequencia.MENSAL, 1),
                        definirLembrete = true,
                    ),
                    Despesa(
                        id = 0,
                        nome = "Almoço",
                        categoria = "Alimentação",
                        Recorrencia(Frequencia.MENSAL, 1),
                        definirLembrete = true,
                    ),
                    Despesa(
                        id = 0,
                        nome = "Cinema",
                        categoria = "Entretenimento",
                        Recorrencia(Frequencia.MENSAL, 1),
                        definirLembrete = false,
                    ),
                )
            ),
            onDetailsClick = {}
        )
    }
}

private fun valorPorCategoria(despesas: Map.Entry<String, List<Despesa>>): String {
    return despesas.value
        .filter { despesa -> despesa.categoria == despesas.key }
        .sumOf { BigDecimal.ZERO }
        .toReal()
}
