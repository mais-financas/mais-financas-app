package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.model.Despesa

data class DespesaAndCategoria(
    @Embedded val registroDespesa: DespesaWithRegistro,
    @Relation(
        parentColumn = "categoria_id",
        entityColumn = "categoria_id"
    ) val categoria: CategoriaEntity,
)

fun List<DespesaAndCategoria>.mapToModel(): List<Despesa> = map {
    Despesa(
        id = it.registroDespesa.despesa.id,
        nome = it.registroDespesa.despesa.nome,
        categoria = it.categoria.nome,
        valor = it.registroDespesa.registro.valor,
        recorrencia = it.registroDespesa.despesa.recorrencia,
        definirLembrete = it.registroDespesa.despesa.definirLembrete,
        data = it.registroDespesa.registro.data,
    )
}
