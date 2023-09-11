package com.neuralnet.maisfinancas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.neuralnet.maisfinancas.ui.navigation.FinancasNavigationBar
import com.neuralnet.maisfinancas.ui.navigation.allowedRoutes
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeNavGraph
import com.neuralnet.maisfinancas.ui.navigation.screens
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun MaisFinancasApp(
    navController: NavHostController = rememberNavController(),
) {
    MaisFinancasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                bottomBar = {
                    val showBottomBar =
                        navController.currentBackStackEntryAsState()
                            .value?.destination?.route in allowedRoutes

                    if (showBottomBar) {
                        FinancasNavigationBar(navController = navController)
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
    }
}
