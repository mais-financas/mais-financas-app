package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistro
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface DespesaDao {

    @Insert
    suspend fun insertDespesa(despesa: DespesaEntity): Long

    @Insert
    suspend fun insertRegistro(registroDespesaEntity: RegistroDespesaEntity): Long

    @Transaction
    @Query("SELECT * FROM despesa WHERE gestor_id = :gestorId")
    fun getDepesasByGestorId(gestorId: UUID?): Flow<List<DespesaAndCategoria>>


    @Transaction
    suspend fun insertRegistroDespesa(registroDespesa: DespesaWithRegistro): Long {
        val despesaId = insertDespesa(registroDespesa.despesa)
        insertRegistro(registroDespesa.registro.copy(despesaId = despesaId))
        return despesaId
    }

}