package com.neuralnet.maisfinancas.data.network.model.auth

import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.gestor.Gestor
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class GestorResponse(
    val id: String,
    val nome: String,
    val email: String,
)

fun GestorResponse.toEntity() = GestorEntity(
    id = UUID.fromString(id),
    nome = nome,
)

fun GestorResponse.toModel() = Gestor(
    id = UUID.fromString(id),
    nome = nome,
)
