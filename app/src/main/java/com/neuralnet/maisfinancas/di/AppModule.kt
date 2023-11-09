package com.neuralnet.maisfinancas.di

import android.content.Context
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmScheduler
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmSchedulerImpl
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.EstatisticaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.repository.impl.DespesaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.EstatisticaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.GestorRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.RendaRepositoryImpl
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.EstatisticaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.dao.RendaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideGestorRepository(
        gestorDao: GestorDao,
        despesaRepository: DespesaRepository,
        rendaRepository: RendaRepository,
    ): GestorRepository =
        GestorRepositoryImpl(gestorDao, despesaRepository, rendaRepository)

    @Singleton
    @Provides
    fun provideEstatisticaRepository(estatisticaDao: EstatisticaDao): EstatisticaRepository =
        EstatisticaRepositoryImpl(estatisticaDao)

    @Singleton
    @Provides
    fun provideRendaRepository(rendaDao: RendaDao): RendaRepository =
        RendaRepositoryImpl(rendaDao)

    @Singleton
    @Provides
    fun provideLembreteAlarmScheduler(
        @ApplicationContext context: Context,
    ): LembreteAlarmScheduler = LembreteAlarmSchedulerImpl(context)

}
