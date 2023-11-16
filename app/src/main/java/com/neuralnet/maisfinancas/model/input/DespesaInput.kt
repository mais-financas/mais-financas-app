package com.neuralnet.maisfinancas.model.input

import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import java.util.UUID

data class DespesaInput(
    val despesa: Despesa,
    val gestorId: UUID,
    val categoriaId: Int,
    val registro: RegistroDespesa
)

fun DespesaInput.toDespesaEntity() = DespesaEntity(
    id = despesa.id,
    nome = despesa.nome,
    definirLembrete = despesa.definirLembrete,
    gestorId = gestorId,
    categoriaId = categoriaId,
)

fun DespesaInput.toRegistroEntity(despesaId: Long) = RegistroDespesaEntity(
    id = registro.id,
    valor = registro.valor,
    data = registro.data,
    despesaId = despesaId
)