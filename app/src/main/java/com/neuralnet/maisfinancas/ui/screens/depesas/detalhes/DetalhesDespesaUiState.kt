package com.neuralnet.maisfinancas.ui.screens.depesas.detalhes

import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia

data class DetalhesDespesaUiState(
    val despesaId: Long = 0L,
    val nome: String = "",
    val categoria: String = "",
    val recorrencia: Recorrencia = Recorrencia(Frequencia.NENHUMA, 1),
    val definirLembrete: Boolean = false,
    val registros: List<RegistroDespesaEntity> = emptyList(),
)

fun DetalhesDespesaUiState.toModel() = Despesa(
    id = despesaId,
    nome = nome,
    categoria = categoria,
    recorrencia = recorrencia,
    definirLembrete = definirLembrete,
    valor = registros.first().valor,
    data = registros.first().data,
)

fun Despesa.toDetalhesUiState() = DetalhesDespesaUiState(
    despesaId = id,
    nome = nome,
    categoria = categoria,
    recorrencia = recorrencia,
    definirLembrete = definirLembrete,
    registros = registros
)
