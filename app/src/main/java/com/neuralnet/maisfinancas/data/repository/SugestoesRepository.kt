package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa

interface SugestoesRepository {

    suspend fun getDespesasComuns(): Map<Categoria, List<ItemDespesa>>
}