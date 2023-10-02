package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.Embedded
import androidx.room.Relation

data class DespesaWithRegistro(
    @Embedded val despesa: DespesaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    )
    val registro: RegistroDespesaEntity
)
