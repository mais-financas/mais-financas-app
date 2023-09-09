package com.neuralnet.maisfinancas.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.ObjetivosOverview
import com.neuralnet.maisfinancas.ui.components.OrcamentoOverview
import com.neuralnet.maisfinancas.ui.components.TransferenciasSemana
import com.neuralnet.maisfinancas.ui.navigation.FinancasNavigationBar
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeNavGraph
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun HomeRoute(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { FinancasNavigationBar(navController = navController) }
    ) { paddingValues ->
        HomeNavGraph(navController, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
) {
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    when (authState) {
        AuthState.Loading -> {
            LoadingScreen()
        }

        AuthState.NotLoggedIn -> {
            LaunchedEffect(key1 = Unit) {
                onNavigateToLogin()
            }
        }

        AuthState.LoggedIn -> {
            val homeUiState = viewModel.uiState.collectAsStateWithLifecycle()

            HomeScreen(
                uiState = homeUiState.value,
                onAddClick = onAddClick
            )
        }
    }
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = HomeDestinations.Home.title,
                canNavigateBack = false,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(paddingValues), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OrcamentoOverview(
                gasto = uiState.gastoMensal,
                saldo = uiState.saldoMensal,
            )

            ObjetivosOverview()

            TransferenciasSemana(
                rendaSemanal = uiState.rendaSemanal,
                despesasSemanais = uiState.despesasSemanais,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaisFinancasTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onAddClick = {},
        )
    }
}