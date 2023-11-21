package com.neuralnet.maisfinancas.model.objetivo

import com.neuralnet.maisfinancas.data.room.model.objetivo.ObjetivoEntity
import java.math.BigDecimal
import java.util.UUID

data class Objetivo(
    val id: Int = 0,
    val descricao: String,
    val valor: BigDecimal,
)

fun ObjetivoEntity.toModel() = Objetivo(
    id = id,
    descricao = descricao,
    valor = valor,
)

fun List<ObjetivoEntity>.toModel(): List<Objetivo> = map(ObjetivoEntity::toModel)

fun Objetivo.toEntity(gestorId: UUID) = ObjetivoEntity(
    id = id,
    descricao = descricao,
    valor = valor,
    gestorId = gestorId
)

