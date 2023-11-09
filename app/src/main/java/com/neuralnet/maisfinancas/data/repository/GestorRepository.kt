package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GestorRepository {

    fun getGestor(userId: UUID?): Flow<GestorEntity?>

    suspend fun inserirGestor(gestorEntity: GestorEntity)

    fun getUltimasMovimentacoes(): Flow<List<MovimentacaoItem>>
}
