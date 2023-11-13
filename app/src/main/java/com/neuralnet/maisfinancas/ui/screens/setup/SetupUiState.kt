package com.neuralnet.maisfinancas.ui.screens.setup

import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.input.DespesaInput
import java.util.UUID

data class SetupUiState(
    val despesas: Map<Categoria, List<ItemDespesa>> = SugestoesDatasource.defaultDespesas,
)

fun SetupUiState.mapDespesasSelecionadasToList(gestorId: UUID): List<DespesaInput> {
    return despesas.entries.flatMap { (categoria, despesas) ->
        despesas
            .filter(ItemDespesa::selecionado)
            .map { itemDespesa ->
                itemDespesa.toDespesaInput(gestorId = gestorId, categoria.id)
            }
    }
}
