package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GestorDao {

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM gestor")
    fun existsById(): Flow<Boolean>

    @Insert
    suspend fun insertGestor(gestorEntity: GestorEntity)

    @Query("SELECT * from gestor WHERE gestor_id = :gestorId")
    fun getGestor(gestorId: UUID?): Flow<GestorEntity?>

}
