package com.neuralnet.maisfinancas.model

import com.neuralnet.maisfinancas.data.room.model.DespesaEntity
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

data class Despesa(
    val nome: String,
    val categoria: String,
    val valor: BigDecimal,
    val recorrenciaEmDias: Int,
    val definirLembrete: Boolean,
    val data: Calendar,
)

fun Despesa.toEntity(gestorId: UUID, categoriaId: Int) = DespesaEntity(
    nome = nome,
    valor = valor,
    data = data,
    recorrenciaEmDias = recorrenciaEmDias,
    definirLembrete = definirLembrete,
    gestorId = gestorId,
    categoriaId = categoriaId,
)