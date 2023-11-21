package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ObjetivoRepository {

    suspend fun insertObjetivo(objetivo: Objetivo, gestorId: UUID)

    fun getObjetivos(): Flow<List<Objetivo>>

    suspend fun fetchObjetivos(gestorId: UUID)

    suspend fun inserirObjetivos(gestorId: UUID)

    fun findById(objetivoId: Int): Flow<Objetivo>

    suspend fun atualizarObjetivo(objetivo: Objetivo, gestorId: UUID)

}