package com.neuralnet.maisfinancas.ui.components.core

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun NumberTextField(
    valor: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
) {
    val isError = errorMessage != null

    TextField(
        placeholder = { Text(stringResource(R.string.valor)) },
        value = valor,
        onValueChange = onValueChange,
        singleLine = true,
        prefix = { Text(stringResource(R.string.moeda)) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        isError = isError,
        supportingText = errorMessage?.let { error ->
            { Text(text = stringResource(id = error.message)) }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun NumberTextFieldPreview() {
    MaisFinancasTheme {
        NumberTextField(
            valor = "123",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        NumberTextField(
            valor = "NAN",
            onValueChange = {},
            errorMessage = FieldValidationError.NUMERO_INVALIDO
        )
    }
}
