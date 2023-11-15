package com.neuralnet.maisfinancas.data.repository

import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal
import java.util.Calendar

interface SaldoRepository {

    fun getSaldoMensal(calendar: Calendar): Flow<BigDecimal>

    fun getSaldoTotal(): Flow<BigDecimal>

}