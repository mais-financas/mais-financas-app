package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistrosAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.mapToModel
import com.neuralnet.maisfinancas.model.Despesa
import com.neuralnet.maisfinancas.model.toDespesaModel
import com.neuralnet.maisfinancas.model.toEntity
import com.neuralnet.maisfinancas.model.toRegistroDespesaEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class DespesaRepositoryImpl(
    private val despesaDao: DespesaDao,
    private val categoriaDao: CategoriaDao,
) : DespesaRepository {

    override fun getDespesas(gestorId: UUID?): Flow<List<Despesa>> =
        despesaDao.getDepesasByGestorId(gestorId).map(List<DespesaAndCategoria>::mapToModel)

    override suspend fun salvarDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int): Long {
        val registroDespesa = despesa.toRegistroDespesaEntity(gestorId, categoriaId)
        return despesaDao.insertRegistroDespesa(registroDespesa)
    }

    override fun getCategorias(): Flow<List<CategoriaEntity>> = categoriaDao.getCategorias()

    override suspend fun findCategoriaIdByNome(nome: String): Int =
        categoriaDao.findCategoriaIdByNome(nome)

    override fun getDespesasAndRegistros(gestorId: UUID, despesaId: Long): Flow<Despesa> {
        val despesaWithRegistro = despesaDao.getDespesaAndRegistro(gestorId, despesaId)
        return despesaWithRegistro.map(DespesaWithRegistrosAndCategoria::toDespesaModel)
    }

    override suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int) =
        despesaDao.updateDespesa(despesa.toEntity(gestorId, categoriaId))

    override suspend fun inserirRegistro(registroDespesa: RegistroDespesaEntity) =
        despesaDao.insertRegistro(registroDespesa)
}