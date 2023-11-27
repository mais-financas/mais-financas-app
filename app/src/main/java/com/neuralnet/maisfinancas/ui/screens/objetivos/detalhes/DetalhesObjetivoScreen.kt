package com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.neuralnet.maisfinancas.util.toReal
import kotlinx.coroutines.launch

@Composable
fun DetalhesObjetivoScreen(
    viewModel: DetalhesObjetivoViewModel,
    onNavigateUp: () -> Unit,
) {
    val detalhesObjetivoUiStateState = viewModel.detalhesObjetivoState.collectAsStateWithLifecycle()
    val updateObjetivoUiState = viewModel.updateObjetivoUiState.collectAsStateWithLifecycle()

    val modoAtivo = viewModel.modoAtivo.collectAsStateWithLifecycle()

    val connectionState = viewModel.connectionState.collectAsStateWithLifecycle()
    val connectionMessage = connectionState.value.message?.let { stringResource(id = it) }

    when (connectionState.value) {
        is ConnectionState.Loading -> LoadingScreen()
        is ConnectionState.ServerUnavailable -> {
            ServidorIndisponivel(
                onFinish = {
                    if (modoAtivo.value == ModoAtivo.GUARDAR) {
                        viewModel.guardar()
                    } else if (modoAtivo.value == ModoAtivo.RESGATAR) {
                        viewModel.resgatar()
                    }
                }
            )
        }

        else -> {
            DetalhesObjetivoScreen(
                detalhesObjetivoUiState = detalhesObjetivoUiStateState.value,
                updateObjetivoUiState = updateObjetivoUiState.value,
                modoAtivo = modoAtivo.value,
                onAlternarModoAtivo = viewModel::alternarModoAtivo,
                onUiStateChange = viewModel::updateObjetivoState,
                onNavigateUp = onNavigateUp,
                onResgatarClick = {
                    if (viewModel.isFormValid()) {
                        viewModel.resgatar()
                    }
                },
                onGuardarClick = {
                    if (viewModel.isFormValid()) {
                        viewModel.guardar()
                    }
                },
                connectionMessage = connectionMessage,
                onResetState = viewModel::resetObjetivoState,
            )
        }
    }
}

@Composable
fun DetalhesObjetivoScreen(
    detalhesObjetivoUiState: DetalhesObjetivoUiState,
    updateObjetivoUiState: UpdateObjetivoUiState,
    modoAtivo: ModoAtivo,
    onAlternarModoAtivo: (ModoAtivo) -> Unit,
    onUiStateChange: (UpdateObjetivoUiState) -> Unit,
    onNavigateUp: () -> Unit,
    onResgatarClick: () -> Unit,
    onGuardarClick: () -> Unit,
    connectionMessage: String? = null,
    onResetState: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = R.string.meu_objetivo),
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
                text = detalhesObjetivoUiState.descricao,
                style = MaterialTheme.typography.headlineSmall,
            )

            Text(
                text = stringResource(
                    R.string.total_guardado,
                    detalhesObjetivoUiState.valor.toReal()
                ),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )

            if (modoAtivo != ModoAtivo.INVISIBLE) {
                AlertDialog(
                    onDismissRequest = {
                        onAlternarModoAtivo(ModoAtivo.INVISIBLE)
                    },
                    title = {
                        Text(
                            text = detalhesObjetivoUiState.descricao,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    text = {
                        ObjetivoTextField(
                            valor = updateObjetivoUiState.valor,
                            saldo = updateObjetivoUiState.saldo,
                            onValueChange = {
                                onUiStateChange(
                                    updateObjetivoUiState.copy(valor = it, valorErrorMessage = null)
                                )
                            },
                        )
                    },
                    dismissButton = {
                        TextButton(
                            onClick = onResetState

                        ) {
                            Text(text = stringResource(id = R.string.cancelar))
                        }
                    },
                    confirmButton = {
                        if (modoAtivo == ModoAtivo.GUARDAR) {
                            TextButton(
                                onClick = onGuardarClick,
                                enabled = updateObjetivoUiState.isFormValid(),
                            ) {
                                Text(text = stringResource(id = R.string.guardar))
                            }
                        } else {
                            TextButton(
                                onClick = onResgatarClick,
                                enabled = updateObjetivoUiState.isFormValid(),
                            ) {
                                Text(text = stringResource(id = R.string.resgatar))
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.weight(.55f))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        onAlternarModoAtivo(ModoAtivo.RESGATAR)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.resgatar))
                }

                Button(
                    onClick = { onAlternarModoAtivo(ModoAtivo.GUARDAR) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.guardar))
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetalhesObjetivoScreenPreview() {
    MaisFinancasTheme {
        DetalhesObjetivoScreen(
            detalhesObjetivoUiState = DetalhesObjetivoUiState(descricao = "Casa própria"),
            updateObjetivoUiState = UpdateObjetivoUiState(),
            modoAtivo = ModoAtivo.INVISIBLE,
            onAlternarModoAtivo = {},
            onUiStateChange = {},
            onNavigateUp = {},
            onResgatarClick = {},
            onGuardarClick = {},
            onResetState = {},
        )
    }
}
