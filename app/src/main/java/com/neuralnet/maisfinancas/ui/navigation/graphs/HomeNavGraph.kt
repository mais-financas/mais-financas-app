package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neuralnet.maisfinancas.ui.screens.AddDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.HomeContent

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        route = AppGraph.HOME,
        startDestination = HomeDestinations.Home.route,
        modifier = modifier,
    ) {

        composable(route = HomeDestinations.Home.route) {
            HomeContent()
        }

        composable(route = HomeDestinations.AddDespesa.route) {
            AddDespesaScreen()
        }

        despesasNavGraph(navController)

        composable(route = HomeDestinations.Statistics.route) {
            Screen(
                route = HomeDestinations.Statistics.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }

        composable(route = HomeDestinations.FinancialGoals.route) {
            Screen(
                route = HomeDestinations.FinancialGoals.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }

        composable(route = HomeDestinations.Profile.route) {
            Screen(
                route = HomeDestinations.Profile.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }
    }
}

sealed class HomeDestinations(val route: String) {
    object Home : HomeDestinations("home")
    object DespesasGraph : HomeDestinations("despesas_graph")
    object AddDespesa : HomeDestinations("add_despesa")
    object Statistics : HomeDestinations("statistics")
    object FinancialGoals : HomeDestinations("financial_goals")
    object Profile : HomeDestinations("profile")
}