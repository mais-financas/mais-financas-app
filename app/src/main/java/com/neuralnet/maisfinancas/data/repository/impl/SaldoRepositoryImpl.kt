package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.SaldoRepository
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.ObjetivoDao
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import com.neuralnet.maisfinancas.util.toMonthQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.math.BigDecimal
import java.util.Calendar

class SaldoRepositoryImpl(
    private val rendaDao: RendaDao,
    private val despesaDao: DespesaDao,
    private val objetivoDao: ObjetivoDao,
) : SaldoRepository {

    override fun getSaldoMensal(calendar: Calendar): Flow<BigDecimal> {
        val rendaMensal = rendaDao.getRendasPorMes(calendar.toMonthQuery())
        val gastoMensal = despesaDao.getGastosDoMes(calendar.toMonthQuery())
        return rendaMensal.combine(gastoMensal) { renda, gastos ->
            renda.minus(gastos)
        }
    }

    override fun getSaldoTotal(): Flow<BigDecimal> {
        val rendaTotal = rendaDao.getRendaTotal()
        val gastoTotal = despesaDao.getGastoTotal()
        val reservaObjetivos = objetivoDao.getReservasTotais()

        return combine(rendaTotal, gastoTotal, reservaObjetivos) { renda, gastos, reserva ->
            renda - gastos - reserva
        }
    }
}