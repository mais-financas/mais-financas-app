package com.neuralnet.maisfinancas.model.despesa

import androidx.annotation.StringRes
import com.neuralnet.maisfinancas.R

enum class Frequencia(@StringRes val descricao: Int, @StringRes val unidadeTemporalNoPlural: Int) {

    NENHUMA(R.string.recorrencia_nenhuma, R.string.recorrencia_nenhuma),
    DIARIA(R.string.recorrencia_diaria, R.string.dias),
    SEMANAL(R.string.recorrencia_semanal, R.string.semanas),
    MENSAL(R.string.recorrencia_mensal, R.string.meses),
    ANUAL(R.string.recorrencia_anual, R.string.anos),

    ;

    companion object {
        fun from(value: Int): Frequencia = Frequencia.values()
            .firstOrNull { it.descricao == value } ?: NENHUMA
    }
}
