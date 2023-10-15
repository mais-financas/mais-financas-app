package com.neuralnet.maisfinancas.ui.screens.depesas

import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa

data class DespesasUiState(
    val despesas: List<Despesa> = listOf(),
    val categorias: List<CategoriaEntity> = listOf()
)
