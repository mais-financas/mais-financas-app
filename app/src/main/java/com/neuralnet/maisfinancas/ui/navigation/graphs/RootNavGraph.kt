package com.neuralnet.maisfinancas.ui.navigation.graphs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neuralnet.maisfinancas.ui.screens.home.HomeScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = AppGraph.ROOT,
        startDestination = AppGraph.HOME
    ) {
        authNavGraph(navController)

        composable(route = AppGraph.HOME) {
            HomeScreen()
        }
    }
}


@Composable
fun Screen(route: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = route.uppercase(),
            modifier = Modifier.clickable { onClick() }
        )
    }
}

object AppGraph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}