package com.neuralnet.maisfinancas.ui.screens.home

import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.util.formattedDate
import java.math.BigDecimal
import java.util.Calendar

data class MovimentacaoItem(
    val descricao: String,
    val valor: BigDecimal,
    val data: Calendar,
    val isIncome: Boolean,
) {
    override fun toString(): String {
        return "Movimentação(descricao=$descricao, valor=$valor, data=${data.formattedDate()}, isIncome=$isIncome"
    }
}

fun List<RegistroAndDespesa>.toMovimentacoesDespesa(): List<MovimentacaoItem> = map { registroDespesa ->
    MovimentacaoItem(
        descricao = registroDespesa.despesa.nome,
        valor = registroDespesa.registro.valor,
        data = registroDespesa.registro.data,
        isIncome = false
    )
}

fun List<Renda>.toMovimentacoesRenda(): List<MovimentacaoItem> = map { renda ->
    MovimentacaoItem(
        descricao = renda.descricao,
        valor = renda.valor,
        data = renda.data,
        isIncome = true
    )
}
