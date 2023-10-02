package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.Embedded
import androidx.room.Relation

data class DespesaWithRegistroAndRecorrencia(
    @Embedded val despesa: DespesaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    )
    val registro: RegistroDespesaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    )
    val recorrencia: RecorrenciaDespesaEntity? = null,
)
