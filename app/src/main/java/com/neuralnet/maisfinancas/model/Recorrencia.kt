package com.neuralnet.maisfinancas.model

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import com.neuralnet.maisfinancas.R

data class Recorrencia(
    @ColumnInfo(name = "tipo_recorrencia")
    val tipoRecorrencia: TipoRecorrencia,

    @ColumnInfo(name = "quantidade_recorrencia")
    val quantidade: Int = 1
)

enum class TipoRecorrencia(@StringRes val descricao: Int) {
    NENHUMA(R.string.recorrencia_nenhuma),
    DIARIA(R.string.recorrencia_diaria),
    SEMANAL(R.string.recorrencia_semanal),
    MENSAL(R.string.recorrencia_mensal),
    ANUAL(R.string.recorrencia_anual),

    ;

    companion object {
        infix fun from(value: Int): TipoRecorrencia = TipoRecorrencia.values().firstOrNull { it.descricao == value } ?: NENHUMA
    }
}
