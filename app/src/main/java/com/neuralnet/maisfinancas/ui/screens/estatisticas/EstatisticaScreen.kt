package com.neuralnet.maisfinancas.ui.screens.estatisticas

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.estatisticas.PeriodoSelector
import com.neuralnet.maisfinancas.ui.components.estatisticas.PieChart
import com.neuralnet.maisfinancas.ui.components.estatisticas.PieChartDetails
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun EstatisticaScreen(viewModel: EstatisticaViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    EstatisticaScreen(
        uiState = uiState.value,
        onPreviousClick = viewModel::previousMonth,
        onNextClick = viewModel::nextMonth,
    )
}

@Composable
fun EstatisticaScreen(
    uiState: EstatistacaUiState,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.Estatisticas.title),
                canNavigateBack = false,
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            item {
                PeriodoSelector(
                    data = uiState.periodo,
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (uiState.dados.isEmpty()) {
                        Text(text = stringResource(R.string.lista_despesas_vazia))
                    } else {
                        PieChart(dados = uiState.dados)
                    }
                }

            }
            items(uiState.dados) { item ->
                PieChartDetails(item = item, modifier = Modifier.padding(horizontal = 8.dp))
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
fun EstatisticasScreenPreview() {
    MaisFinancasTheme {
        EstatisticaScreen(
            uiState = EstatistacaUiState(),
            onPreviousClick = { },
            onNextClick = { }
        )
    }
}
