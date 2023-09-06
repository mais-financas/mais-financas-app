package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import com.neuralnet.maisfinancas.ui.components.items

data class AddDespesaUiState(
    val nome: String = "",
    val valor: String = "",
    val categoria: String = list[0],
    val recorrencia: String = items[0],
    val definirLembrete: Boolean = false,
)
