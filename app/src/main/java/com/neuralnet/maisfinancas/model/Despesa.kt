package com.neuralnet.maisfinancas.model

data class Despesa(
    val nome: String,
    val categoria: String,
    val valor: Double,
    val recorrencia: String,
    val definirLembrete: Boolean,
    val dataEmEpochMillis: Long,
)
