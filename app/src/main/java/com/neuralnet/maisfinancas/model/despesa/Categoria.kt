package com.neuralnet.maisfinancas.model.despesa

import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity

data class Categoria(
    val id: Int = 0,
    val nome: String,
)

fun CategoriaEntity.toModel() = Categoria(
    id = id,
    nome = nome,
)

fun List<CategoriaEntity>.asModel() = map(CategoriaEntity::toModel)
