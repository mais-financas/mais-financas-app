package com.neuralnet.maisfinancas.di

import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.impl.DespesaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.GestorRepositoryImpl
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDespesaRepository(
        despesaDao: DespesaDao,
        categoriaDao: CategoriaDao,
    ): DespesaRepository = DespesaRepositoryImpl(despesaDao, categoriaDao)

    @Singleton
    @Provides
    fun provideGestorRepository(gestorDao: GestorDao): GestorRepository =
        GestorRepositoryImpl(gestorDao)

}
