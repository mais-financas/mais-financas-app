package com.neuralnet.maisfinancas.ui.components.despesa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.neuralnet.maisfinancas.model.despesa.Frequencia

@Composable
fun RecorrenciaDespesaDropdown(
    label: Int,
    options: Array<Frequencia>,
    selectedOptionText: Frequencia,
    onSelectedOptionText: (Frequencia) -> Unit,
    expanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)) {

        OutlinedTextField(
            value = stringResource(id = selectedOptionText.descricao),
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(label)) },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "up/down",
                    modifier = Modifier.clickable { onExpandedChanged(!expanded) }
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChanged(false) },
            modifier = Modifier
                .width(textFieldSize.width.dp)
        ) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onSelectedOptionText(Frequencia.from(it.descricao))
                    onExpandedChanged(false)
                }, text = {
                    Text(text = stringResource(id = it.descricao))
                })
            }
        }
    }
}
