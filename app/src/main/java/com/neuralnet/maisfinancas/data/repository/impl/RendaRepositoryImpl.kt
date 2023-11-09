package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.model.renda.toEntity
import com.neuralnet.maisfinancas.model.renda.toModel
import com.neuralnet.maisfinancas.util.toMonthQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.util.Calendar

class RendaRepositoryImpl(
    private val rendaDao: RendaDao
) : RendaRepository {

    override suspend fun insertRenda(renda: Renda) = rendaDao.insert(renda.toEntity())

    override fun getRendaPorMes(calendar: Calendar): Flow<BigDecimal> =
        rendaDao.getRendasPorMes(calendar.toMonthQuery())

    override fun getUltimasRendas(): Flow<List<Renda>> =
        rendaDao.getUltimasRendas().map { rendaEntities -> rendaEntities.map(RendaEntity::toModel) }

}