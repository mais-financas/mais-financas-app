package com.neuralnet.maisfinancas.model

import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity

data class Recorrencia(
    val frequencia: Frequencia,

    val quantidade: Int = 1
)

fun RecorrenciaDespesaEntity.toModel() = Recorrencia(
    frequencia = frequencia,
    quantidade = quantidade,
)