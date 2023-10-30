package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.room.model.estatistica.ValorTotalPorCategoria
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface EstatisticaRepository {

    fun getDespesasPorCategoriaPeloMes(calendar: Calendar): Flow<List<ValorTotalPorCategoria>>

}