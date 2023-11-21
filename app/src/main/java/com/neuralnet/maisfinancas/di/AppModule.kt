package com.neuralnet.maisfinancas.di

import android.content.Context
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmScheduler
import com.neuralnet.maisfinancas.data.alarm.LembreteAlarmSchedulerImpl
import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.EstatisticaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.repository.SaldoRepository
import com.neuralnet.maisfinancas.data.repository.SugestoesRepository
import com.neuralnet.maisfinancas.data.repository.impl.DespesaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.EstatisticaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.GestorRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.ObjetivoRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.RendaRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.SaldoRepositoryImpl
import com.neuralnet.maisfinancas.data.repository.impl.SugestoesRepositoryImpl
import com.neuralnet.maisfinancas.data.room.dao.CategoriaDao
import com.neuralnet.maisfinancas.data.room.dao.DespesaDao
import com.neuralnet.maisfinancas.data.room.dao.EstatisticaDao
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.dao.ObjetivoDao
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
        maisFinancasApi: MaisFinancasApi,
    ): DespesaRepository = DespesaRepositoryImpl(despesaDao, categoriaDao, maisFinancasApi)

    @Singleton
    @Provides
    fun provideGestorRepository(
        gestorDao: GestorDao,
        despesaRepository: DespesaRepository,
        rendaRepository: RendaRepository,
        objetivoRepository: ObjetivoRepository,
        maisFinancasApi: MaisFinancasApi,
    ): GestorRepository =
        GestorRepositoryImpl(
            gestorDao = gestorDao,
            despesaRepository = despesaRepository,
            rendaRepository = rendaRepository,
            objetivoRepository = objetivoRepository,
            maisFinancasApi = maisFinancasApi
        )

    @Singleton
    @Provides
    fun provideEstatisticaRepository(estatisticaDao: EstatisticaDao): EstatisticaRepository =
        EstatisticaRepositoryImpl(estatisticaDao)

    @Singleton
    @Provides
    fun provideRendaRepository(
        rendaDao: RendaDao,
        maisFinancasApi: MaisFinancasApi
    ): RendaRepository = RendaRepositoryImpl(rendaDao, maisFinancasApi)

    @Singleton
    @Provides
    fun provideObjetivoRepository(
        objetivoDao: ObjetivoDao,
        maisFinancasApi: MaisFinancasApi
    ): ObjetivoRepository = ObjetivoRepositoryImpl(objetivoDao, maisFinancasApi)

    @Singleton
    @Provides
    fun provideSugestoesRepository(despesaRepository: DespesaRepository): SugestoesRepository =
        SugestoesRepositoryImpl(despesaRepository)

    @Singleton
    @Provides
    fun provideSaldoRepository(
        rendaDao: RendaDao,
        despesaDao: DespesaDao,
        objetivoDao: ObjetivoDao
    ): SaldoRepository = SaldoRepositoryImpl(rendaDao, despesaDao, objetivoDao)

    @Singleton
    @Provides
    fun provideLembreteAlarmScheduler(
        @ApplicationContext context: Context,
    ): LembreteAlarmScheduler = LembreteAlarmSchedulerImpl(context)

}
