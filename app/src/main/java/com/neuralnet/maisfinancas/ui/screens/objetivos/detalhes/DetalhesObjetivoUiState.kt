package com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes

import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import java.math.BigDecimal

data class DetalhesObjetivoUiState(
    val id: Int = 0,
    val descricao: String = "",
    val saldo: BigDecimal = BigDecimal.ZERO,
    val valor: BigDecimal = BigDecimal.ZERO,
)

fun DetalhesObjetivoUiState.toModel() = Objetivo(
    id = id,
    descricao = descricao,
    valor = valor
)
