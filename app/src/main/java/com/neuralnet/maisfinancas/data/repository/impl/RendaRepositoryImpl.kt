package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.model.renda.toEntity
import com.neuralnet.maisfinancas.model.renda.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal

class RendaRepositoryImpl(
    private val rendaDao: RendaDao
) : RendaRepository {

    override suspend fun insertRenda(renda: Renda) = rendaDao.insert(renda.toEntity())

    override fun getUltimasRendas(): Flow<List<Renda>> =
        rendaDao.getUltimasRendas().map { rendaEntities -> rendaEntities.map(RendaEntity::toModel) }

    override fun getRendaTotal(): Flow<BigDecimal> = rendaDao.getRendaTotal()

}