package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistrosAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.input.DespesaInput
import com.neuralnet.maisfinancas.model.input.toDespesaEntity
import com.neuralnet.maisfinancas.model.input.toRegistroEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface DespesaDao {

    @Insert
    suspend fun insertDespesa(despesa: DespesaEntity): Long

    @Insert
    suspend fun insertRegistro(registroDespesaEntity: RegistroDespesaEntity)

    @Insert
    suspend fun insertRecorrencia(recorrencia: RecorrenciaDespesaEntity)

    @Transaction
    @Query("SELECT * FROM despesa")
    fun getDepesas(): Flow<List<DespesaAndCategoria>>

    @Transaction
    suspend fun cadastrarDepesaComRegistro(despesaInput: DespesaInput): Long {
        val despesaId = insertDespesa(despesaInput.toDespesaEntity())
        val registroDespesa = despesaInput.toRegistroEntity(despesaId)

        insertRegistro(registroDespesa)

        despesaInput.despesa.recorrencia.run {
            if (frequencia != Frequencia.NENHUMA) {
                val recorrenciaDespesaEntity = RecorrenciaDespesaEntity(
                    frequencia = frequencia,
                    quantidade = quantidade,
                    despesaId = despesaId
                )
                insertRecorrencia(recorrenciaDespesaEntity)
            }
        }

        return despesaId
    }

    @Transaction
    @Query("SELECT * FROM despesa WHERE despesa_id =:despesaId")
    fun getDespesaAndRegistro(
        despesaId: Long,
    ): Flow<DespesaWithRegistrosAndCategoria>

    @Update
    suspend fun updateDespesa(despesa: DespesaEntity)

    @Query(
        "SELECT SUM(valor) FROM registro_despesa " +
                "WHERE strftime('%Y-%m', datetime(registro_despesa.data / 1000, 'unixepoch')) = :mes"
    )
    fun getGastosDoMes(mes: String): Flow<BigDecimal>

    @Transaction
    @Query("SELECT * FROM registro_despesa ORDER BY data DESC LIMIT 5")
    fun getUltimasDespesas(): Flow<List<RegistroAndDespesa>>

    @Transaction
    suspend fun registrarDespesas(despesas: List<DespesaInput>) {
        despesas.forEach { despesaInput ->
            println(despesaInput)
            cadastrarDepesaComRegistro(despesaInput)
        }
    }

    @Query("SELECT SUM(valor) FROM registro_despesa")
    fun getGastoTotal(): Flow<BigDecimal>

}
