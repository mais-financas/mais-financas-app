package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import com.neuralnet.maisfinancas.data.network.model.despesa.DespesaResponse
import com.neuralnet.maisfinancas.data.network.model.despesa.input.toNetwork
import com.neuralnet.maisfinancas.data.network.model.despesa.toDespesaInput
import com.neuralnet.maisfinancas.data.network.model.despesa.toEntity
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistrosAndCategoria
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.mapToModel
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
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
    private val maisFinancasApi: MaisFinancasApi,
) : DespesaRepository {

    override fun getDespesas(): Flow<List<Despesa>> =
        despesaDao.getDepesas().map(List<DespesaAndCategoria>::mapToModel)

    override suspend fun registrarDespesa(despesaInput: DespesaInput): Long {
        val despesaResponse = maisFinancasApi.adicionarDespesa(despesaInput.toNetwork())
        return despesaDao.cadastrarDepesaComRegistro(despesaResponse.toDespesaInput())
    }

    override suspend fun registrarDespesas(despesas: List<DespesaInput>) {
        val response = maisFinancasApi.cadastrarDespesas(despesas.map(DespesaInput::toNetwork))

        despesaDao.registrarDespesas(response.map(DespesaResponse::toDespesaInput))
    }

    override fun getCategorias(): Flow<List<Categoria>> = categoriaDao.getCategorias()
        .map(List<CategoriaEntity>::asModel)

    override suspend fun findCategoriaIdByNome(nome: String): Int =
        categoriaDao.findCategoriaIdByNome(nome)

    override fun getDespesasAndRegistros(despesaId: Long): Flow<Despesa> {
        val despesaWithRegistro = despesaDao.getDespesaAndRegistro(despesaId)
        return despesaWithRegistro.map(DespesaWithRegistrosAndCategoria::toDespesaModel)
    }

    override suspend fun updateDespesa(despesa: Despesa, gestorId: UUID, categoriaId: Int) {
        try {
            despesaDao.updateDespesa(despesa.toEntity(gestorId, categoriaId))
            maisFinancasApi.alternarLembrete(despesa.id)
        } catch (_: Exception) {
        }
    }

    override suspend fun inserirRegistro(despesaId: Long, registro: RegistroDespesa) {
        val registroResponse = maisFinancasApi.adicionarRegistro(despesaId, registro.toNetwork())
        despesaDao.insertRegistro(registroResponse.toEntity(despesaId = despesaId))
    }

    override fun getGastosPorMes(calendar: Calendar): Flow<BigDecimal> =
        despesaDao.getGastosDoMes(calendar.toMonthQuery())

    override fun getUltimasDespeas(): Flow<List<RegistroAndDespesa>> =
        despesaDao.getUltimasDespesas()

    override fun getGastoTotal(): Flow<BigDecimal> = despesaDao.getGastoTotal()

    override suspend fun fetchDespesas(gestorId: UUID) {
        val despesas = maisFinancasApi.getDespesas(gestorId)

        despesaDao.sincronizar(despesas.map(DespesaResponse::toDespesaInput))
    }
}
