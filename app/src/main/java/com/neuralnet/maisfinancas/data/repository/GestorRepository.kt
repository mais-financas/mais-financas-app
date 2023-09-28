package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GestorRepository {

    fun getGestor(userId: UUID?): Flow<GestorEntity?>

    suspend fun inserirGestor(gestorEntity: GestorEntity)
}
