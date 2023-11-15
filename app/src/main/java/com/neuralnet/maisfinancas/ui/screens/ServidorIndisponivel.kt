package com.neuralnet.maisfinancas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.formatTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ServidorIndisponivel(onFinish: () -> Unit) {
    var time by rememberSaveable { mutableIntStateOf(150) }

    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(time) {
        val job = coroutineScope.launch {
            while (time > 0) {
                delay(1000)
                time--
            }
            onFinish()
        }
        onDispose {
            job.cancel()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.servidor_iniciando),
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = formatTime(time),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun ServidorIndisponivelScreenPreview() {
    MaisFinancasTheme {
        ServidorIndisponivel(onFinish = {})
    }
}
