package com.neuralnet.maisfinancas.ui.components.objetivos

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal

@Composable
fun ObjetivoTextField(
    valor: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    saldo: BigDecimal = BigDecimal.ZERO,
    errorMessage: FieldValidationError? = null,
) {
    var isError by remember {
        mutableStateOf(errorMessage != null)
    }

    TextField(
        value = valor,
        onValueChange = { value ->
            value.toBigDecimalOrNull()?.let { isError = it > saldo }
            onValueChange(value)
        },
        singleLine = true,
        prefix = { Text(stringResource(R.string.moeda)) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        isError = isError,
        supportingText = {
            if (errorMessage == FieldValidationError.NUMERO_INVALIDO) {
                Text(text = stringResource(id = errorMessage.message))
            } else {
                Text(text = stringResource(R.string.disponivel, saldo.toReal()))
            }
        }
    )
}

@Preview
@Composable
private fun ObjetivoTextFieldPreview() {
    var valor by remember { mutableStateOf("") }

    MaisFinancasTheme {
        ObjetivoTextField(
            valor = valor,
            saldo = BigDecimal.TEN,
            onValueChange = { valor = it }
        )
    }
}
