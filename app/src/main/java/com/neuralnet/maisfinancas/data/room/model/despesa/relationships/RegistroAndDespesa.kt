package com.neuralnet.maisfinancas.data.room.model.despesa.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity

data class RegistroAndDespesa(
    @Embedded val registro: RegistroDespesaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    ) val despesa: DespesaEntity,
)
