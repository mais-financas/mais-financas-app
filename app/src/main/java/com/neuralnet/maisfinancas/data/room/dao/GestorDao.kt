package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GestorDao {

    @Insert
    suspend fun insertGestor(gestorEntity: GestorEntity)

    @Query("SELECT * from gestor WHERE gestor_id = :gestorId")
    fun getGestor(gestorId: UUID): Flow<GestorEntity>

}
