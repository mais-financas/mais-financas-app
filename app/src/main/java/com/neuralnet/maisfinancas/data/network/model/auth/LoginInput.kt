package com.neuralnet.maisfinancas.data.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginInput(
    val email: String,
    val senha: String,
)
