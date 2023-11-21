package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.screens.Screen
import com.neuralnet.maisfinancas.ui.screens.chatbot.ChatBotScreen
import com.neuralnet.maisfinancas.ui.screens.estatisticas.EstatisticaScreen
import com.neuralnet.maisfinancas.ui.screens.estatisticas.EstatisticaViewModel
import com.neuralnet.maisfinancas.ui.screens.home.HomeScreen
import com.neuralnet.maisfinancas.ui.screens.home.HomeViewModel
import com.neuralnet.maisfinancas.ui.screens.objetivos.ObjetivosScreen
import com.neuralnet.maisfinancas.ui.screens.objetivos.ObjetivosViewModel
import com.neuralnet.maisfinancas.ui.screens.objetivos.adicionar.AddObjetivoScreen
import com.neuralnet.maisfinancas.ui.screens.objetivos.adicionar.AddObjetivoViewModel
import com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes.DetalhesObjetivoScreen
import com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes.DetalhesObjetivoViewModel
import com.neuralnet.maisfinancas.ui.screens.rendas.AddRendaScreen
import com.neuralnet.maisfinancas.ui.screens.rendas.AddRendaViewModel
import com.neuralnet.maisfinancas.ui.screens.saldo.SaldoScreen
import java.time.Instant

const val HOME_GRAPH = "home_graph"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH,
        startDestination = HomeDestinations.Home.route,
        modifier = modifier,
    ) {

        composable(route = HomeDestinations.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate(route = HomeDestinations.AuthGraph.route)
                },
                onCardRendaMensalClick = {
//                    navController.navigate(route = HomeDestinations.Saldo.route)
                },
                onAddRendaClick = {
                    navController.navigate(route = HomeDestinations.AddRenda.route)
                }
            )
        }

        composable(route = HomeDestinations.Saldo.route) {
            SaldoScreen(onNavigateUp = { navController.navigateUp() })
        }

        composable(route = HomeDestinations.AddRenda.route) {
            val viewModel = hiltViewModel<AddRendaViewModel>()
            val calendarState = rememberDatePickerState(
                initialSelectedDateMillis = Instant.now().toEpochMilli()
            )

            AddRendaScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() },
                calendarState = calendarState,
                navigateToHome = { navController.navigate(route = HOME_GRAPH) }
            )
        }

        despesasNavGraph(navController)

        composable(route = HomeDestinations.Estatisticas.route) {
            val viewModel = hiltViewModel<EstatisticaViewModel>()
            EstatisticaScreen(viewModel = viewModel)
        }

        composable(route = HomeDestinations.Objetivos.route) {
            val viewModel = hiltViewModel<ObjetivosViewModel>()
            ObjetivosScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(HomeDestinations.AddObjetivo.route) },
                onItemClick = { objetivoId ->
                    navController.navigate(
                        route = HomeDestinations.DetalhesObjetivo.routeWithArgs(objetivoId)
                    )
                },
                onInfoClick = { },
            )
        }

        composable(route = HomeDestinations.Objetivos.route) {
            val viewModel = hiltViewModel<ObjetivosViewModel>()
            ObjetivosScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(HomeDestinations.AddObjetivo.route) },
                onItemClick = { objetivoId ->
                    navController.navigate(
                        route = HomeDestinations.DetalhesObjetivo.routeWithArgs(objetivoId)
                    )
                },
                onInfoClick = { navController.navigate(route = HomeDestinations.Chatbot.route) },
            )
        }

        composable(
            route = HomeDestinations.DetalhesObjetivo.route,
            arguments = listOf(
                navArgument("objetivo_id") { type = NavType.IntType }
            )
        ) {
            val viewModel = hiltViewModel<DetalhesObjetivoViewModel>()
            DetalhesObjetivoScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(route = HomeDestinations.AddObjetivo.route) {
            val viewModel = hiltViewModel<AddObjetivoViewModel>()
            AddObjetivoScreen(
                viewModel = viewModel,
                onNavigateUp = { navController.navigateUp() },
                navigateBack = { navController.navigateUp() },
            )
        }

        composable(route = HomeDestinations.Chatbot.route) {
            ChatBotScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(route = HomeDestinations.Perfil.route) {
            Screen(
                route = HomeDestinations.Perfil.route,
                onClick = { navController.navigate(HomeDestinations.Home.route) })
        }

        authNavGraph(navController)
    }
}

sealed class HomeDestinations(val route: String, @StringRes val title: Int) {
    data object Home : HomeDestinations("home", R.string.carteira)
    data object DespesasGraph : HomeDestinations("despesas_graph", R.string.despesas)
    data object AddRenda : HomeDestinations("add_renda", R.string.adicionar_renda)
    data object Saldo : HomeDestinations("saldo", R.string.saldo_da_conta)
    data object Estatisticas : HomeDestinations("estatisticas", R.string.estatisticas)
    data object Objetivos : HomeDestinations("objetivos", R.string.objetivos)
    data object DetalhesObjetivo :
        HomeDestinations("detalhes_objetivo/{objetivo_id}", R.string.detalhes_objetivo) {
        fun routeWithArgs(objetivoId: Int) = "detalhes_objetivo/$objetivoId"
    }

    data object AddObjetivo : HomeDestinations("add_objetivo", R.string.adicionar_objetivo)
    data object Chatbot : HomeDestinations("chatbot", R.string.chatbot)
    data object Perfil : HomeDestinations("profile", R.string.perfil)
    data object AuthGraph : HomeDestinations("auth_grpah", R.string.auth)
}
