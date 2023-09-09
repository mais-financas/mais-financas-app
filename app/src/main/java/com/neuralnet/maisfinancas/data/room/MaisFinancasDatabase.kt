package com.neuralnet.maisfinancas.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.GestorEntity

@Database(
    entities = [
        GestorEntity::class,
        DespesaEntity::class,
        CategoriaEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MaisFinancasDatabase : RoomDatabase() {

    abstract fun gestorDao(): GestorDao

    abstract fun despesaDao(): DespesaDao

    abstract fun categoriaDao(): CategoriaDao

}
