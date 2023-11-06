package com.neuralnet.maisfinancas.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.auth.SignUpFormState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.FieldValidationError

@Composable
fun SignUpForm(
    formState: SignUpFormState,
    onSignUpFormStateChange: (SignUpFormState) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.crie_uma_conta),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )

        PersonNameTextField(
            value = formState.nome,
            onValueChange = {
                onSignUpFormStateChange(
                    formState.copy(nome = it, nomeErrorMessage = null)
                )
            },
            errorMessage = formState.nomeErrorMessage,
            modifier = Modifier.fillMaxWidth(),
        )

        EmailTextField(
            value = formState.email,
            onValueChange = {
                onSignUpFormStateChange(
                    formState.copy(email = it, emailErrorMessage = null)
                )
            },
            errorMessage = formState.emailErrorMessage,
            modifier = Modifier.fillMaxWidth(),
        )

        PasswordTextField(
            value = formState.senha,
            onValueChange = {
                onSignUpFormStateChange(
                    formState.copy(
                        senha = it,
                        senhaErrorMessage = null
                    )
                )
            },
            errorMessage = formState.senhaErrorMessage,
            modifier = Modifier.fillMaxWidth(),
        )

        PasswordTextField(
            value = formState.confirmarSenha,
            onValueChange = {
                onSignUpFormStateChange(
                    formState.copy(
                        confirmarSenha = it,
                        confirmarSenhaErrorMessage = null
                    )
                )
            },
            confirmPassword = true,
            errorMessage = formState.confirmarSenhaErrorMessage,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Start),
        ) {
            Checkbox(
                checked = formState.agreeWithTermsAndConditions,
                onCheckedChange = {
                    onSignUpFormStateChange(
                        formState.copy(
                            agreeWithTermsAndConditions = !formState.agreeWithTermsAndConditions
                        )
                    )
                }
            )
            Text(text = stringResource(id = R.string.concordar_termos))
        }

        Button(onClick = onSignUpClick, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.registrar))
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFormPreview() {
    MaisFinancasTheme {
        SignUpForm(
            formState = SignUpFormState(),
            onSignUpFormStateChange = {},
            onSignUpClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpFormWithErrorPreview() {
    MaisFinancasTheme {
        SignUpForm(
            formState = SignUpFormState(
                nomeErrorMessage = FieldValidationError.NOME_INVALIDO,
                emailErrorMessage = FieldValidationError.EMAIL_INVALIDO,
                senhaErrorMessage = FieldValidationError.SENHA_INVALIDA,
                confirmarSenhaErrorMessage = FieldValidationError.CONFIRMAR_SENHA_INVALIDA
            ),
            onSignUpFormStateChange = {},
            onSignUpClick = {}
        )
    }
}
