package com.neuralnet.maisfinancas.ui.components.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.ui.components.core.NumberTextField
import com.neuralnet.maisfinancas.ui.components.despesa.RecorrenciaDespesaDropdown
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InserirDespesaDialog(
    item: ItemDespesa,
    onItemChange: (ItemDespesa) -> Unit,
    onSaveItem: (ItemDespesa) -> Unit,
    onDismiss: () -> Unit,
    onRemoverItem: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = MaterialTheme.shapes.large) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = item.nome,
                    style = MaterialTheme.typography.headlineSmall,
                )

                NumberTextField(
                    valor = item.valor,
                    onValueChange = {
                        onItemChange(item.copy(valor = it, valorErrorField = null))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    errorMessage = item.valorErrorField
                )

                RecorrenciaDespesaDropdown(
                    frequencia = item.recorrencia.frequencia,
                    onFrequenciaChanged = { frequencia ->
                        onItemChange(
                            item.copy(
                                recorrencia = item.run { recorrencia.copy(frequencia = frequencia) })
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DatePicker(state = datePickerState, showModeToggle = false, title = null)

                Row(modifier = Modifier.align(Alignment.End)) {
                    TextButton(onClick = onRemoverItem) {
                        Text(
                            text = if (item.selecionado)
                                stringResource(id = R.string.remover)
                            else
                                stringResource(id = R.string.cancelar),
                        )
                    }

                    TextButton(
                        onClick = {
                            onSaveItem(
                                item.copy(dataEmMillis = datePickerState.selectedDateMillis ?: 0)
                            )
                        },
                        enabled = datePickerState.selectedDateMillis != null,
                    ) {
                        Text(text = stringResource(id = R.string.confirmar))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun InserirDespesaDialogPreview() {
    MaisFinancasTheme {
        val item = SugestoesDatasource.despesas[0][0]

        InserirDespesaDialog(
            item = item,
            onItemChange = {},
            onSaveItem = {},
            onDismiss = {},
            onRemoverItem = {})
    }
}
