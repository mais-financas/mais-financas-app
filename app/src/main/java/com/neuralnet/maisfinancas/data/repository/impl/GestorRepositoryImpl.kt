package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class GestorRepositoryImpl(private val gestorDao: GestorDao) : GestorRepository {

    override fun getGestor(userId: UUID?): Flow<GestorEntity?> = gestorDao.getGestor(userId)

    override suspend fun inserirGestor(gestorEntity: GestorEntity) {
        gestorDao.insertGestor(gestorEntity)
    }

}