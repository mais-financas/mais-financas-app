package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GestorRepository {

    fun hasLoggedIn(userId: UUID?): Flow<Boolean>

    suspend fun inserirGestor(gestorEntity: GestorEntity)
}
