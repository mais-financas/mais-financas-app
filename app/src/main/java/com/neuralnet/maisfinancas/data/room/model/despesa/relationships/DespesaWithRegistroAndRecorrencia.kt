package com.neuralnet.maisfinancas.data.room.model.despesa.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity

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
