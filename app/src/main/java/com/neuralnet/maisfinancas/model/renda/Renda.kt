package com.neuralnet.maisfinancas.model.renda

import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

data class Renda(
    val id: Int,
    val descricao: String,
    val valor: BigDecimal,
    val data: Calendar,
)

fun RendaEntity.toModel() = Renda(
    id = id,
    descricao = descricao,
    valor = valor,
    data = data
)

fun Renda.toEntity(gestorId: UUID) = RendaEntity(
    id = id,
    descricao = descricao,
    valor = valor,
    data = data,
    gestorId = gestorId
)
