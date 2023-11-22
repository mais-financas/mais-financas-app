package com.neuralnet.maisfinancas.ui.components.despesa

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun DescricaoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: FieldValidationError? = null,
) {
    TextField(
        placeholder = { Text(stringResource(R.string.nome)) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier,
        isError = errorMessage != null,
        supportingText = errorMessage?.let {
            { Text(text = stringResource(id = it.message)) }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}
