package com.neuralnet.maisfinancas.data.network

import com.neuralnet.maisfinancas.BuildConfig
import com.neuralnet.maisfinancas.data.network.model.auth.GestorResponse
import com.neuralnet.maisfinancas.data.network.model.auth.LoginInput
import com.neuralnet.maisfinancas.data.network.model.auth.SignupInput
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

const val BASE_URL = "${BuildConfig.HOST}/api/"

interface MaisFinancasApi {

    @POST("gestores")
    suspend fun signup(@Body signupInput: SignupInput): Response<GestorResponse>

    @POST("auth/login")
    suspend fun login(@Body loginInput: LoginInput): Response<GestorResponse>

}
