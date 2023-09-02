package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neuralnet.maisfinancas.data.model.Despesa
import com.neuralnet.maisfinancas.ui.components.items
import com.neuralnet.maisfinancas.ui.screens.AddDespesaScreen
import com.neuralnet.maisfinancas.ui.screens.HomeContent
import com.neuralnet.maisfinancas.ui.screens.despesas
import com.neuralnet.maisfinancas.ui.screens.list
import java.time.Instant
import java.time.LocalDate

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
            HomeContent(
                onAddClick = { navController.navigate(HomeDestinations.AddDespesa.route) }
            )
        }

        composable(route = HomeDestinations.AddDespesa.route) {
            var nome by remember {
                mutableStateOf("")
            }
            var valor by remember {
                mutableStateOf("")
            }
            var categoria by remember {
                mutableStateOf(list[0])
            }
            var recorrencia by remember {
                mutableStateOf(items[0])
            }
            val calendarState = rememberDatePickerState()

            AddDespesaScreen(
                nome = nome,
                onNomeChanged = { nome = it },
                valor = valor,
                onValorChanged = { valor = it },
                categoria = categoria,
                onCategoriaChanged = { categoria = it },
                recorrencia = recorrencia,
                calendarState = calendarState,
                onRecorrenciaChanged = { recorrencia = it },
                onNavigateUp = { navController.navigateUp() }, onSaveClick = {
                    despesas.add(
                        Despesa(
                            nome = nome,
                            categoria = categoria,
                            valor = valor.toDouble(),
                            recorrencia = recorrencia,
                            dataEmMillis = calendarState.selectedDateMillis ?: Instant.now().toEpochMilli()
                        )
                    )
                    navController.navigateUp()
                })
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