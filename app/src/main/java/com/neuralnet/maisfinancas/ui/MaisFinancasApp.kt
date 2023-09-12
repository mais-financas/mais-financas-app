package com.neuralnet.maisfinancas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.neuralnet.maisfinancas.ui.navigation.MaisFinancasNavHost
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
            MaisFinancasNavHost(navController)
        }
    }
}
