package com.neuralnet.maisfinancas.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.neuralnet.maisfinancas.data.network.BASE_URL
import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideFinancasApi(): MaisFinancasApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(MaisFinancasApi::class.java)

}
