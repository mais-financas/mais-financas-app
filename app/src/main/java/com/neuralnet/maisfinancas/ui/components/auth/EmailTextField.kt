package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    val isError = errorMessage != null
    val bottomPadding = if (isError) 8.dp else 0.dp

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
        modifier = modifier.padding(bottom = bottomPadding),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        isError = isError,
        trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = errorMessage
                )
            }
        },
        supportingText = { errorMessage?.let { Text(text = it) } }
    )
}

@Preview
@Composable
private fun EmailTextFieldPreview() {
    MaisFinancasTheme {
        EmailTextField(
            value = "",
            onValueChange = {},
        )
    }
}

@Preview
@Composable
private fun EmailTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        EmailTextField(
            value = "",
            onValueChange = {},
            errorMessage = "Email não deve conter espaços em branco"
        )
    }
}
