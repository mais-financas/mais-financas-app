package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.ui.screens.Screen
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesaViewModel
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesasScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.detalhes.DetalhesDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.detalhes.DetalhesDespesaViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.despesasNavGraph(navController: NavController) {
    navigation(
        route = HomeDestinations.DespesasGraph.route,
        startDestination = DespesasDestinations.Overview.route,
    ) {
        composable(
            route = DespesasDestinations.Overview.route
        ) {
            val viewModel = hiltViewModel<DespesaViewModel>()

            DespesasScreen(
                viewModel = viewModel,
                onDetailsClick = { selectedDespesaId ->
                    navController.navigate(
                        DespesasDestinations.DetalhesDespesa.routeWithArgs(
                            selectedDespesaId
                        )
                    )
                }
            )
        }

        composable(
            route = DespesasDestinations.DetalhesDespesa.route,
            arguments = listOf(
                navArgument("despesa_id") { type = NavType.LongType }
            )
        ) {
            val viewModel = hiltViewModel<DetalhesDespesaViewModel>()
            DetalhesDespesaScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}

sealed class DespesasDestinations(val route: String) {
    data object Overview : DespesasDestinations("despesas_overview")
    data object DepesasByCategoria : DespesasDestinations("despesas_by_categoria")
    data object DetalhesDespesa : DespesasDestinations("detalhes_despesa/{despesa_id}") {
        fun routeWithArgs(despesaId: Long) = "detalhes_despesa/$despesaId"
    }
}