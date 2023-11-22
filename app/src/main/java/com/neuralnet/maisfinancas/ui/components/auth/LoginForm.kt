package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.auth.login.LoginFormState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun LoginForm(
    loginFormState: LoginFormState,
    onLoginFormStateChange: (LoginFormState) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.welcome),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        EmailTextField(
            value = loginFormState.email,
            onValueChange = {
                onLoginFormStateChange(
                    loginFormState.copy(
                        email = it,
                        emailErrorMessage = null,
                        senhaErrorMessage = loginFormState.senhaErrorMessage.takeIf { senhaError ->
                            senhaError != FieldValidationError.FALHA_LOGIN
                        }
                    )
                )
            },
            errorMessage = loginFormState.emailErrorMessage,
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        PasswordTextField(
            value = loginFormState.senha,
            onValueChange = {
                onLoginFormStateChange(
                    loginFormState.copy(
                        senha = it,
                        senhaErrorMessage = null,
                        emailErrorMessage = loginFormState.emailErrorMessage.takeIf { emailError ->
                            emailError != FieldValidationError.FALHA_LOGIN
                        }
                    )
                )
            },
            errorMessage = loginFormState.senhaErrorMessage,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        Text(
            text = stringResource(R.string.esqueceu_sua_senha),
            modifier = Modifier.align(Alignment.End)
        )

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.entrar))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    MaisFinancasTheme {
        LoginForm(
            loginFormState = LoginFormState(),
            onLoginFormStateChange = {},
            onLoginClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormWithErrorPreview() {
    MaisFinancasTheme {
        LoginForm(
            loginFormState = LoginFormState(
                emailErrorMessage = FieldValidationError.EMAIL_INVALIDO,
                senhaErrorMessage = FieldValidationError.SENHA_INVALIDA,
            ),
            onLoginFormStateChange = {},
            onLoginClick = {}
        )
    }
}
