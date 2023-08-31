package com.neuralnet.maisfinancas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.neuralnet.maisfinancas.ui.navigation.graphs.RootNavGraph
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaisFinancasApp()
        }
    }
}

@Composable
fun MaisFinancasApp(
    navController: NavHostController = rememberNavController()
) {
    MaisFinancasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RootNavGraph(navController = navController)
        }
    }
}
