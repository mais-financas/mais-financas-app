package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface RendaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rendaEntity: RendaEntity)

    @Query("SELECT SUM(valor) FROM renda " +
            "WHERE strftime('%Y-%m', datetime(renda.data / 1000, 'unixepoch')) = :mes ")
    fun getRendasPorMes(mes: String): Flow<BigDecimal>

    @Query("SELECT * FROM renda ORDER BY data DESC LIMIT 5")
    fun getUltimasRendas(): Flow<List<RendaEntity>>

    @Query("SELECT SUM(valor) FROM renda")
    fun getRendaTotal(): Flow<BigDecimal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun sincronizar(rendas: List<RendaEntity>)

}