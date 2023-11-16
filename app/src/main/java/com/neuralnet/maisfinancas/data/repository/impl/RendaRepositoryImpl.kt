package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import com.neuralnet.maisfinancas.data.network.model.renda.toEntity
import com.neuralnet.maisfinancas.data.network.model.renda.toNetwork
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.model.renda.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.util.UUID

class RendaRepositoryImpl(
    private val rendaDao: RendaDao,
    private val maisFinancasApi: MaisFinancasApi,
) : RendaRepository {

    override suspend fun insertRenda(renda: Renda, gestorId: UUID) {
        val rendaResponse = maisFinancasApi.adicionarRenda(renda.toNetwork(gestorId))
        rendaDao.insert(rendaResponse.toEntity(gestorId))
    }

    override fun getUltimasRendas(): Flow<List<Renda>> =
        rendaDao.getUltimasRendas().map { rendaEntities -> rendaEntities.map(RendaEntity::toModel) }

    override fun getRendaTotal(): Flow<BigDecimal> = rendaDao.getRendaTotal()

    override suspend fun fetchRendas(gestorId: UUID) {
        val rendaResponses = maisFinancasApi.getRendas(gestorId)

        rendaDao.sincronizar(rendaResponses.map { it.toEntity(gestorId = gestorId) })
    }
}