package com.neuralnet.maisfinancas.ui.screens.saldo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasTopAppBar
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations

@Composable
fun SaldoScreen(onAddClick: () -> Unit) {
    Scaffold(
        topBar = {
            MaisFinancasTopAppBar(
                title = stringResource(id = HomeDestinations.Saldo.title),
                canNavigateBack = true
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.adicionar_renda)) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add)
                    )
                },
                onClick = onAddClick
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), contentAlignment = Alignment.Center) {
            Text(text = HomeDestinations.Saldo.route)
        }
    }
}