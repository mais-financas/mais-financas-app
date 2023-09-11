package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.ui.screens.Screen
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesaViewModel
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesasScreen

fun NavGraphBuilder.despesasNavGraph(navController: NavController) {
    navigation(
        route = HomeDestinations.DespesasGraph.route,
        startDestination = DespesasDestinations.Overview.route,
    ) {
        composable(route = DespesasDestinations.Overview.route) {
            val viewModel = hiltViewModel<DespesaViewModel>()

            DespesasScreen(
                viewModel = viewModel,
            )
        }

        composable(route = DespesasDestinations.DepesasByCategoria.route) {
            Screen(
                route = DespesasDestinations.DepesasByCategoria.route,
                onClick = { navController.navigate(route = DespesasDestinations.DetalhesDespesa.route) })
        }

        composable(route = DespesasDestinations.DetalhesDespesa.route) {
            Screen(
                route = DespesasDestinations.DetalhesDespesa.route,
                onClick = {
                    navController.navigate(HomeDestinations.Home.route) {
                        this.popUpTo(HomeDestinations.Home.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}

sealed class DespesasDestinations(val route: String) {
    data object Overview : DespesasDestinations("despesas_overview")
    data object DepesasByCategoria : DespesasDestinations("despesas_by_categoria")
    data object DetalhesDespesa : DespesasDestinations("detalhes_despesas")
}