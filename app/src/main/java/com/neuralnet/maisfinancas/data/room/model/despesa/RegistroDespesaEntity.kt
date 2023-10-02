package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Calendar

@Entity(
    tableName = "registro_despesa",
    foreignKeys = [
        ForeignKey(
            entity = DespesaEntity::class,
            parentColumns = ["despesa_id"],
            childColumns = ["despesa_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class RegistroDespesaEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("registro_despesa_id")
    val id: Int = 0,

    @ColumnInfo("valor")
    val valor: BigDecimal,

    @ColumnInfo("data")
    val data: Calendar,

    @ColumnInfo("despesa_id", index = true)
    val despesaId: Long,
)
