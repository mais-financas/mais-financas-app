package com.neuralnet.maisfinancas.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.EstatisticaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.DespesaEntity
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RecorrenciaDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.RegistroDespesaEntity
import com.neuralnet.maisfinancas.data.room.model.renda.RendaEntity

@Database(
    entities = [
        GestorEntity::class,
        DespesaEntity::class,
        RegistroDespesaEntity::class,
        RecorrenciaDespesaEntity::class,
        CategoriaEntity::class,
        RendaEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MaisFinancasDatabase : RoomDatabase() {

    abstract fun gestorDao(): GestorDao

    abstract fun despesaDao(): DespesaDao

    abstract fun categoriaDao(): CategoriaDao

    abstract fun estatisticaDao(): EstatisticaDao

    abstract fun rendaDao(): RendaDao
}
