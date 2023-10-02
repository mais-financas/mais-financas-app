package com.neuralnet.maisfinancas.model

import androidx.annotation.StringRes
import com.neuralnet.maisfinancas.R

enum class Frequencia(@StringRes val descricao: Int) {
    NENHUMA(R.string.recorrencia_nenhuma),
    DIARIA(R.string.recorrencia_diaria),
    SEMANAL(R.string.recorrencia_semanal),
    MENSAL(R.string.recorrencia_mensal),
    ANUAL(R.string.recorrencia_anual),

    ;

    companion object {
        infix fun from(value: Int): Frequencia = Frequencia.values()
            .firstOrNull { it.descricao == value } ?: NENHUMA
    }
}