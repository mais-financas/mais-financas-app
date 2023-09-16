package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.model.Recorrencia
import com.neuralnet.maisfinancas.model.TipoRecorrencia
import com.neuralnet.maisfinancas.util.toCalendar

data class AddDespesaUiState(
    val id: Long = 0,
    val nome: String = "",
    val valor: String = "",
    val categoria: String = "",
    val tipoRecorrencia: TipoRecorrencia = TipoRecorrencia.NENHUMA,
    val quantidadeRecorrencia: Int = 1,
    val definirLembrete: Boolean = false,
)

fun AddDespesaUiState.toDespesaModel(dataEmEpochMillis: Long) = Despesa(
    id = id,
    nome = nome,
    valor = valor.toBigDecimal(),
    categoria = categoria,
    recorrencia = Recorrencia(tipoRecorrencia, quantidadeRecorrencia),
    definirLembrete = definirLembrete,
    data = dataEmEpochMillis.toCalendar(),
)
