package com.neuralnet.maisfinancas.model.despesa

import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity

data class Categoria(
    val id: Int,
    val nome: String,
    val porcentagemLimite: Double,
)

fun CategoriaEntity.toModel() = Categoria(
    id = id,
    nome = nome,
    porcentagemLimite = porcentagemLimite
)

fun List<CategoriaEntity>.asModel() = map(CategoriaEntity::toModel)
