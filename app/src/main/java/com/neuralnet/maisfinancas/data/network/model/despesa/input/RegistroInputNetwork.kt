package com.neuralnet.maisfinancas.data.network.model.despesa.input

import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import com.neuralnet.maisfinancas.util.toNetworkString
import kotlinx.serialization.Serializable

@Serializable
data class RegistroInputNetwork(
    val valor: Double,
    val data: String
)

fun RegistroDespesa.toNetwork() = RegistroInputNetwork(
    valor = valor.toDouble(),
    data = data.toNetworkString()
)
