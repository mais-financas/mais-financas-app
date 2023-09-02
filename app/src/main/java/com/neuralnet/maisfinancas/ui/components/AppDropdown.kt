package com.neuralnet.maisfinancas.ui.components

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
import com.neuralnet.maisfinancas.R

@Composable
fun AppDropdown(
    options: List<String>,
    selectedOptionText: String,
    onSelectedOptionText: (String) -> Unit,
    expanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = selectedOptionText,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable { onExpandedChanged(!expanded) },
            label = { Text(stringResource(R.string.categoria)) },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "up/down",
                    modifier = Modifier.clickable { onExpandedChanged(!expanded) }
                )
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChanged(false) },
            modifier = Modifier
                .width(textFieldSize.width.dp)
        ) {
            options.forEach { label ->
                DropdownMenuItem(onClick = {
                    onSelectedOptionText(label)
                    onExpandedChanged(false)
                }, text = {
                    Text(text = label)
                })
            }
        }
    }
}
