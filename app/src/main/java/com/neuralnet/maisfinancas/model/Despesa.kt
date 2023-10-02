package com.neuralnet.maisfinancas.model

import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaWithRegistro
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

data class Despesa(
    val id: Long,
    val nome: String,
    val categoria: String,
    val valor: BigDecimal,
    val recorrencia: Recorrencia,
    val definirLembrete: Boolean,
    val data: Calendar,
)

fun Despesa.toRegistroDespesaEntity(gestorId: UUID, categoriaId: Int) = DespesaWithRegistro(
    DespesaEntity(
        id = id,
        nome = nome,
        recorrencia = recorrencia,
        definirLembrete = definirLembrete,
        gestorId = gestorId,
        categoriaId = categoriaId,
    ), RegistroDespesaEntity(
        valor = valor,
        data = data,
        despesaId = id
    )
)
