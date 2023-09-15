package com.neuralnet.maisfinancas.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(tableName = "gestor")
data class GestorEntity(

    @PrimaryKey
    @ColumnInfo("gestor_id")
    val id: UUID,

    @ColumnInfo("nome_gestor")
    val nome: String,

    val orcamento: BigDecimal
)
