package com.neuralnet.maisfinancas.data.room.model.despesa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
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

    @ColumnInfo("nome_despesa")
    val nome: String,

    @ColumnInfo("definir_lembrete")
    val definirLembrete: Boolean,

    @ColumnInfo("gestor_id", index = true)
    val gestorId: UUID,

    @ColumnInfo("categoria_id", index = true)
    val categoriaId: Int,
)
