package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.Despesa
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface DespesaRepository {

    fun getDespesas(gestorId: UUID?): Flow<List<Despesa>>

    suspend fun salvarDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int): Long

    fun getCategorias(): Flow<List<CategoriaEntity>>

    fun getDespesasAndRegistros(gestorId: UUID, despesaId: Long): Flow<Despesa>

    suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int)

    suspend fun findCategoriaIdByNome(nome: String): Int

    suspend fun inserirRegistro(registroDespesa: RegistroDespesaEntity)

}