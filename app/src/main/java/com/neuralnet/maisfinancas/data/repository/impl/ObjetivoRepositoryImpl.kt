package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import com.neuralnet.maisfinancas.data.network.model.objetivo.toEntity
import com.neuralnet.maisfinancas.data.network.model.objetivo.toNetwork
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import com.neuralnet.maisfinancas.data.room.dao.ObjetivoDao
import com.neuralnet.maisfinancas.data.room.model.objetivo.ObjetivoEntity
import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.model.objetivo.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class ObjetivoRepositoryImpl(
    private val objetivoDao: ObjetivoDao,
    private val maisFinancasApi: MaisFinancasApi,
) : ObjetivoRepository {

    override suspend fun insertObjetivo(objetivo: Objetivo, gestorId: UUID) {
        val objetivoResponse = maisFinancasApi.adicionarObjetivo(objetivo.toNetwork(gestorId))
        objetivoDao.insert(objetivoResponse.toEntity(gestorId))
    }

    override fun getObjetivos(): Flow<List<Objetivo>> = objetivoDao.getObjetivos()
        .map(List<ObjetivoEntity>::toModel)

    override suspend fun fetchObjetivos(gestorId: UUID) {
        val objetivosResponse = maisFinancasApi.getObjetivos(gestorId)

        objetivoDao.sincronizar(objetivosResponse.map { it.toEntity(gestorId) })
    }

    override suspend fun inserirObjetivos(gestorId: UUID) {
        val objetivos = SugestoesDatasource.objetivos.map { it.toNetwork(gestorId) }
        val objetivosResponse = maisFinancasApi.cadastrarObjetivos(objetivos)

        objetivoDao.sincronizar(objetivosResponse.map { it.toEntity(gestorId) })
    }

    override fun findById(objetivoId: Int): Flow<Objetivo> =
        objetivoDao.findById(objetivoId).map(ObjetivoEntity::toModel)

    override suspend fun atualizarObjetivo(objetivo: Objetivo, gestorId: UUID) {
        val objetivoResponse = maisFinancasApi.atualizarObjetivo(
            objetivoid = objetivo.id,
            objetivo = objetivo.toNetwork(gestorId)
        )
        objetivoDao.insert(objetivoResponse.toEntity(gestorId))
    }

}
