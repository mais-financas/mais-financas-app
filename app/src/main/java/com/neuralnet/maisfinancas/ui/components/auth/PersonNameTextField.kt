package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun PersonNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
    required: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    val isError = errorMessage != null

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
        modifier = modifier,
        singleLine = true,
        isError = isError,
        supportingText = if (required) {
            { Text(text = stringResource(id = R.string.required)) }
        } else {
            errorMessage?.let { error ->
                { Text(text = stringResource(id = error.message)) }
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = keyboardActions,
    )
}

@Preview(showBackground = true)
@Composable
private fun NameTextFieldPreview() {
    MaisFinancasTheme {
        PersonNameTextField(
            value = "",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NameTextFieldRequiredPreview() {
    MaisFinancasTheme {
        PersonNameTextField(
            value = "",
            onValueChange = {},
            required = true,
        )
    }
}

@Preview(showBackground = true, widthDp = 380)
@Composable
private fun NameTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        PersonNameTextField(
            value = "",
            onValueChange = {},
            errorMessage = FieldValidationError.NOME_INVALIDO
        )
    }
}
