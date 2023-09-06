package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.model.Despesa
import kotlinx.coroutines.flow.Flow

interface DespesaRepository {

    fun getDespesas(): Flow<List<Despesa>>

    suspend fun salvarDespesa(despesa: Despesa)
}