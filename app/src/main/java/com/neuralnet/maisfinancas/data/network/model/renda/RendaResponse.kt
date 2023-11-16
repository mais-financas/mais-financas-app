package com.neuralnet.maisfinancas.data.network.model.renda

import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import com.neuralnet.maisfinancas.util.toCalendar
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RendaResponse(
    val id: Int,
    val descricao: String,
    val valor: Double,
    val data: String,
)

fun RendaResponse.toEntity(gestorId: UUID) = RendaEntity(
    id = id,
    descricao = descricao,
    valor = valor.toBigDecimal(),
    data = data.toCalendar(),
    gestorId = gestorId
)
