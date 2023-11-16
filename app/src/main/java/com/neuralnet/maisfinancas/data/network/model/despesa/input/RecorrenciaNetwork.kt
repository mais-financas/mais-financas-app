package com.neuralnet.maisfinancas.data.network.model.despesa.input

import com.neuralnet.maisfinancas.model.despesa.Frequencia
import kotlinx.serialization.Serializable

@Serializable
class RecorrenciaNetwork(
    val frequencia: Frequencia,
    val quantidade: Int
)
