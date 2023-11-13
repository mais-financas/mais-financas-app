package com.neuralnet.maisfinancas.ui.screens.setup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.ui.components.setup.DespesasPorCategoria
import com.neuralnet.maisfinancas.ui.components.setup.InserirDespesaDialog
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import kotlin.reflect.KFunction0

@Composable
fun SetupScreen(viewModel: SetupViewModel, onConfirmClick: () -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val selectedItem = viewModel.selectedItem.collectAsStateWithLifecycle()

    SetupScreen(
        uiState = uiState.value,
        onSelecionarItem = viewModel::selecionarItem,
        selectedItem = selectedItem.value,
        onAddDespesa = viewModel::adicionarDespesa,
        onConfirmClick = onConfirmClick,
        onResetSelection = viewModel::resetSelection,
        onRemoverItem = viewModel::removerItem,
        isItemValid = viewModel::isItemValid
    )
}

@Composable
fun SetupScreen(
    uiState: SetupUiState,
    onSelecionarItem: (Categoria, ItemDespesa) -> Unit,
    selectedItem: ItemDespesa?,
    isItemValid: (ItemDespesa) -> Boolean,
    onRemoverItem: (ItemDespesa) -> Unit,
    onAddDespesa: (ItemDespesa) -> Unit,
    onConfirmClick: () -> Unit,
    onResetSelection: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = R.string.configure_sua_conta),
                canNavigateBack = false
            )
        },
    ) { paddingValues ->

        if (selectedItem != null) {
            InserirDespesaDialog(
                item = selectedItem,
                onConfirmClick = { itemDespesa ->
                    if (isItemValid(itemDespesa)) {
                        onAddDespesa(itemDespesa)
                    }
                },
                onDismiss = onResetSelection,
                onRemoverItem = onRemoverItem,
            )
        }

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.selecione_despesas),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.7f)
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                )
            }

            items(uiState.despesas.keys.toList()) { categoria ->
                DespesasPorCategoria(
                    categoria = categoria,
                    despesas = uiState.despesas[categoria].orEmpty(),
                    onCardClick = onSelecionarItem,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                Button(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.confirmar))
                }
            }
        }
    }
}

@Preview
@Composable
fun SetupScreenPreview() {
    MaisFinancasTheme {
        SetupScreen(
            uiState = SetupUiState(),
            onSelecionarItem = { _, _ -> },
            selectedItem = null,
            onRemoverItem = { },
            onAddDespesa = { },
            onConfirmClick = { },
            isItemValid = { true },
        )
    }
}
