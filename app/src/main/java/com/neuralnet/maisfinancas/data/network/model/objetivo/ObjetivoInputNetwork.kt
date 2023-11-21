package com.neuralnet.maisfinancas.data.network.model.objetivo

import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.model.renda.Renda
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ObjetivoInputNetwork(
    val descricao: String,
    val valor: String,
    @SerialName("gestor_id")
    val gestorId: String,
)

fun Objetivo.toNetwork(gestorId: UUID) = ObjetivoInputNetwork(
    descricao = descricao,
    valor = valor.toPlainString(),
    gestorId = gestorId.toString()
)
