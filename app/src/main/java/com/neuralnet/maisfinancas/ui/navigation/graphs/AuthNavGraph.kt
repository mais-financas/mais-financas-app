package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.ui.screens.WelcomeScreen
import com.neuralnet.maisfinancas.ui.screens.auth.login.LoginScreen
import com.neuralnet.maisfinancas.ui.screens.auth.login.LoginViewModel
import com.neuralnet.maisfinancas.ui.screens.auth.signup.SignupScreen
import com.neuralnet.maisfinancas.ui.screens.auth.signup.SignupViewModel
import com.neuralnet.maisfinancas.ui.screens.setup.SetupScreen
import com.neuralnet.maisfinancas.ui.screens.setup.SetupViewModel

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = HomeDestinations.AuthGraph.route,
        startDestination = AuthScreen.Welcome.route,
    ) {
        composable(route = AuthScreen.Welcome.route) {
            WelcomeScreen(
                onNavigateSignup = { navController.navigate(AuthScreen.SignUp.route) },
                onNavigateLogin = { navController.navigate(AuthScreen.Login.route) },
            )
        }

        composable(route = AuthScreen.Login.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()

            LoginScreen(
                viewModel = loginViewModel,
                onNavigateBack = { navController.navigateUp() },
                navigateToHome = { navController.navigate(route = HOME_GRAPH) },
                onNavigateSignUp = { navController.navigate(route = AuthScreen.SignUp.route) }
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            val signUpViewModel = hiltViewModel<SignupViewModel>()

            SignupScreen(
                signUpViewModel = signUpViewModel,
                onNavigateUp = { navController.navigateUp() },
                onNavigateLogin = { navController.navigate(route = AuthScreen.Login.route) },
                navigateToSetup = { navController.navigate(route = AuthScreen.Setup.route) }
            )
        }

        composable(route = AuthScreen.Setup.route) {
            val setupViewModel = hiltViewModel<SetupViewModel>()

            SetupScreen(
                viewModel = setupViewModel,
                navigateToHome = { navController.navigate(route = HOME_GRAPH) }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen("welcome")
    data object Login : AuthScreen("login")
    data object SignUp : AuthScreen("sign_up")
    data object Setup : AuthScreen("setup")
}