package com.neuralnet.maisfinancas.data.network.model.objetivo

import com.neuralnet.maisfinancas.data.room.model.objetivo.ObjetivoEntity
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ObjetivoResponse(
    val id: Int,
    val descricao: String,
    val valor: Double,
)

fun ObjetivoResponse.toEntity(gestorId: UUID) = ObjetivoEntity(
    id = id,
    descricao = descricao,
    valor = valor.toBigDecimal(),
    gestorId = gestorId
)
