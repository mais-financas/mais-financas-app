package com.neuralnet.maisfinancas.data.room.model.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.DespesaEntity
import com.neuralnet.maisfinancas.model.Despesa

data class DespesaAndCategoria(
    @Embedded val despesa: DespesaEntity,
    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "categoria_id"
    ) val categoria: CategoriaEntity,
)

fun List<DespesaAndCategoria>.mapToModel(): List<Despesa> = map {
    Despesa(
        nome = it.despesa.nome,
        categoria = it.categoria.nome,
        valor = it.despesa.valor,
        recorrenciaEmDias = it.despesa.recorrenciaEmDias,
        definirLembrete = it.despesa.definirLembrete,
        data = it.despesa.data,
    )
}
