package com.neuralnet.maisfinancas.ui.screens.objetivos.adicionar

import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.util.FieldValidationError
import java.math.BigDecimal

data class AddObjetivoUiState(
    val descricao: String = "",
    val saldo: BigDecimal = BigDecimal.ZERO,
    val valor: String = "",
    val valorErrorMessage: FieldValidationError? = null
) {
    fun isFormValid() = valor.toBigDecimalOrNull() != null &&
            saldo > (valor.toBigDecimalOrNull() ?: BigDecimal.ZERO)

}

fun AddObjetivoUiState.toModel() = Objetivo(
    descricao = descricao,
    valor = valor.toBigDecimal()
)
