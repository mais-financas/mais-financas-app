package com.neuralnet.maisfinancas.data.network.model.despesa

import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.util.toCalendar
import kotlinx.serialization.Serializable

@Serializable
data class RegistroResponse(
    val id: Long,
    val valor: Double,
    val data: String
)

fun RegistroResponse.toEntity(despesaId: Long) = RegistroDespesaEntity(
    id = id,
    valor = valor.toBigDecimal(),
    data = data.toCalendar(),
    despesaId = despesaId
)
