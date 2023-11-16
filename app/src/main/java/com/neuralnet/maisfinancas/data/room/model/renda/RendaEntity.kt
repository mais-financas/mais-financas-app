package com.neuralnet.maisfinancas.data.room.model.renda

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

@Entity(
    tableName = "renda",
    foreignKeys = [
        ForeignKey(
            entity = GestorEntity::class,
            parentColumns = ["gestor_id"],
            childColumns = ["gestor_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class RendaEntity(

    @PrimaryKey
    @ColumnInfo(name = "renda_id")
    val id: Int = 0,

    @ColumnInfo(name = "descricao")
    val descricao: String,

    @ColumnInfo(name = "valor")
    val valor: BigDecimal,

    @ColumnInfo(name = "data")
    val data: Calendar,

    @ColumnInfo("gestor_id", index = true)
    val gestorId: UUID,
)
