package com.neuralnet.maisfinancas.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID

@Entity(
    tableName = "despesa",
    foreignKeys = [
        ForeignKey(
            entity = GestorEntity::class,
            parentColumns = ["gestor_id"],
            childColumns = ["gestor_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = ["categoria_id"],
            childColumns = ["categoria_id"],
        )
    ]
)
data class DespesaEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("despesa_id")
    val id: Long = 0L,

    val nome: String,

    val valor: BigDecimal,

    @ColumnInfo("recorrencia_em_dias")
    val recorrenciaEmDias: Int,

    @ColumnInfo("definir_lembrete")
    val definirLembrete: Boolean,

    val data: Calendar,

    @ColumnInfo("gestor_id", index = true)
    val gestorId: UUID,

    @ColumnInfo("categoria_id", index = true)
    val categoriaId: Int,
)
