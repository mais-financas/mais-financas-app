package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import java.time.Instant
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

val items: Map<String, Int> = mapOf(
    "Nenhuma" to 0,
    "Diária" to 1,
    "Semanal" to 7,
    "Mensal" to 30,
    "Anual" to 365,
)

@Composable
fun RecorrenciaDespesa(recorrencia: String, onRecorrenciaChanged: (String) -> Unit, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.recorrencia_despesa),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        var expanded by remember { mutableStateOf(false) }

        AppDropdown(
            label = R.string.recorrencia,
            options = items.keys.toList(),
            selectedOptionText = recorrencia,
            onSelectedOptionText = onRecorrenciaChanged,
            expanded = expanded,
            onExpandedChanged = { expanded = it }
        )
    }
}