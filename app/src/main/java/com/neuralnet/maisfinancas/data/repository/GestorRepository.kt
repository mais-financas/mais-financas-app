package com.neuralnet.maisfinancas.data.repository

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface GestorRepository {

    fun hasLoggedIn(userId: UUID?): Flow<Boolean>
}
