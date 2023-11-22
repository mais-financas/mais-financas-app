package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.ui.components.core.NumberTextField
import com.neuralnet.maisfinancas.ui.components.despesa.CategoriaDropdown
import com.neuralnet.maisfinancas.ui.components.despesa.RecorrenciaDespesaDropdown
import com.neuralnet.maisfinancas.ui.components.despesa.DescricaoTextField
import com.neuralnet.maisfinancas.ui.components.despesa.getDate
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.DespesasDestinations
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.ServidorIndisponivel
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import kotlinx.coroutines.launch
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDespesaScreen(
    viewModel: AddDespesaViewModel,
    calendarState: DatePickerState = rememberDatePickerState(),
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val categoriasState = viewModel.categorias.collectAsStateWithLifecycle()

    val categorias by remember {
        derivedStateOf {
            categoriasState.value.map { it.nome }
        }
    }
    val connectionState = viewModel.connectionState.collectAsStateWithLifecycle()
    val connectionMessage = connectionState.value.message?.let { stringResource(id = it) }

    when (connectionState.value) {
        is ConnectionState.Loading -> LoadingScreen()
        is ConnectionState.ServerUnavailable -> {
            ServidorIndisponivel {
                viewModel.salvarDespesa(calendarState.selectedDateMillis)
            }
        }

        is ConnectionState.Success -> {
            LaunchedEffect(key1 = Unit) {
                navigateBack()
            }
        }

        else -> {
            AddDespesaScreen(
                uiState = uiState.value,
                onUiStateChanged = viewModel::updateUiState,
                categorias = categorias,
                calendarState = calendarState,
                onNavigateUp = onNavigateUp,
                onSaveClick = {
                    if (viewModel.isFormValid()) {
                        viewModel.salvarDespesa(calendarState.selectedDateMillis)
                    }
                },
                connectionMessage = connectionMessage,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDespesaScreen(
    uiState: AddDespesaUiState,
    onUiStateChanged: (AddDespesaUiState) -> Unit,
    categorias: List<String>,
    calendarState: DatePickerState,
    onNavigateUp: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    connectionMessage: String? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = DespesasDestinations.AddDespesa.title),
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
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(vertical = 8.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            LaunchedEffect(key1 = connectionMessage) {
                launch {
                    if (connectionMessage != null) {
                        snackbarHostState.showSnackbar(message = connectionMessage)
                    }
                }
            }

            DescricaoTextField(
                value = uiState.nome,
                onValueChange = {
                    onUiStateChanged(uiState.copy(nome = it, nomeErrorField = null))
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Next) }
                ),
                errorMessage = uiState.nomeErrorField,
            )

            NumberTextField(
                valor = uiState.valor,
                onValueChange = {
                    onUiStateChanged(uiState.copy(valor = it, valorErrorField = null))
                },
                errorMessage = uiState.valorErrorField,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )

            CategoriaDropdown(
                options = categorias,
                selected = uiState.categoria,
                onSelectedOptionText = {
                    onUiStateChanged(uiState.copy(categoria = it, categoriaErrorField = null))
                },
                errorMessage = uiState.categoriaErrorField,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )

            RecorrenciaDespesaDropdown(
                frequencia = uiState.frequencia,
                onFrequenciaChanged = { onUiStateChanged(uiState.copy(frequencia = it)) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.definir_lembrete),
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    enabled = uiState.frequencia != Frequencia.NENHUMA,
                    checked = uiState.definirLembrete && uiState.frequencia != Frequencia.NENHUMA,
                    onCheckedChange = { onUiStateChanged(uiState.copy(definirLembrete = it)) }
                )
            }

            if (uiState.definirLembrete) {
                if (uiState.frequencia != Frequencia.NENHUMA) {
                    val dataProximoLembrete =
                        remember(calendarState.selectedDateMillis, uiState.frequencia) {
                            derivedStateOf {
                                definirProximoLembrete(
                                    selectedDateMillis = calendarState.selectedDateMillis
                                        ?: Instant.now().toEpochMilli(),
                                    recorrencia = Recorrencia(
                                        frequencia = uiState.frequencia,
                                        quantidade = uiState.quantidadeRecorrencia
                                    )
                                )
                            }
                        }
                    Text(
                        text = stringResource(
                            R.string.prximo_lembrete,
                            dataProximoLembrete.value.timeInMillis.getDate()
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

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
private fun AddDespesaScreenPreview() {
    MaisFinancasTheme {
        AddDespesaScreen(
            uiState = AddDespesaUiState(
                frequencia = Frequencia.ANUAL,
                definirLembrete = true
            ),
            onUiStateChanged = {},
            categorias = listOf("Essenciais", "Entretenimento", "Saúde"),
            calendarState = rememberDatePickerState(),
            onNavigateUp = {},
            onSaveClick = {}
        )
    }
}
