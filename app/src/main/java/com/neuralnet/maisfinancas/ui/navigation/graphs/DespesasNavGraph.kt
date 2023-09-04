package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesasScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesasUiState
import com.neuralnet.maisfinancas.ui.screens.depesas.despesas

fun NavGraphBuilder.despesasNavGraph(navController: NavController) {
    navigation(
        route = HomeDestinations.DespesasGraph.route,
        startDestination = DespesasDestinations.Overview.route,
    ) {
        composable(route = DespesasDestinations.Overview.route) {
            val despesasState = remember {
                mutableStateListOf(*(despesas.toTypedArray()))
            }
            DespesasScreen(DespesasUiState(despesasState))
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
    object Overview : DespesasDestinations("despesas_overview")
    object DepesasByCategoria : DespesasDestinations("despesas_by_categoria")
    object DetalhesDespesa : DespesasDestinations("detalhes_despesas")
}