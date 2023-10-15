package com.neuralnet.maisfinancas.model.despesa

import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.DespesaWithRegistrosAndCategoria
import java.util.UUID

data class Despesa(
    val id: Long,
    val nome: String,
    val categoria: String,
    val recorrencia: Recorrencia,
    val definirLembrete: Boolean,
    val registros: List<RegistroDespesaEntity> = emptyList(),
)

fun Despesa.toEntity(gestorId: UUID, categoriaId: Int) = DespesaEntity(
    id = id,
    nome = nome,
    definirLembrete = definirLembrete,
    gestorId = gestorId,
    categoriaId = categoriaId
)

fun DespesaWithRegistrosAndCategoria.toDespesaModel() = Despesa(
    id = despesa.id,
    nome = despesa.nome,
    categoria = categoria.nome,
    recorrencia = recorrencia?.let { Recorrencia(it.frequencia) }
        ?: Recorrencia(Frequencia.NENHUMA),
    definirLembrete = despesa.definirLembrete,
    registros = registros
)
