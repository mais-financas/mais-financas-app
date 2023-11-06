package com.neuralnet.maisfinancas.ui.screens.auth

import com.neuralnet.maisfinancas.util.FieldValidationError

data class LoginFormState(
    val email: String = "",
    val emailErrorMessage: FieldValidationError? = null,
    val senha: String = "",
    val senhaErrorMessage: FieldValidationError? = null,
)
