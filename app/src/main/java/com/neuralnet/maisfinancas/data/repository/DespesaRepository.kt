package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.input.DespesaInput
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

interface DespesaRepository {

    fun getDespesas(): Flow<List<Despesa>>

    suspend fun registrarDespesa(despesaInput: DespesaInput): Long

    suspend fun registrarDespesas(despesas: List<DespesaInput>)

    fun getCategorias(): Flow<List<Categoria>>

    fun getDespesasAndRegistros(despesaId: Long): Flow<Despesa>

    suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int)

    suspend fun findCategoriaIdByNome(nome: String): Int

    suspend fun inserirRegistro(registroDespesa: RegistroDespesaEntity)

    fun getGastosPorMes(calendar: Calendar): Flow<BigDecimal>

    fun getUltimasDespeas(): Flow<List<RegistroAndDespesa>>

    fun getGastoTotal(): Flow<BigDecimal>

}
