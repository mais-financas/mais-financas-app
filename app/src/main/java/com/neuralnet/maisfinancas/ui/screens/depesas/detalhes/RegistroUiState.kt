package com.neuralnet.maisfinancas.ui.screens.depesas.detalhes

import com.neuralnet.maisfinancas.util.FieldValidationError

data class RegistroUiState(
    val valor: String = "",
    val valorErrorField: FieldValidationError? = null
) {
    fun isRegistroValid(): Boolean = valorErrorField == null
}
