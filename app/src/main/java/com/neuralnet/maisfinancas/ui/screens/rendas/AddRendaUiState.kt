package com.neuralnet.maisfinancas.ui.screens.rendas

import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.toCalendar

data class AddRendaUiState(
    val descricao: String = "",
    val descricaoErrorField: FieldValidationError? = null,
    val valor: String = "",
    val valorErrorField: FieldValidationError? = null,
) {
    fun isFormValid(): Boolean = descricaoErrorField == null && valorErrorField == null
}

fun AddRendaUiState.toModel(dataEmMillis: Long) = Renda(
    id = 0,
    descricao,
    valor.toBigDecimal(),
    dataEmMillis.toCalendar()
)