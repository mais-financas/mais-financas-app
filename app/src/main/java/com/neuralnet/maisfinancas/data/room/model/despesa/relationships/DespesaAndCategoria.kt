package com.neuralnet.maisfinancas.data.room.model.despesa.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.toModel

data class DespesaAndCategoria(
    @Embedded val registroDespesa: DespesaWithRegistroAndRecorrencia,
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
        recorrencia = it.registroDespesa.recorrencia?.toModel() ?: Recorrencia(Frequencia.NENHUMA),
        definirLembrete = it.registroDespesa.despesa.definirLembrete,
        data = it.registroDespesa.registro.data,
    )
}
