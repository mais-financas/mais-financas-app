package com.neuralnet.maisfinancas.ui.components.despesa

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecorrenciaDespesaDropdown(
    frequencia: Frequencia,
    onFrequenciaChanged: (Frequencia) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        TextField(
            value = stringResource(id = frequencia.descricao),
            onValueChange = { },
            readOnly = true,
            label = { Text(stringResource(id = R.string.recorrencia)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            Frequencia.values().forEach {
                DropdownMenuItem(
                    onClick = {
                        onFrequenciaChanged(Frequencia.from(it.descricao))
                        expanded = false
                    },
                    text = { Text(text = stringResource(id = it.descricao)) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecorrenciaDespesaPreview() {
    MaisFinancasTheme {
        var frequencia by remember { mutableStateOf(Frequencia.NENHUMA) }

        RecorrenciaDespesaDropdown(
            frequencia = frequencia,
            onFrequenciaChanged = { frequencia = it }
        )
    }
}
