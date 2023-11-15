package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.model.renda.Renda
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import java.util.Calendar

interface RendaRepository {

    suspend fun insertRenda(renda: Renda)

    fun getUltimasRendas(): Flow<List<Renda>>

    fun getRendaTotal(): Flow<BigDecimal>

}