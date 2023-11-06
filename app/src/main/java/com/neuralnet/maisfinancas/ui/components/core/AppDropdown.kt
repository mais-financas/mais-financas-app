package com.neuralnet.maisfinancas.ui.components.core

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
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun AppDropdown(
    label: Int,
    options: List<String>,
    selectedOptionText: String,
    onSelectedOptionText: (String) -> Unit,
    expanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val isError = errorMessage != null
    val bottomPadding = if (isError) 8.dp else 0.dp
    Column(modifier.padding(bottom = bottomPadding, start = 16.dp, end = 16.dp)) {

        OutlinedTextField(
            value = selectedOptionText,
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
            },
            isError = isError,
            supportingText = { errorMessage?.let { Text(text = stringResource(id = it.message)) } },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChanged(false) },
            modifier = Modifier
                .width(textFieldSize.width.dp)
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    onClick = {
                        onSelectedOptionText(label)
                        onExpandedChanged(false)
                    },
                    text = {
                        Text(text = label)
                    }
                )
            }
        }
    }
}
