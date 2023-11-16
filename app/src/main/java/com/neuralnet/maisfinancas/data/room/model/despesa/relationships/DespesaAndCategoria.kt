package com.neuralnet.maisfinancas.data.room.model.despesa.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.toModel

data class DespesaAndCategoria(
    @Embedded val despesa: DespesaEntity,
    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "categoria_id"
    ) val categoria: CategoriaEntity,

    @Relation(
        parentColumn = "despesa_id",
        entityColumn = "recorrencia_despesa_id"
    ) val recorrencia: RecorrenciaDespesaEntity? = null,
)

fun List<DespesaAndCategoria>.mapToModel(): List<Despesa> = map {
    Despesa(
        id = it.despesa.id,
        nome = it.despesa.nome,
        categoria = it.categoria.nome,
        recorrencia = it.recorrencia?.toModel() ?: Recorrencia(),
        definirLembrete = it.despesa.definirLembrete,
    )
}
