package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity

data class DespesaWithRegistrosAndCategoria(
    @Embedded val despesa: DespesaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    ) val registros: List<RegistroDespesaEntity>,

    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "categoria_id"
    ) val categoria: CategoriaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "despesa_id"
    )
    val recorrencia: RecorrenciaDespesaEntity? = null,
)
