package com.neuralnet.maisfinancas.ui.screens.auth.login

import com.neuralnet.maisfinancas.data.network.model.auth.LoginInput
import com.neuralnet.maisfinancas.util.FieldValidationError

data class LoginFormState(
    val email: String = "",
    val senha: String = "",
    val emailErrorMessage: FieldValidationError? = null,
    val senhaErrorMessage: FieldValidationError? = null,
) {
    fun isFormValid(): Boolean = emailErrorMessage == null && senhaErrorMessage == null
}

fun LoginFormState.toLoginInput() = LoginInput(
    email = email,
    senha = senha
)
