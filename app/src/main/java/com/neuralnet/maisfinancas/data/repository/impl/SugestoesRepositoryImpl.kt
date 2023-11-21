package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.SugestoesRepository
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import kotlinx.coroutines.flow.first

class SugestoesRepositoryImpl(
    private val despesaRepository: DespesaRepository,
) : SugestoesRepository {

    override suspend fun getDespesasComuns(): Map<Categoria, List<ItemDespesa>> {
        val categorias = despesaRepository.getCategorias().first()

        return categorias.zip(SugestoesDatasource.despesas).toMap()
    }

    override fun getObjetivosComuns(): List<Objetivo> = SugestoesDatasource.objetivos

}
