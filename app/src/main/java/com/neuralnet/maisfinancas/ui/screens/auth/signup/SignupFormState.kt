package com.neuralnet.maisfinancas.ui.screens.auth.signup

import com.neuralnet.maisfinancas.data.network.model.auth.SignupInput
import com.neuralnet.maisfinancas.util.FieldValidationError

data class SignupFormState(
    val nome: String = "",
    val nomeErrorMessage: FieldValidationError? = null,
    val email: String = "",
    val emailErrorMessage: FieldValidationError? = null,
    val senha: String = "",
    val senhaErrorMessage: FieldValidationError? = null,
    val confirmarSenha: String = "",
    val confirmarSenhaErrorMessage: FieldValidationError? = null,
    val agreeWithTermsAndConditions: Boolean = false,
    val agreeWithTermsErrorMessage: FieldValidationError? = null,
) {
    fun isFormValid(): Boolean = nomeErrorMessage == null &&
            emailErrorMessage == null && senhaErrorMessage == null &&
            confirmarSenhaErrorMessage == null && agreeWithTermsAndConditions
}

fun SignupFormState.toSignupInput() = SignupInput(
    nome = nome,
    email = email,
    senha = senha
)
