package com.neuralnet.maisfinancas.ui.screens.setup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.ServidorIndisponivel
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import kotlinx.coroutines.launch

@Composable
fun SetupScreen(viewModel: SetupViewModel, navigateToHome: () -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val selectedItem = viewModel.selectedItem.collectAsStateWithLifecycle()

    val connectionState = viewModel.connectionState.collectAsStateWithLifecycle()
    val connectionMessage = connectionState.value.message?.let { stringResource(id = it) }

    when (connectionState.value) {
        is ConnectionState.Loading -> LoadingScreen()
        is ConnectionState.ServerUnavailable -> {
            ServidorIndisponivel {
                viewModel.inserirDespesas()
            }
        }

        is ConnectionState.Success -> {
            LaunchedEffect(key1 = Unit) {
                navigateToHome()
            }
        }

        else -> {
            SetupScreen(
                uiState = uiState.value,
                onSelecionarItem = viewModel::selecionarItem,
                selectedItem = selectedItem.value,
                onItemChange = viewModel::updateItem,
                onAdicionarItem = {
                    if (viewModel.isItemValid()) {
                        viewModel.adicionarDespesa(it)
                    }
                },
                onRemoverItem = viewModel::removerItem,
                onConfirmClick = {
                    viewModel.inserirDespesas()
                    viewModel.inserirObjetivos()
                },
                onResetSelection = viewModel::resetSelection,
                connectionMessage = connectionMessage,
            )
        }
    }
}

@Composable
fun SetupScreen(
    uiState: SetupUiState,
    onSelecionarItem: (Categoria, ItemDespesa) -> Unit,
    selectedItem: ItemDespesa?,
    onItemChange: (ItemDespesa) -> Unit,
    onAdicionarItem: (ItemDespesa) -> Unit,
    onRemoverItem: () -> Unit,
    onConfirmClick: () -> Unit,
    onResetSelection: () -> Unit = {},
    connectionMessage: String? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = R.string.configure_sua_conta),
                canNavigateBack = false,
            )
        }, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        if (selectedItem != null) {
            InserirDespesaDialog(
                item = selectedItem,
                onItemChange = onItemChange,
                onSaveItem = onAdicionarItem,
                onDismiss = onResetSelection,
                onRemoverItem = onRemoverItem,
            )
        }

        LaunchedEffect(key1 = connectionMessage) {
            launch {

                if (connectionMessage != null) {
                    snackbarHostState.showSnackbar(message = connectionMessage)
                }
            }
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
            onRemoverItem = {},
            onConfirmClick = {},
            onItemChange = {},
            onAdicionarItem = {},
        )
    }
}
