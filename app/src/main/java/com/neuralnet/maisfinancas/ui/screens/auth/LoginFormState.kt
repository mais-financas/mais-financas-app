package com.neuralnet.maisfinancas.ui.screens.auth

data class LoginFormState(
    val email: String = "",
    val emailErrorMessage: String? = null,
    val senha: String = "",
    val senhaErrorMessage: String? = null,
)
