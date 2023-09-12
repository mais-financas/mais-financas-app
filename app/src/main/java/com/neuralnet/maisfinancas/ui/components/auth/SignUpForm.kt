package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.auth.SignUpFormState

@Composable
fun SignUpForm(
    signUpFormState: SignUpFormState,
    onSignUpFormStateChange: (SignUpFormState) -> Unit,
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.crie_uma_conta),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        NameTextField(
            value = signUpFormState.nome,
            onValueChange = { onSignUpFormStateChange(signUpFormState.copy(nome = it)) },
            modifier = Modifier.fillMaxWidth(),
        )

        EmailTextField(
            value = signUpFormState.email,
            onValueChange = { onSignUpFormStateChange(signUpFormState.copy(email = it)) },
            modifier = Modifier.fillMaxWidth(),
        )

        PasswordTextField(
            value = signUpFormState.senha,
            onValueChange = { onSignUpFormStateChange(signUpFormState.copy(senha = it)) },
            modifier = Modifier.fillMaxWidth(),
        )

        PasswordTextField(
            value = signUpFormState.confirmarSenha,
            onValueChange = { onSignUpFormStateChange(signUpFormState.copy(confirmarSenha = it)) },
            confirmPassword = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Start),
        ) {
            Checkbox(
                checked = signUpFormState.agreeWithTermsAndConditions,
                onCheckedChange = {
                    onSignUpFormStateChange(
                        signUpFormState.copy(
                            agreeWithTermsAndConditions = !signUpFormState.agreeWithTermsAndConditions
                        )
                    )
                }
            )
            Text(text = stringResource(id = R.string.concordar_termos))
        }

        Button(onClick = onSignUpClick, Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text(text = stringResource(id = R.string.registrar))
        }

    }
}