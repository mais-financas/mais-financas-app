package com.neuralnet.maisfinancas.data.network

import com.neuralnet.maisfinancas.data.network.model.auth.GestorResponse
import com.neuralnet.maisfinancas.data.network.model.auth.LoginInput
import com.neuralnet.maisfinancas.data.network.model.auth.SignupInput
import com.neuralnet.maisfinancas.data.network.model.despesa.DespesaResponse
import com.neuralnet.maisfinancas.data.network.model.despesa.RegistroResponse
import com.neuralnet.maisfinancas.data.network.model.despesa.input.DespesaInputNetwork
import com.neuralnet.maisfinancas.data.network.model.despesa.input.RegistroInputNetwork
import com.neuralnet.maisfinancas.data.network.model.objetivo.ObjetivoInputNetwork
import com.neuralnet.maisfinancas.data.network.model.objetivo.ObjetivoResponse
import com.neuralnet.maisfinancas.data.network.model.renda.RendaInputNetwork
import com.neuralnet.maisfinancas.data.network.model.renda.RendaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

const val BASE_URL = "https://mais-financas-api.onrender.com/api/"

interface MaisFinancasApi {

    @POST("gestores")
    suspend fun signup(@Body signupInput: SignupInput): Response<GestorResponse>

    @POST("auth/login")
    suspend fun login(@Body loginInput: LoginInput): Response<GestorResponse>

    @POST("despesas/setup")
    suspend fun cadastrarDespesas(@Body despesas: List<DespesaInputNetwork>): List<DespesaResponse>

    @POST("despesas")
    suspend fun adicionarDespesa(@Body despesa: DespesaInputNetwork): DespesaResponse

    @PATCH("despesas/{despesaId}/alternar-lembrete")
    suspend fun alternarLembrete(@Path("despesaId") despesaId: Long): DespesaResponse

    @GET("despesas")
    suspend fun getDespesas(@Query("gestorId") gestorId: UUID): List<DespesaResponse>

    @POST("rendas")
    suspend fun adicionarRenda(@Body rendaInputNetwork: RendaInputNetwork): RendaResponse

    @GET("rendas")
    suspend fun getRendas(@Query("gestorId") gestorId: UUID): List<RendaResponse>

    @POST("despesas/{despesaId}/registros")
    suspend fun adicionarRegistro(
        @Path("despesaId") despesaId: Long,
        @Body registroInputNetwork: RegistroInputNetwork
    ): RegistroResponse

    @POST("objetivos/setup")
    suspend fun cadastrarObjetivos(@Body objetivos: List<ObjetivoInputNetwork>): List<ObjetivoResponse>

    @POST("objetivos")
    suspend fun adicionarObjetivo(@Body objetivoInputNetwork: ObjetivoInputNetwork): ObjetivoResponse

    @GET("objetivos")
    suspend fun getObjetivos(@Query("gestorId") gestorId: UUID): List<ObjetivoResponse>

    @PATCH("objetivos/{objetivoid}")
    suspend fun atualizarObjetivo(
        @Path("objetivoid") objetivoid: Int,
        @Body objetivo: ObjetivoInputNetwork
    ): ObjetivoResponse

}
