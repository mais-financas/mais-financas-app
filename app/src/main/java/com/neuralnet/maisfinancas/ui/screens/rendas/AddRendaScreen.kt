package com.neuralnet.maisfinancas.ui.screens.rendas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.core.NumberTextField
import com.neuralnet.maisfinancas.ui.components.despesa.ValorDescricaoTextField
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.ServidorIndisponivel
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRendaScreen(
    viewModel: AddRendaViewModel,
    calendarState: DatePickerState,
    onNavigateUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val connectionState = viewModel.connectionState.collectAsStateWithLifecycle()
    val connectionMessage = connectionState.value.message?.let { stringResource(id = it) }

    when (connectionState.value) {
        is ConnectionState.Loading -> LoadingScreen()
        is ConnectionState.ServerUnavailable -> {
            ServidorIndisponivel {
                viewModel.salvarRenda(calendarState.selectedDateMillis)
            }
        }
        is ConnectionState.Success -> {
            LaunchedEffect(key1 = Unit) {
                navigateToHome()
            }
        }
        else -> {
            AddRendaScreen(
                uiState = uiState.value,
                onUiStateChange = viewModel::updateUiState,
                calendarState = calendarState,
                onNavigateUp = onNavigateUp,
                onSaveClick = {
                    if (viewModel.isFormValid()) {
                        viewModel.salvarRenda(calendarState.selectedDateMillis)
                    }
                },
                connectionMessage = connectionMessage,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRendaScreen(
    uiState: AddRendaUiState,
    onUiStateChange: (AddRendaUiState) -> Unit,
    calendarState: DatePickerState,
    onNavigateUp: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    connectionMessage: String? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.AddRenda.title),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.add)
                )
            }
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
            modifier = modifier
                .padding(paddingValues)
                .padding(top = 8.dp)
                .verticalScroll(
                    state = rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            ValorDescricaoTextField(
                value = uiState.descricao,
                onValueChange = {
                    onUiStateChange(uiState.copy(descricao = it, descricaoErrorField = null))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                errorMessage = uiState.descricaoErrorField,
            )

            NumberTextField(
                valor = uiState.valor,
                onValueChange = {
                    onUiStateChange(uiState.copy(valor = it, valorErrorField = null))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                errorMessage = uiState.valorErrorField,
            )

            DatePicker(
                state = calendarState,
                showModeToggle = false,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
private fun AddRendaScreenPreview() {
    MaisFinancasTheme {
        AddRendaScreen(
            uiState = AddRendaUiState(),
            onUiStateChange = {},
            calendarState = rememberDatePickerState(),
            onNavigateUp = {},
            onSaveClick = {}
        )
    }
}
