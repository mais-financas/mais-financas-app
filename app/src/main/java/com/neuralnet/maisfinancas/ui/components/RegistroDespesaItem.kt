package com.neuralnet.maisfinancas.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme
import com.neuralnet.maisfinancas.util.formattedDate
import com.neuralnet.maisfinancas.util.toReal
import java.math.BigDecimal
import java.util.Calendar

@Composable
fun RegistroDespesaItem(regisroDespesa: RegistroDespesaEntity, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = regisroDespesa.data.formattedDate(), modifier = Modifier.weight(1f))

        Text(text = regisroDespesa.valor.toReal())
    }
}

@Preview
@Composable
fun RegistroDespesaItemPreview() {
    MaisFinancasTheme {
        RegistroDespesaItem(
            RegistroDespesaEntity(
                id = 0,
                valor = BigDecimal.ZERO,
                data = Calendar.getInstance(),
                despesaId = 1
            )
        )
    }
}
