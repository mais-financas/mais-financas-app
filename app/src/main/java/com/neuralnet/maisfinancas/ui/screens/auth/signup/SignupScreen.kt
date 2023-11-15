package com.neuralnet.maisfinancas.ui.screens.auth.signup

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.neuralnet.maisfinancas.ui.components.auth.SignUpForm
import com.neuralnet.maisfinancas.ui.components.core.MaisFinancasBackground
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.NoConnectionScreen
import com.neuralnet.maisfinancas.ui.screens.ServidorIndisponivel
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun SignupScreen(
    signUpViewModel: SignupViewModel,
    onNavigateUp: () -> Unit,
    onNavigateLogin: () -> Unit,
    navigateToSetup: () -> Unit
) {
    val uiState = signUpViewModel.uiState.collectAsStateWithLifecycle()

    val authState = signUpViewModel.authState.collectAsStateWithLifecycle()

    when (authState.value) {
        AuthState.Loading -> {
            LoadingScreen()
        }

        AuthState.LoggedIn -> {
            LaunchedEffect(key1 = Unit) {
                navigateToSetup()
            }
        }

        AuthState.NotLoggedIn -> {
            SignupScreen(
                signUpFormState = uiState.value,
                onSignUpFormStateChange = signUpViewModel::updateState,
                onNavigateUp = onNavigateUp,
                onSignUpClick = {
                    if (signUpViewModel.isFormValid()) {
                        signUpViewModel.signUp()
                    }
                },
                onNavigateLogin = onNavigateLogin,
            )
        }

        AuthState.ConnectionError -> {
            NoConnectionScreen()
        }

        AuthState.ServerUnavailableError -> {
            ServidorIndisponivel(signUpViewModel::signUp)
        }
    }
}

@Composable
fun SignupScreen(
    signUpFormState: SignupFormState,
    onSignUpFormStateChange: (SignupFormState) -> Unit,
    onNavigateUp: () -> Unit,
    onSignUpClick: () -> Unit,
    onNavigateLogin: () -> Unit,
) {
    MaisFinancasBackground(
        canNavigateBack = true,
        onNavigateUp = onNavigateUp,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.heightIn(36.dp)
        )

        SignUpForm(
            formState = signUpFormState,
            onSignUpFormStateChange = onSignUpFormStateChange,
            onSignUpClick = onSignUpClick,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        ClickableText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(stringResource(R.string.possui_conta))
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.fazer_login))
                }
            },
            onClick = { onNavigateLogin() },
        )

    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignupScreenPreview() {
    MaisFinancasTheme {
        SignupScreen(
            signUpFormState = SignupFormState(),
            onSignUpFormStateChange = {},
            onNavigateUp = {},
            onSignUpClick = {},
            onNavigateLogin = {},
        )
    }
}
