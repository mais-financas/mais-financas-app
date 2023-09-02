package com.neuralnet.maisfinancas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.AppDropdown
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
fun AddDespesaScreen(modifier: Modifier = Modifier) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var nome by remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                placeholder = { Text(stringResource(R.string.nome)) },
                value = nome,
                onValueChange = { nome = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            var selectedOptionText by remember { mutableStateOf(list[0]) }
            var expanded by remember { mutableStateOf(false) }
            AppDropdown(
                options = list,
                selectedOptionText = selectedOptionText,
                onSelectedOptionText = { selectedOption -> selectedOptionText = selectedOption },
                expanded = expanded,
                onExpandedChanged = { expanded = it },
            )

            val calendarState = rememberDatePickerState()
            DatePicker(state = calendarState, showModeToggle = false)

            Text(text = "${calendarState.selectedDateMillis?.getDate()}")
        }
    }
}

private fun Long.getDate(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        sdf.format(Date(this))
    } catch (e: Exception) {
        e.toString()
    }
}