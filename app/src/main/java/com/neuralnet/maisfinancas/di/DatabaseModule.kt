package com.neuralnet.maisfinancas.di

import android.content.Context
import androidx.room.Room
import com.neuralnet.maisfinancas.data.room.MaisFinancasDatabase
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.EstatisticaDao
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MaisFinancasDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MaisFinancasDatabase::class.java,
            name = "financas_db"
        ).createFromAsset("database/financas.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideGestorDao(database: MaisFinancasDatabase): GestorDao = database.gestorDao()

    @Provides
    @Singleton
    fun provideDespesaDao(database: MaisFinancasDatabase): DespesaDao = database.despesaDao()

    @Provides
    @Singleton
    fun provideCategoriaDao(database: MaisFinancasDatabase): CategoriaDao = database.categoriaDao()

    @Provides
    @Singleton
    fun provideEstatisticaDao(database: MaisFinancasDatabase): EstatisticaDao =
        database.estatisticaDao()

}
