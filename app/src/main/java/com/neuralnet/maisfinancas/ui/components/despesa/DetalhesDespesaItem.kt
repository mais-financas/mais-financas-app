package com.neuralnet.maisfinancas.ui.components.despesa

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.ui.theme.MaisFinancasTheme

@Composable
fun DetalhesDespesaItem(
    nome: String,
    categoria: String,
    definirLembrete: Boolean,
    recorrencia: Recorrencia,
    modifier: Modifier = Modifier,
    onCheckChange: ((Boolean) -> Unit),
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = nome,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall,
        )

        Text(text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) {
                append(stringResource(R.string.categoria_detalhe))
            }

            append(categoria)
        }, modifier = Modifier.padding(vertical = 12.dp))

        AnimatedVisibility(visible = recorrencia.frequencia != Frequencia.NENHUMA) {
            Text(
                text =
                pluralStringResource(
                    id = R.plurals.recorrencia_despesa,
                    count = recorrencia.quantidade,
                    if (recorrencia.quantidade == 1) {
                        stringResource(id = recorrencia.frequencia.descricao)
                            .replaceFirstChar(Char::lowercase)
                    } else stringResource(id = recorrencia.frequencia.unidadeTemporalNoPlural)
                )
            )
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.definir_lembrete), Modifier.weight(1f))

            Switch(
                checked = definirLembrete,
                onCheckedChange = onCheckChange,
                enabled = recorrencia.frequencia != Frequencia.NENHUMA
            )
        }
    }
}

@Preview
@Composable
fun DetalhesDespesaItemPreview() {
    MaisFinancasTheme {
        DetalhesDespesaItem(
            nome = "Energia",
            categoria = "Essenciais",
            definirLembrete = true,
            recorrencia = Recorrencia(Frequencia.MENSAL, 1),
            onCheckChange = {}
        )
    }
}
