package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: FieldValidationError? = null,
    confirmPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val visibilityIcon = if (passwordVisibility)
        painterResource(R.drawable.ic_visibility)
    else
        painterResource(R.drawable.ic_visibility_off)

    val isError = errorMessage != null
    val label = if (confirmPassword) {
        stringResource(id = R.string.confirmar_senha)
    } else
        stringResource(id = R.string.senha)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        modifier = modifier,
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                if (isError) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = errorMessage?.message?.let { stringResource(id = it) }
                    )
                } else {
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = null
                    )
                }
            }
        },
        singleLine = true,
        isError = isError,
        supportingText = errorMessage?.let { error ->
            { Text(text = stringResource(id = error.message)) }
        },
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = keyboardActions,
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    MaisFinancasTheme {
        PasswordTextField(
            value = "",
            onValueChange = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        PasswordTextField(
            value = "",
            onValueChange = {},
            errorMessage = FieldValidationError.SENHA_INVALIDA,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordTextFieldPreview() {
    MaisFinancasTheme {
        PasswordTextField(
            value = "",
            onValueChange = {},
            confirmPassword = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmPasswordTextFieldWithErrorPreview() {
    MaisFinancasTheme {
        PasswordTextField(
            value = "",
            onValueChange = {},
            errorMessage = FieldValidationError.CONFIRMAR_SENHA_INVALIDA,
            confirmPassword = true,
        )
    }
}
