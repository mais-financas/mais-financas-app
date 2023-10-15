package com.neuralnet.maisfinancas.model.despesa

import java.math.BigDecimal
import java.util.Calendar

data class RegistroDespesa(
    val id: Long,
    val valor: BigDecimal,
    val data: Calendar,
)