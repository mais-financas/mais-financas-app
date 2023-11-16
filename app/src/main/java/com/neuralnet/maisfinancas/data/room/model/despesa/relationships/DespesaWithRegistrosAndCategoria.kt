package com.neuralnet.maisfinancas.data.room.model.despesa.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity

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
        entityColumn = "recorrencia_despesa_id"
    )
    val recorrencia: RecorrenciaDespesaEntity? = null,
)
