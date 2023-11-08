package com.neuralnet.maisfinancas.ui.screens.rendas

import com.neuralnet.maisfinancas.util.FieldValidationError

data class AddRendaUiState(
    val descricao: String = "",
    val descricaoErrorField: FieldValidationError? = null,
    val valor: String = "",
    val valorErrorField: FieldValidationError? = null,
)
