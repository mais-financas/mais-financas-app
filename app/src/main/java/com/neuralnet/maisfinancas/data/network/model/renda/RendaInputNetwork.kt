package com.neuralnet.maisfinancas.data.network.model.renda

import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.util.toNetworkString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RendaInputNetwork(
    val descricao: String,
    val valor: String,
    val data: String,
    @SerialName("gestor_id")
    val gestorId: String
)

fun Renda.toNetwork(gestorId: UUID) = RendaInputNetwork(
    descricao = descricao,
    valor = valor.toPlainString(),
    data = data.toNetworkString(),
    gestorId = gestorId.toString()
)