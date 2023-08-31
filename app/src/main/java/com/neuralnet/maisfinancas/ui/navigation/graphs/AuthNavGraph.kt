package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.AUTH,
        startDestination = AuthScreen.Welcome.route,
    ) {
        composable(route = AuthScreen.Welcome.route) {
            Screen(
                route = AuthScreen.Welcome.route,
                onClick = { navController.navigate(route = AuthScreen.SignUp.route) })
        }

        composable(route = AuthScreen.Login.route) {
            Screen(
                route = AuthScreen.Login.route,
                onClick = { navController.navigate(route = AuthScreen.Setup.route) })
        }

        composable(route = AuthScreen.SignUp.route) {
            Screen(
                route = AuthScreen.SignUp.route,
                onClick = { navController.navigate(route = AuthScreen.Login.route) })
        }

        composable(route = AuthScreen.Setup.route) {
            Screen(
                route = AuthScreen.Setup.route,
                onClick = { navController.navigate(route = AppGraph.HOME) })
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Welcome : AuthScreen("welcome")
    object Login : AuthScreen("login")
    object SignUp : AuthScreen("sign_up")
    object Setup : AuthScreen("setup")
}