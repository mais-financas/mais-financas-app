package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.relationships.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.relationships.mapToModel
import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class DespesaRepositoryImpl(
    private val despesaDao: DespesaDao,
    private val categoriaDao: CategoriaDao,
) : DespesaRepository {

    override fun getDespesas(gestorId: UUID): Flow<List<Despesa>> =
        despesaDao.getDepesasByGestorId(gestorId).map(List<DespesaAndCategoria>::mapToModel)

    override suspend fun salvarDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int) {
        val despesaEntity = despesa.toEntity(gestorId, categoriaId)
        despesaDao.insertDespesa(despesaEntity)
    }

    override fun getCategorias(): Flow<List<CategoriaEntity>> = categoriaDao.getCategorias()
}