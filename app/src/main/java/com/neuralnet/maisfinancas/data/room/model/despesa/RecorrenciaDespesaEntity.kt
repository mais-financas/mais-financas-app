package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.neuralnet.maisfinancas.model.despesa.Frequencia

@Entity(
    tableName = "recorrencia_despesa",
    foreignKeys = [
        ForeignKey(
            entity = DespesaEntity::class,
            parentColumns = ["despesa_id"],
            childColumns = ["recorrencia_despesa_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class RecorrenciaDespesaEntity(

    @PrimaryKey
    @ColumnInfo("recorrencia_despesa_id")
    val id: Long = 0,

    val frequencia: Frequencia,

    val quantidade: Int,
)
