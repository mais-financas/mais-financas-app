package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CategoriaDao {

    @Insert
    suspend fun insertCategoria(categoriaEntity: CategoriaEntity)

    @Insert
    suspend fun insertCategorias(categoriaEntity: List<CategoriaEntity>)

    @Query("SELECT * from categoria WHERE categoria_id = :categoriaId")
    fun getCategoria(categoriaId: UUID): Flow<CategoriaEntity>

}
