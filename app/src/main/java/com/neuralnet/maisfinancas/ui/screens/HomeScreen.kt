package com.neuralnet.maisfinancas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.ObjetivosOverview
import com.neuralnet.maisfinancas.ui.components.OrcamentoOverview
import com.neuralnet.maisfinancas.ui.components.TransferenciasSemana
import com.neuralnet.maisfinancas.ui.navigation.AppBottomBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeNavGraph
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            if (HomeDestinations.Home.route == currentDestination) {
                FloatingActionButton(onClick = { navController.navigate(HomeDestinations.Home.route) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { AppBottomBar(navController = navController) }
    ) { paddingValues ->
        HomeNavGraph(navController, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun HomeContent() {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OrcamentoOverview()

        ObjetivosOverview()

        TransferenciasSemana()
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaisFinancasTheme {
        HomeContent()
    }
}
