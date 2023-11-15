package com.neuralnet.maisfinancas.data.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignupInput(
    val nome: String,
    val email: String,
    val senha: String,
)
