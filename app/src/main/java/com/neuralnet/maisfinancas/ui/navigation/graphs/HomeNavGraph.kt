package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.depesas.adicionar.AddDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.adicionar.AddDespesaViewModel
import com.neuralnet.maisfinancas.ui.screens.home.HomeScreen
import com.neuralnet.maisfinancas.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        route = AppGraph.HOME,
        startDestination = HomeDestinations.Home.route,
        modifier = modifier,
    ) {

        composable(route = HomeDestinations.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(HomeDestinations.AddDespesa.route) }
            )
        }

        composable(route = HomeDestinations.AddDespesa.route) {
            val viewModel = hiltViewModel<AddDespesaViewModel>()
            val calendarState = rememberDatePickerState()

            AddDespesaScreen(
                viewModel = viewModel,
                calendarState = calendarState,
                onNavigateUp = { navController.navigateUp() },
                onSaveClick = {
                    viewModel.salvarDespesa(dataEmEpochMillis = calendarState.selectedDateMillis)
                    navController.popBackStack()
                }
            )
        }

        despesasNavGraph(navController)

        composable(route = HomeDestinations.Estatisticas.route) {
            Screen(
                route = HomeDestinations.Estatisticas.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }

        composable(route = HomeDestinations.FinancialGoals.route) {
            Screen(
                route = HomeDestinations.FinancialGoals.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }

        composable(route = HomeDestinations.Perfil.route) {
            Screen(
                route = HomeDestinations.Perfil.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }
    }
}

sealed class HomeDestinations(val route: String, @StringRes val title: Int) {
    object Home : HomeDestinations("home", R.string.carteira)
    object DespesasGraph : HomeDestinations("despesas_graph", R.string.despesas)
    object AddDespesa : HomeDestinations("add_despesa", R.string.adicionar_despesa)
    object Estatisticas : HomeDestinations("estatisticas", R.string.estatisticas)
    object FinancialGoals : HomeDestinations("objetivos", R.string.objetivos)
    object Perfil : HomeDestinations("profile", R.string.perfil)
}
