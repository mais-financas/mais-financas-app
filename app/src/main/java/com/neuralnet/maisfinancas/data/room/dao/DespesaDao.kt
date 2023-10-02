package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistroAndRecorrencia
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.Frequencia
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface DespesaDao {

    @Insert
    suspend fun insertDespesa(despesa: DespesaEntity): Long

    @Insert
    suspend fun insertRegistro(registroDespesaEntity: RegistroDespesaEntity): Long

    @Insert
    suspend fun insertRecorrencia(recorrencia: RecorrenciaDespesaEntity)

    @Transaction
    @Query("SELECT * FROM despesa WHERE gestor_id = :gestorId")
    fun getDepesasByGestorId(gestorId: UUID?): Flow<List<DespesaAndCategoria>>


    @Transaction
    suspend fun insertRegistroDespesa(registroDespesa: DespesaWithRegistroAndRecorrencia): Long {
        val despesaId = insertDespesa(registroDespesa.despesa)
        insertRegistro(registroDespesa.registro.copy(despesaId = despesaId))

        registroDespesa.recorrencia?.let {
            if (it.frequencia != Frequencia.NENHUMA) {
                insertRecorrencia(it.copy(despesaId = despesaId))
            }
        }

        return despesaId
    }

}