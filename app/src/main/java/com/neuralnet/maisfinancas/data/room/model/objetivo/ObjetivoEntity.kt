package com.neuralnet.maisfinancas.data.room.model.objetivo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "objetivo",
    foreignKeys = [
        ForeignKey(
            entity = GestorEntity::class,
            parentColumns = ["gestor_id"],
            childColumns = ["gestor_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class ObjetivoEntity(

    @PrimaryKey
    @ColumnInfo(name = "objetivo_id")
    val id: Int = 0,

    @ColumnInfo(name = "descricao")
    val descricao: String,

    @ColumnInfo(name = "valor")
    val valor: BigDecimal,

    @ColumnInfo("gestor_id", index = true)
    val gestorId: UUID,
)
