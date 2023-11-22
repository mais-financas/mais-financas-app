package com.neuralnet.maisfinancas.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.home.MovimentacaoRecenteItem
import com.neuralnet.maisfinancas.ui.components.home.SaldoDisponivel
import com.neuralnet.maisfinancas.ui.components.home.SaldoTotal
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.screens.LoadingScreen
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.toCalendar
import java.math.BigDecimal
import java.util.Calendar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToLogin: () -> Unit,
    onCardRendaMensalClick: () -> Unit,
    onAddRendaClick: () -> Unit,
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
                onCardClick = onCardRendaMensalClick,
                onAddRendaClick = onAddRendaClick,
                onSairClick = viewModel::sair,
            )
        }
    }
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onCardClick: () -> Unit,
    onAddRendaClick: () -> Unit,
    onSairClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.Home.title),
                canNavigateBack = false,
                actions = {
                    IconButton(onClick = onSairClick) {
                        Icon(
                            imageVector = Icons.Outlined.Logout,
                            contentDescription = stringResource(id = R.string.sair),
                            tint = Color.Red,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            item {
                SaldoTotal(
                    saldoTotal = uiState.saldoTotal,
                    onAddClick = onAddRendaClick
                )
            }

            item {
                SaldoDisponivel(
                    valor = uiState.saldoMensal,
                    calendar = Calendar.getInstance(),
                    onClick = onCardClick,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.movimentacoes_recentes),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            items(uiState.movimentacoes) { item ->
                MovimentacaoRecenteItem(item)
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(name = "pixel4", device = "id:pixel_4")
@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    MaisFinancasTheme {
        HomeScreen(
            uiState = HomeUiState(
                movimentacoes = listOf(
                    MovimentacaoItem(
                        descricao = "Água",
                        valor = BigDecimal.valueOf(130.0),
                        data = 1698449115913.toCalendar(),
                        isIncome = false
                    ),
                    MovimentacaoItem(
                        descricao = "Energia",
                        valor = BigDecimal.valueOf(121.0),
                        data = 1697449115913.toCalendar(),
                        isIncome = false
                    ),
                    MovimentacaoItem(
                        descricao = "Salário",
                        valor = BigDecimal.valueOf(2300.0),
                        data = 1696812215913.toCalendar(),
                        isIncome = true
                    ),
                    MovimentacaoItem(
                        descricao = "Jantar",
                        valor = BigDecimal.valueOf(130.0),
                        data = 1789949115913.toCalendar(),
                        isIncome = false
                    ),
                )
            ),
            onCardClick = {},
            onAddRendaClick = {},
            onSairClick = {},
        )
    }
}
