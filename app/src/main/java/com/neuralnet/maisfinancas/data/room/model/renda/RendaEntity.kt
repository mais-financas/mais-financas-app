package com.neuralnet.maisfinancas.data.room.model.renda

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Calendar

@Entity("renda")
data class RendaEntity(

    @PrimaryKey
    @ColumnInfo(name = "renda_id")
    val id: Int = 0,

    @ColumnInfo(name = "descricao")
    val descricao: String,

    @ColumnInfo(name = "valor")
    val valor: BigDecimal,

    @ColumnInfo(name = "data")
    val data: Calendar
)
