package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.auth.LoginFormState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun LoginForm(
    loginFormState: LoginFormState,
    onLoginFormStateChange: (LoginFormState) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmailTextField(
            value = loginFormState.email,
            onValueChange = { onLoginFormStateChange(loginFormState.copy(email = it)) },
            errorMessage = loginFormState.emailErrorMessage,
            modifier = Modifier.fillMaxWidth()
        )

        PasswordTextField(
            value = loginFormState.senha,
            onValueChange = { onLoginFormStateChange(loginFormState.copy(senha = it)) },
            errorMessage = loginFormState.senhaErrorMessage,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = loginFormState.rememberMe,
                onCheckedChange = {
                    onLoginFormStateChange(
                        loginFormState.copy(rememberMe = !loginFormState.rememberMe)
                    )
                }
            )
            Text(
                text = stringResource(id = R.string.remember_me),
                modifier = Modifier.weight(1f)
            )

            Text(text = stringResource(R.string.esqueceu_sua_senha))
        }

        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(stringResource(R.string.entrar))
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    MaisFinancasTheme {
        LoginForm(
            loginFormState = LoginFormState(),
            onLoginFormStateChange = {},
            onLoginClick = {}
        )
    }
}
