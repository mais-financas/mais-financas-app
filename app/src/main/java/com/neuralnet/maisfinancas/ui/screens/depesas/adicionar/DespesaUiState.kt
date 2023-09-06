package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.ui.components.items

data class DespesaUiState(
    val nome: String = "",
    val valor: String = "",
    val categoria: String = list[0],
    val recorrencia: String = items[0],
    val definirLembrete: Boolean = false,
)

fun DespesaUiState.toDespesaModel(dataEmEpochMillis: Long) = Despesa(
    nome = nome,
    valor = valor.toDouble(),
    categoria = categoria,
    recorrencia = recorrencia,
    definirLembrete = definirLembrete,
    dataEmEpochMillis = dataEmEpochMillis,
)