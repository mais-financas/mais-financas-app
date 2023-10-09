package com.neuralnet.maisfinancas.model.despesa

import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistroAndRecorrencia
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistrosAndCategoria
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
    val registros: List<RegistroDespesaEntity> = emptyList(),
)

fun Despesa.toEntity(gestorId: UUID, categoriaId: Int) = DespesaEntity(
    id = id,
    nome = nome,
    definirLembrete = definirLembrete,
    gestorId = gestorId,
    categoriaId = categoriaId
)

fun Despesa.toRegistroDespesaEntity(gestorId: UUID, categoriaId: Int) =
    DespesaWithRegistroAndRecorrencia(
        DespesaEntity(
            id = id,
            nome = nome,
            definirLembrete = definirLembrete,
            gestorId = gestorId,
            categoriaId = categoriaId,
        ), RegistroDespesaEntity(
            valor = valor,
            data = data,
            despesaId = id
        ),
        RecorrenciaDespesaEntity(
            frequencia = recorrencia.frequencia,
            quantidade = recorrencia.quantidade,
            despesaId = id
        )
    )

fun DespesaWithRegistrosAndCategoria.toDespesaModel() = Despesa(
    id = despesa.id,
    nome = despesa.nome,
    categoria = categoria.nome,
    valor = registros.sumOf { it.valor },
    recorrencia = recorrencia?.let { Recorrencia(it.frequencia) }
        ?: Recorrencia(Frequencia.NENHUMA),
    definirLembrete = despesa.definirLembrete,
    data = registros.first().data,
    registros = registros
)
