package com.neuralnet.maisfinancas.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {

    @Insert
    suspend fun insertCategoria(categoriaEntity: CategoriaEntity)

    @Insert
    suspend fun insertCategorias(categoriaEntity: List<CategoriaEntity>)

    @Query("SELECT * FROM categoria")
    fun getCategorias(): Flow<List<CategoriaEntity>>

    @Query("SELECT categoria_id FROM categoria WHERE nome_categoria =:nome")
    suspend fun findCategoriaIdByNome(nome: String): Int

}
