package com.neuralnet.maisfinancas.ui.screens.auth

import com.neuralnet.maisfinancas.util.FieldValidationError

data class SignUpFormState(
    val nome: String = "",
    val nomeErrorMessage: FieldValidationError? = null,
    val email: String = "",
    val emailErrorMessage: FieldValidationError? = null,
    val senha: String = "",
    val senhaErrorMessage: FieldValidationError? = null,
    val confirmarSenha: String = "",
    val confirmarSenhaErrorMessage: FieldValidationError? = null,
    val agreeWithTermsAndConditions: Boolean = false
)
