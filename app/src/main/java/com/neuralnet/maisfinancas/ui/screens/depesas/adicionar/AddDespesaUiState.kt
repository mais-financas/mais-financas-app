package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.ui.components.items
import com.neuralnet.maisfinancas.util.toCalendar

data class AddDespesaUiState(
    val nome: String = "",
    val valor: String = "",
    val categoria: String = "",
    val recorrencia: String = items.keys.first(),
    val definirLembrete: Boolean = false,
)

fun AddDespesaUiState.toDespesaModel(dataEmEpochMillis: Long) = Despesa(
    nome = nome,
    valor = valor.toBigDecimal(),
    categoria = categoria,
    recorrenciaEmDias = items[recorrencia] ?: items.values.first(),
    definirLembrete = definirLembrete,
    data = dataEmEpochMillis.toCalendar(),
)
