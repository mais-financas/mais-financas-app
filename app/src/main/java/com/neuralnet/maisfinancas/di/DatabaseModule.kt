package com.neuralnet.maisfinancas.di

import android.content.Context
import androidx.room.Room
import com.neuralnet.maisfinancas.data.room.MaisFinancasDatabase
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MaisFinancasDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MaisFinancasDatabase::class.java,
            name = "financas_db"
        ).createFromAsset("database/financas.db")
            .build()
    }

    @Provides
    fun provideGestorDao(database: MaisFinancasDatabase): GestorDao = database.gestorDao()

    @Provides
    fun provideDespesaDao(database: MaisFinancasDatabase): DespesaDao = database.despesaDao()

    @Provides
    fun provideCategoriaDao(database: MaisFinancasDatabase): CategoriaDao = database.categoriaDao()

}
