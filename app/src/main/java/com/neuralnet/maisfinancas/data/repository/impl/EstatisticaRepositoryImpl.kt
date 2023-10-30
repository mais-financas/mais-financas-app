package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.repository.EstatisticaRepository
import com.neuralnet.maisfinancas.data.room.dao.EstatisticaDao
import com.neuralnet.maisfinancas.data.room.model.estatistica.ValorTotalPorCategoria
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class EstatisticaRepositoryImpl(
    private val estatisticaDao: EstatisticaDao
) : EstatisticaRepository {

    override fun getDespesasPorCategoriaPeloMes(calendar: Calendar): Flow<List<ValorTotalPorCategoria>> {
        return estatisticaDao.getDespesasPorCategoriaPeloMes(calendar.toMonthQuery())
    }
}

private fun Calendar.toMonthQuery(): String {
    val ano = get(Calendar.YEAR)
    val mes = get(Calendar.MONTH) + 1 // Meses começam em 0
    return String.format("%04d-%02d", ano, mes)
}
