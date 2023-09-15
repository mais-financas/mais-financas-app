package com.neuralnet.maisfinancas.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
data class CategoriaEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoria_id")
    val id: Int,

    @ColumnInfo(name = "nome_categoria")
    val nome: String,

    @ColumnInfo("porcentagem_limite")
    val porcentagemLimite: Double
)
