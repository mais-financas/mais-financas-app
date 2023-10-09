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
            childColumns = ["despesa_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class RecorrenciaDespesaEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("recorrencia_id")
    val id: Int = 0,

    val frequencia: Frequencia,

    val quantidade: Int,

    @ColumnInfo(name = "despesa_id")
    val despesaId: Long,
)
