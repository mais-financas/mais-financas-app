package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.input.DespesaInput
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface DespesaRepository {

    fun getDespesas(gestorId: UUID?): Flow<List<Despesa>>

    suspend fun registrarDespesa(despesaInput: DespesaInput): Long

    fun getCategorias(): Flow<List<Categoria>>

    fun getDespesasAndRegistros(gestorId: UUID, despesaId: Long): Flow<Despesa>

    suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int)

    suspend fun findCategoriaIdByNome(nome: String): Int

    suspend fun inserirRegistro(registroDespesa: RegistroDespesaEntity)

}
