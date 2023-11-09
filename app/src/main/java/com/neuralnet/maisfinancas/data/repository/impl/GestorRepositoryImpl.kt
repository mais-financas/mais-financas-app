package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import com.neuralnet.maisfinancas.ui.screens.home.toMovimentacoesDespesa
import com.neuralnet.maisfinancas.ui.screens.home.toMovimentacoesRenda
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.util.UUID

class GestorRepositoryImpl(
    private val gestorDao: GestorDao,
    private val despesaRepository: DespesaRepository,
    private val rendaRepository: RendaRepository
) : GestorRepository {

    override fun getGestor(userId: UUID?): Flow<GestorEntity?> = gestorDao.getGestor(userId)

    override suspend fun inserirGestor(gestorEntity: GestorEntity) {
        gestorDao.insertGestor(gestorEntity)
    }

    override fun getUltimasMovimentacoes(): Flow<List<MovimentacaoItem>> {
        val ultimasRendas = rendaRepository.getUltimasRendas()
            .map(List<Renda>::toMovimentacoesRenda)

        val ultimasDespesas = despesaRepository.getUltimasDespeas()
            .map(List<RegistroAndDespesa>::toMovimentacoesDespesa)

        return ultimasDespesas.combine(ultimasRendas) { despesas, rendas ->
            val movimentacoes = despesas + rendas

            movimentacoes.sortedBy { it.data }
                .reversed()
                .take(5)
        }
    }

}