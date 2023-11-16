package com.neuralnet.maisfinancas.ui.components.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.core.NumberTextField
import com.neuralnet.maisfinancas.ui.components.despesa.RecorrenciaDespesa
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InserirDespesaDialog(
    item: ItemDespesa,
    onItemChange: (ItemDespesa) -> Unit,
    onSaveItem: (ItemDespesa) -> Unit,
    onDismiss: () -> Unit,
    onRemoverItem: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = item.dataEmMillis,
        initialDisplayMode = DisplayMode.Input
    )

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = item.nome,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                NumberTextField(
                    valor = item.valor,
                    onValueChange = {
                        onItemChange(item.copy(valor = it, valorErrorField = null))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    errorMessage = item.valorErrorField
                )

                RecorrenciaDespesa(
                    frequencia = item.recorrencia.frequencia,
                    onRecorrenciaChanged = { frequencia ->
                        onItemChange(
                            item.copy(
                                recorrencia = item.run { recorrencia.copy(frequencia = frequencia) })
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DatePicker(state = datePickerState, showModeToggle = false)

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
//                        onClick = {
//                            if (valor.isBlank()) {
//                                valorErrorMessage = FieldValidationError.NUMERO_INVALIDO
//                            }
//                            onConfirmClick(
//                                item.copy(
//                                    valor = valor,
//                                    dataEmMillis = datePickerState.selectedDateMillis ?: 0,
//                                    recorrencia = recorrencia
//                                )
//                            )
//                        },
                        onClick = {
                            onSaveItem(
                                item.copy(dataEmMillis = datePickerState.selectedDateMillis ?: 0)
                            )
                        }
                    ) {
                        Text(text = stringResource(id = R.string.confirmar))
                    }
                }
            }
        }
    }
}
