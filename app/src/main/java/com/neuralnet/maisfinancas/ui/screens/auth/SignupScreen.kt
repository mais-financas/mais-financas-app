package com.neuralnet.maisfinancas.ui.screens.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.MaisFinancasBackground
import com.neuralnet.maisfinancas.ui.components.auth.LoginOptions
import com.neuralnet.maisfinancas.ui.components.auth.SignUpForm
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun SignupScreen(
    signUpFormState: SignUpFormState,
    onSignUpFormStateChange: (SignUpFormState) -> Unit,
    onNavigateUp: () -> Unit,
    onSignUpClick: () -> Unit,
    onSigninWithFacebook: () -> Unit,
    onSigninWithGoogle: () -> Unit,
    onSigninWithTwitter: () -> Unit,
    onNavigateLogin: (Int) -> Unit,
) {
    MaisFinancasBackground(
        canNavigateBack = true,
        onNavigateUp = onNavigateUp
    ) {

        Text(
            text = stringResource(R.string.crie_uma_conta),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )

        SignUpForm(
            signUpFormState = signUpFormState,
            onSignUpFormStateChange = onSignUpFormStateChange,
            onSignUpClick = onSignUpClick,
        )

        LoginOptions(
            label = stringResource(id = R.string.signup_options_description),
            onSigninWithFacebook = onSigninWithFacebook,
            onSigninWithGoogle = onSigninWithGoogle,
            onSigninWithTwitter = onSigninWithTwitter)

        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle()) {
                    append(stringResource(R.string.possui_conta))
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.fazer_login))
                }
            },
            onClick = onNavigateLogin,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignupScreenPreview() {
    MaisFinancasTheme {
        SignupScreen(
            signUpFormState = SignUpFormState(),
            onSignUpFormStateChange = {},
            onNavigateUp = {},
            onSignUpClick = {},
            onSigninWithFacebook = {},
            onSigninWithGoogle = {},
            onSigninWithTwitter = {},
            onNavigateLogin = {},
        )
    }
}
