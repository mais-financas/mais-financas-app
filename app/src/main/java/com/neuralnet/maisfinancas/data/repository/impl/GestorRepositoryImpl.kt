package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class GestorRepositoryImpl(private val gestorDao: GestorDao) : GestorRepository {

    override fun hasLoggedIn(userId: UUID?): Flow<Boolean> = gestorDao.existsById(userId)
}