package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistroAndRecorrencia
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistrosAndCategoria
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
    suspend fun insertRegistro(registroDespesaEntity: RegistroDespesaEntity)

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

    @Transaction
    @Query("SELECT * FROM despesa WHERE gestor_id =:gestorId AND despesa_id =:despesaId")
    fun getDespesaAndRegistro(
        gestorId: UUID,
        despesaId: Long,
    ): Flow<DespesaWithRegistrosAndCategoria>

    @Update
    suspend fun updateDespesa(despesa: DespesaEntity)

}