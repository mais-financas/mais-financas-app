package com.neuralnet.maisfinancas.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeNavGraph

@Composable
fun MaisFinancasNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val showBottomBar = navBackStackEntry?.destination?.route in allowedRoutes

            if (showBottomBar) {
                FinancasNavigationBar(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry
                )
            }
        }
    )
    { paddingValues ->
        HomeNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}