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
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriaDropdown(
    options: List<String>,
    selected: String,
    onSelectedOptionText: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        TextField(
            value = selected,
            onValueChange = { },
            readOnly = true,
            label = { Text(stringResource(id = R.string.categoria)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            isError = errorMessage != null,
            supportingText = errorMessage?.let {
                { Text(text = stringResource(id = it.message)) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize(),
        ) {
            options.forEach { categoria ->
                DropdownMenuItem(
                    onClick = {
                        onSelectedOptionText(categoria)
                        expanded = false
                    },
                    text = { Text(text = categoria) },
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 200)
@Composable
private fun CategoriaDropdownPreview() {
    MaisFinancasTheme {
        var selected by remember { mutableStateOf("") }

        CategoriaDropdown(
            options = listOf("Essenciais", "Saúde", "Entretenimento"),
            selected = selected,
            onSelectedOptionText = { selected = it }
        )
    }
}
