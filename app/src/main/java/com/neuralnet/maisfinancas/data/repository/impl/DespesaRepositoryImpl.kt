package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistrosAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.mapToModel
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.asModel
import com.neuralnet.maisfinancas.model.despesa.toDespesaModel
import com.neuralnet.maisfinancas.model.despesa.toEntity
import com.neuralnet.maisfinancas.model.input.DespesaInput
import com.neuralnet.maisfinancas.util.toMonthQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

class DespesaRepositoryImpl(
    private val despesaDao: DespesaDao,
    private val categoriaDao: CategoriaDao,
) : DespesaRepository {

    override fun getDespesas(): Flow<List<Despesa>> =
        despesaDao.getDepesas().map(List<DespesaAndCategoria>::mapToModel)

    override suspend fun registrarDespesa(despesaInput: DespesaInput): Long {
        return despesaDao.cadastrarDepesaComRegistro(despesaInput)
    }

    override suspend fun registrarDespesas(despesas: List<DespesaInput>) {
        despesaDao.registrarDespesas(despesas)
    }

    override fun getCategorias(): Flow<List<Categoria>> = categoriaDao.getCategorias()
        .map(List<CategoriaEntity>::asModel)

    override suspend fun findCategoriaIdByNome(nome: String): Int =
        categoriaDao.findCategoriaIdByNome(nome)

    override fun getDespesasAndRegistros(despesaId: Long): Flow<Despesa> {
        val despesaWithRegistro = despesaDao.getDespesaAndRegistro(despesaId)
        return despesaWithRegistro.map(DespesaWithRegistrosAndCategoria::toDespesaModel)
    }

    override suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int) =
        despesaDao.updateDespesa(despesa.toEntity(gestorId, categoriaId))

    override suspend fun inserirRegistro(registroDespesa: RegistroDespesaEntity) =
        despesaDao.insertRegistro(registroDespesa)

    override fun getGastosPorMes(calendar: Calendar): Flow<BigDecimal> =
        despesaDao.getGastosDoMes(calendar.toMonthQuery())

    override fun getUltimasDespeas(): Flow<List<RegistroAndDespesa>> =
        despesaDao.getUltimasDespesas()
}
