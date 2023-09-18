package com.neuralnet.maisfinancas.util

import androidx.annotation.StringRes
import com.neuralnet.maisfinancas.R

enum class FieldValidationError(@StringRes val message: Int) {

    VAZIO(R.string.message_campo_vazio),
    NUMERO_INVALIDO(R.string.message_numero_invalido),
}