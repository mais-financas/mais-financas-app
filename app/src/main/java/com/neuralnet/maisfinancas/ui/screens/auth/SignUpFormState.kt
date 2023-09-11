package com.neuralnet.maisfinancas.ui.screens.auth

data class SignUpFormState(
    val nome: String = "",
    val nomeErrorMessage: String? = null,
    val email: String = "",
    val emailErrorMessage: String? = null,
    val senha: String = "",
    val senhaErrorMessage: String? = null,
    val confirmarSenha: String = "",
    val confirmarSenhaErrorMessage: String? = null,
    val agreeWithTermsAndConditions: Boolean = false
)
