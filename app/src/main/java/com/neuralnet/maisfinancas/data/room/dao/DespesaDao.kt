package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.relationships.DespesaAndCategoria
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface DespesaDao {

    @Insert
    suspend fun insertDespesa(despesa: DespesaEntity): Long

    @Query("SELECT * FROM despesa WHERE gestor_id = :gestorId")
    fun getDepesasByGestorId(gestorId: UUID): Flow<List<DespesaAndCategoria>>

}