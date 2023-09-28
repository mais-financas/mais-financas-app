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
import com.neuralnet.maisfinancas.model.TipoRecorrencia

@Composable
fun RecorrenciaDespesa(
    recorrencia: TipoRecorrencia,
    onRecorrenciaChanged: (TipoRecorrencia) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.recorrencia_despesa),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        var expanded by remember { mutableStateOf(false) }

        RecorrenciaDespesaDropdown(
            label = R.string.recorrencia,
            options = TipoRecorrencia.values(),
            selectedOptionText = recorrencia,
            onSelectedOptionText = onRecorrenciaChanged,
            expanded = expanded,
            onExpandedChanged = { expanded = it }
        )
    }
}