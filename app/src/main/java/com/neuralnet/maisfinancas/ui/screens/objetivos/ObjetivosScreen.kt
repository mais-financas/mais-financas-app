package com.neuralnet.maisfinancas.ui.screens.objetivos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.components.objetivos.CardObjetivo
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun ObjetivosScreen(
    viewModel: ObjetivosViewModel,
    onAddClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onInfoClick: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ObjetivosScreen(uiState.value, onAddClick = onAddClick, onItemClick = onItemClick, onInfoClick = onInfoClick)
}

@Composable
fun ObjetivosScreen(
    uiState: ObjetivosUiState,
    onAddClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onInfoClick: () -> Unit
) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.Objetivos.title),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onInfoClick, containerColor = Color(0xFF6B89BD)) {
                Icon(
                    imageVector = Icons.Default.QuestionMark,
                    contentDescription = stringResource(R.string.saber_mais),
                    tint = Color.White,
                )
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.comece_a_guardar),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = onAddClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.adicionar_objetivo),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(uiState.objetivos) { objetivo ->
                    CardObjetivo(objetivo = objetivo, onCardClick = onItemClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ObjetivosScreenPreview() {
    MaisFinancasTheme {
        ObjetivosScreen(
            uiState = ObjetivosUiState(),
            onAddClick = {},
            onItemClick = {},
            onInfoClick = {},
        )
    }
}
