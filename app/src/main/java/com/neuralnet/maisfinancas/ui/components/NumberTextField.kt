package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun NumberTextField(
    valor: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null
) {
    val isError = errorMessage != null
    val bottomPadding = if (isError) 8.dp else 0.dp
    OutlinedTextField(
        placeholder = { Text(stringResource(R.string.valor)) },
        value = valor,
        onValueChange = onValueChange,
        singleLine = true,
        prefix = { Text(stringResource(R.string.moeda)) },
        modifier = modifier.padding(bottom = bottomPadding),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        isError = isError,
        supportingText = { errorMessage?.let { Text(text = stringResource(id = it.message)) } }
    )
}