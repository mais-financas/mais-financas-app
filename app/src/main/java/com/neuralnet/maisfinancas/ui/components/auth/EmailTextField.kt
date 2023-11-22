package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    val isError = errorMessage != null

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = R.string.email)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
            )
        },
        modifier = modifier,
        isError = isError,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = errorMessage?.message?.let { stringResource(id = it) }
                )
            }
        },
        singleLine = true,
        supportingText = errorMessage?.let { error ->
            { Text(text = stringResource(id = error.message)) }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        keyboardActions = keyboardActions,
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldPreview() {
    MaisFinancasTheme {
        EmailTextField(
            value = "",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 350)
@Composable
private fun EmailTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        EmailTextField(
            value = "",
            onValueChange = {},
            errorMessage = FieldValidationError.EMAIL_INVALIDO
        )
    }
}
