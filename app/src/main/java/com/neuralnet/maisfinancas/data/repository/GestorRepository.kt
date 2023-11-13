package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import kotlinx.coroutines.flow.Flow

interface GestorRepository {

    fun getGestor(): Flow<GestorEntity?>

    suspend fun inserirGestor(gestorEntity: GestorEntity)

    fun getUltimasMovimentacoes(): Flow<List<MovimentacaoItem>>

}
