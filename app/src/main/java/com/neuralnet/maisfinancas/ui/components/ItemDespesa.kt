package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.util.formattedDate
import com.neuralnet.maisfinancas.util.toReal
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDespesa(
    despesa: Despesa,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(modifier = modifier.fillMaxWidth(), onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = despesa.nome,
                    fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                    fontWeight = FontWeight.Medium
                )
                Text(text = despesa.data.formattedDate(), fontWeight = FontWeight.Light)
            }

            Text(
                text = despesa.valor.toReal(),
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                fontWeight = FontWeight.Medium
            )

        }
    }
}

fun Long.getDate(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        sdf.timeZone = TimeZone.getTimeZone("Etc/UTC");
        sdf.format(Date(this))
    } catch (e: Exception) {
        e.toString()
    }
}