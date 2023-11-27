package com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes

import com.neuralnet.maisfinancas.util.FieldValidationError
import java.math.BigDecimal

data class UpdateObjetivoUiState(
    val valor: String = "",
    val saldo: BigDecimal = BigDecimal.ZERO,
    val valorErrorMessage: FieldValidationError? = null,
) {
    fun isFormValid() = valor.toDoubleOrNull() != null &&
            saldo > (valor.toBigDecimalOrNull() ?: BigDecimal.ZERO)
}
