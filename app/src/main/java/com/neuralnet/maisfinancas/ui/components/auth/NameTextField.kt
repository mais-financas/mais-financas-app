package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    required: Boolean = false,
) {
    val isError = errorMessage != null
    val bottomPadding = if (isError || required) 8.dp else 0.dp

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = R.string.nome)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
            )
        },
        modifier = modifier.padding(bottom = bottomPadding),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
        ),
        isError = isError,
        supportingText = {
            if (errorMessage != null) {
                Text(text = errorMessage)
            } else if (required) {
                Text(text = stringResource(R.string.required))
            }
        },
    )
}

@Preview
@Composable
fun NameTextFieldPreview() {
    MaisFinancasTheme {
        NameTextField(
            value = "",
            onValueChange = {},
        )
    }
}

@Preview
@Composable
private fun NameTextFieldRequiredPreview() {
    MaisFinancasTheme {
        NameTextField(
            value = "",
            onValueChange = {},
            required = true,
        )
    }
}

@Preview
@Composable
fun NameTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        NameTextField(
            value = "",
            onValueChange = {},
            errorMessage = "Nome não deve conter números ou caracteres especiais"
        )
    }
}
