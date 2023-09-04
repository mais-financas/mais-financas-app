package com.neuralnet.maisfinancas.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.AppDropdown
import com.neuralnet.maisfinancas.ui.components.RecorrenciaDespesa
import com.neuralnet.maisfinancas.ui.components.items
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val list = listOf(
    "Essenciais",
    "Transporte",
    "Alimentação",
    "Entretenimento",
    "Saúde",
    "Educação",
    "Dívidas"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDespesaScreen(
    nome: String,
    onNomeChanged: (String) -> Unit,
    valor: String,
    onValorChanged: (String) -> Unit,
    categoria: String,
    onCategoriaChanged: (String) -> Unit,
    recorrencia: String,
    onRecorrenciaChanged: (String) -> Unit,
    calendarState: DatePickerState,
    onNavigateUp: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = HomeDestinations.AddDespesa.title,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(
                    state = rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            OutlinedTextField(
                placeholder = { Text(stringResource(R.string.nome)) },
                value = nome,
                onValueChange = onNomeChanged,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )

            OutlinedTextField(
                placeholder = { Text(stringResource(R.string.valor)) },
                value = valor,
                onValueChange = onValorChanged,
                singleLine = true,
                prefix = { Text("R$") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            var expanded by remember { mutableStateOf(false) }
            AppDropdown(
                label = R.string.categoria,
                options = list,
                selectedOptionText = categoria,
                onSelectedOptionText = onCategoriaChanged,
                expanded = expanded,
                onExpandedChanged = { expanded = it },
            )

            RecorrenciaDespesa(
                recorrencia = recorrencia,
                onRecorrenciaChanged = onRecorrenciaChanged,
                modifier = Modifier.padding(top = 8.dp)
            )

            var definirLembrete by remember {
                mutableStateOf(false)
            }
            AnimatedVisibility(visible = recorrencia != items[0]) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Definir Lembrete?", modifier = Modifier.weight(1f))
                    Switch(
                        checked = definirLembrete,
                        onCheckedChange = {
                            definirLembrete = !definirLembrete
                        }
                    )
                }
            }

            DatePicker(state = calendarState, showModeToggle = false)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AddDespesaScreenPreview() {
    MaisFinancasTheme {
        AddDespesaScreen(
            nome = "",
            onNomeChanged = { },
            valor = "",
            onValorChanged = { },
            categoria = "",
            onCategoriaChanged = { },
            recorrencia = "",
            onRecorrenciaChanged = { },
            calendarState = rememberDatePickerState(),
            onNavigateUp = { },
            onSaveClick = {}
        )
    }
}
