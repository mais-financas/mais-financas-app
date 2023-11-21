package com.neuralnet.maisfinancas.ui.screens.objetivos.adicionar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.objetivos.ObjetivoTextField
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.ServidorIndisponivel
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import kotlinx.coroutines.launch

@Composable
fun AddObjetivoScreen(
    viewModel: AddObjetivoViewModel,
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val connectionState = viewModel.connectionState.collectAsStateWithLifecycle()
    val connectionMessage = connectionState.value.message?.let { stringResource(id = it) }

    when (connectionState.value) {
        is ConnectionState.Loading -> LoadingScreen()
        is ConnectionState.ServerUnavailable -> {
            ServidorIndisponivel(
                onFinish = {
                    viewModel.inserirObjetivo()
                }
            )
        }

        is ConnectionState.Success -> {
            LaunchedEffect(key1 = Unit) {
                navigateBack()
            }
        }

        else -> {
            AddObjetivoScreen(
                uiState = uiState.value,
                onUiStateChange = viewModel::updateState,
                onNavigateUp = onNavigateUp,
                onSaveClick = {
                    if (viewModel.isFormValid()) {
                        viewModel.inserirObjetivo()
                    }
                },
                connectionMessage = connectionMessage,
            )
        }
    }
}

@Composable
fun AddObjetivoScreen(
    uiState: AddObjetivoUiState,
    onUiStateChange: (AddObjetivoUiState) -> Unit,
    onNavigateUp: () -> Unit,
    onSaveClick: () -> Unit,
    connectionMessage: String? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = R.string.adicionar_objetivo),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        LaunchedEffect(key1 = connectionMessage) {
            launch {
                if (connectionMessage != null) {
                    snackbarHostState.showSnackbar(message = connectionMessage)
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(.25f))

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(150.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Savings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(.2f))

            Text(
                text = stringResource(R.string.nome_objetivo),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                placeholder = { Text(text = stringResource(R.string.descricao)) },
                value = uiState.descricao,
                onValueChange = { onUiStateChange(uiState.copy(descricao = it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = stringResource(R.string.quanto_gostaria_guardar),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            ObjetivoTextField(
                valor = uiState.valor,
                saldo = uiState.saldo,
                errorMessage = uiState.valorErrorMessage,
                onValueChange = {
                    onUiStateChange(uiState.copy(valor = it, valorErrorMessage = null))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(.55f))

            Button(
                onClick = onSaveClick,
                enabled = uiState.isFormValid(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.confirmar))
            }
        }
    }
}

@Preview
@Composable
private fun AddObjetivoScreenPreview() {
    MaisFinancasTheme {
        AddObjetivoScreen(
            uiState = AddObjetivoUiState(),
            onUiStateChange = {},
            onNavigateUp = {},
            onSaveClick = {},
        )
    }
}
