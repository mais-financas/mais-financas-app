package com.neuralnet.maisfinancas.ui.components.despesa

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun DespesaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    errorMessage: FieldValidationError? = null,
) {
    val isError = errorMessage != null
    val bottomPadding = if (isError) 8.dp else 0.dp
    OutlinedTextField(
        placeholder = { Text(stringResource(R.string.nome)) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier.padding(bottom = bottomPadding),
        isError = errorMessage != null,
        supportingText = { errorMessage?.let { Text(text = stringResource(id = it.message)) } },
        keyboardOptions = keyboardOptions
    )
}
