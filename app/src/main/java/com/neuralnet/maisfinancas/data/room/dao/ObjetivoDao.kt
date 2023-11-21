package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.objetivo.ObjetivoEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface ObjetivoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rendaEntity: ObjetivoEntity)

    @Query("SELECT * FROM objetivo")
    fun getObjetivos(): Flow<List<ObjetivoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun sincronizar(rendas: List<ObjetivoEntity>)

    @Query("SELECT * FROM objetivo WHERE objetivo_id = :objetivoId")
    fun findById(objetivoId: Int): Flow<ObjetivoEntity>

    @Query("SELECT SUM(valor) FROM objetivo")
    fun getReservasTotais(): Flow<BigDecimal>

}