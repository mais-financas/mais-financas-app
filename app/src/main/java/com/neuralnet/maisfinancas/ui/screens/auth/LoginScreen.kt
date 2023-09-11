package com.neuralnet.maisfinancas.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.auth.LoginForm
import com.neuralnet.maisfinancas.ui.components.auth.LoginOptions
import com.neuralnet.maisfinancas.ui.components.MaisFinancasBackground
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    loginFormState: LoginFormState,
    onLoginFormStateChange: (LoginFormState) -> Unit,
    onLoginClick: () -> Unit,
    onSigninWithFacebook: () -> Unit,
    onSigninWithGoogle: () -> Unit,
    onSigninWithTwitter: () -> Unit,
    onNavigateSignup: (Int) -> Unit,
) {
    MaisFinancasBackground(
        canNavigateBack = true,
        onNavigateUp = onNavigateBack,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.heightIn(48.dp)
        )

        Text(
            text = stringResource(R.string.welcome),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp)
        )

        LoginForm(
            onLoginClick = onLoginClick,
            loginFormState = loginFormState,
            onLoginFormStateChange = onLoginFormStateChange
        )

        LoginOptions(
            label = stringResource(id = R.string.login_options_description),
            onSigninWithFacebook = onSigninWithFacebook,
            onSigninWithGoogle = onSigninWithGoogle,
            onSigninWithTwitter = onSigninWithTwitter,
        )

        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle()) {
                    append(stringResource(R.string.nao_possui_conta))
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.registrar))
                }
            },
            onClick = onNavigateSignup,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaisFinancasTheme {
        LoginScreen(
            onNavigateBack = {},
            loginFormState = LoginFormState(),
            onLoginFormStateChange = {},
            onLoginClick = {},
            onSigninWithFacebook = {},
            onSigninWithGoogle = {},
            onSigninWithTwitter = {},
            onNavigateSignup = {}
        )
    }
}
