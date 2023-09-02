package com.neuralnet.maisfinancas.data.model

data class Despesa(
    val nome: String,
    val categoria: String,
    val valor: Double,
    val recorrencia: String,
    val dataEmMillis: Long
)
