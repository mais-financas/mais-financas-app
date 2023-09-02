package com.neuralnet.maisfinancas.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.data.model.Despesa
import com.neuralnet.maisfinancas.ui.components.ItemDespesa
import com.neuralnet.maisfinancas.ui.components.toReal

val despesas = mutableListOf(
    Despesa("Água", "Essenciais", 100.0, "Mensal", 1693577802000),
    Despesa("Energia", "Essenciais", 123.0, "Mensal", 1693577802000),
    Despesa("Almoço", "Alimentação", 30.0, "Diária", 1693064202000),
    Despesa("Cinema", "Entretenimento", 70.0, "Nenhuma", 1693564202000),
    Despesa("Jantar Restaurante", "Alimentação", 40.0, "Nenhuma", 1693064202000),
)

@Composable
fun DespesasScreen() {
    val despesasState = remember {
        mutableStateListOf(*(despesas.toTypedArray()))
    }

    Scaffold { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Divider(modifier = Modifier.fillMaxWidth())
                Text(
                    stringResource(R.string.despesas),
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 28.sp),
                    modifier = Modifier.padding(start = 12.dp, top = 16.dp, bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            for (grupos in despesasState.groupBy { it.categoria }) {
                item(grupos.key) {
                    Row {
                        Text(
                            text = grupos.key,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 16.dp).weight(1f)
                        )
                        Text(
                            text = "(${valorPorCategoria(grupos)})",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
                items(grupos.value, key = { it.nome }) { despesa ->
                    ItemDespesa(
                        despesa = despesa,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

private fun valorPorCategoria(despesas: Map.Entry<String, List<Despesa>>): String {
    return despesas.value
        .filter { despesa ->  despesa.categoria == despesas.key }
        .sumOf { it.valor }
        .toReal()
}
