package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesaViewModel
import com.neuralnet.maisfinancas.ui.screens.depesas.DespesasScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.adicionar.AddDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.adicionar.AddDespesaViewModel
import com.neuralnet.maisfinancas.ui.screens.depesas.detalhes.DetalhesDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.depesas.detalhes.DetalhesDespesaViewModel
import java.time.Instant

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
                onAddClick = { navController.navigate(route = DespesasDestinations.AddDespesa.route) },
                onDetailsClick = { selectedDespesaId ->
                    navController.navigate(
                        DespesasDestinations.DetalhesDespesa.routeWithArgs(
                            selectedDespesaId
                        )
                    )
                }
            )
        }

        composable(route = DespesasDestinations.AddDespesa.route) {
            val viewModel = hiltViewModel<AddDespesaViewModel>()
            val calendarState = rememberDatePickerState(
                initialSelectedDateMillis = Instant.now().toEpochMilli()
            )

            AddDespesaScreen(
                viewModel = viewModel,
                calendarState = calendarState,
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.navigateUp() }
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
    data object AddDespesa : HomeDestinations("add_despesa", R.string.adicionar_despesa)
    data object DetalhesDespesa : DespesasDestinations("detalhes_despesa/{despesa_id}") {
        fun routeWithArgs(despesaId: Long) = "detalhes_despesa/$despesaId"
    }
}