package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.ui.screens.Screen
import com.neuralnet.maisfinancas.ui.screens.WelcomeScreen
import com.neuralnet.maisfinancas.ui.screens.auth.LoginFormState
import com.neuralnet.maisfinancas.ui.screens.auth.LoginScreen
import com.neuralnet.maisfinancas.ui.screens.auth.SignUpViewModel
import com.neuralnet.maisfinancas.ui.screens.auth.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = HomeDestinations.AuthGraph.route,
        startDestination = AuthScreen.Welcome.route,
    ) {
        composable(route = AuthScreen.Welcome.route) {
            WelcomeScreen(
                onEntrarClick = { navController.navigate(AuthScreen.Login.route) },
                onNavigateSignup = { navController.navigate(AuthScreen.SignUp.route) }
            )
        }

        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                onNavigateBack = { navController.navigateUp() },
                loginFormState = LoginFormState(),
                onLoginFormStateChange = {},
                onLoginClick = { navController.navigate(HOME_GRAPH) },
                onSigninWithFacebook = { /*TODO*/ },
                onSigninWithGoogle = { /*TODO*/ },
                onSigninWithTwitter = { /*TODO*/ },
                onNavigateSignup = { navController.navigate(AuthScreen.SignUp.route) }
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            val signUpViewModel = hiltViewModel<SignUpViewModel>()
            val uiState by signUpViewModel.uiState.collectAsStateWithLifecycle()

            SignupScreen(
                signUpFormState = uiState,
                onSignUpFormStateChange = signUpViewModel::updateState,
                onNavigateUp = { navController.navigateUp() },
                onSignUpClick = {
                    signUpViewModel.cadastrarUsuario()
                    navController.navigate(HOME_GRAPH)
                },
                onNavigateLogin = { navController.navigate(AuthScreen.Login.route) }
            )

        }

        composable(route = AuthScreen.Setup.route) {
            Screen(
                route = AuthScreen.Setup.route,
                onClick = { navController.navigate(route = HOME_GRAPH) })
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen("welcome")
    data object Login : AuthScreen("login")
    data object SignUp : AuthScreen("sign_up")
    data object Setup : AuthScreen("setup")
}