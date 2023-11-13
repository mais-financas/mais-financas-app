package com.neuralnet.maisfinancas.ui.screens.auth.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.auth.LoginForm
import com.neuralnet.maisfinancas.ui.components.core.MaisFinancasBackground
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateBack: () -> Unit,
    onLoginClick: () -> Unit,
    onNavigateSignUp: (Int) -> Unit
) {
    val loginFormState = viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        onNavigateBack = onNavigateBack,
        loginFormState = loginFormState.value,
        onLoginFormStateChange = viewModel::updateLoginFormState,
        onLoginClick = onLoginClick,
        onSigninWithFacebook = { /*TODO*/ },
        onSigninWithGoogle = { /*TODO*/ },
        onSigninWithTwitter = { /*TODO*/ },
        onNavigateSignup = onNavigateSignUp
    )
}

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
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.heightIn(36.dp)
        )

        LoginForm(
            onLoginClick = onLoginClick,
            loginFormState = loginFormState,
            onLoginFormStateChange = onLoginFormStateChange,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        /*
        LoginOptions(
            onSigninWithFacebook = onSigninWithFacebook,
            onSigninWithGoogle = onSigninWithGoogle,
            onSigninWithTwitter = onSigninWithTwitter,
        )
        */

        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.nao_possui_conta))
                }

                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.registrar))
                }
            },
            onClick = onNavigateSignup,
        )
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "pixel4", device = "id:pixel_4")
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
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
