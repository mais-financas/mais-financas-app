package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.model.Despesa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DespesaRepositoryImpl : DespesaRepository {

    private val despesas = mutableListOf(
        Despesa("Água", "Essenciais", 100.0, "Mensal", false, 1693577802000),
        Despesa("Energia", "Essenciais", 123.0, "Mensal", true, 1693577802000),
        Despesa("Almoço", "Alimentação", 30.0, "Diária", true, 1693064202000),
        Despesa("Cinema", "Entretenimento", 70.0, "Nenhuma", false, 1693564202000),
        Despesa("Jantar Restaurante", "Alimentação", 40.0, "Nenhuma", false, 1693064202000),
    )

    override fun getDespesas(): Flow<List<Despesa>> {
        return flowOf(despesas)
    }

    override suspend fun salvarDespesa(despesa: Despesa) {
        despesas.add(despesa)
    }
}