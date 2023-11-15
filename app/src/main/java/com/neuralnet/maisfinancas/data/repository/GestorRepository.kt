package com.neuralnet.maisfinancas.data.repository

import com.neuralnet.maisfinancas.data.network.model.auth.LoginInput
import com.neuralnet.maisfinancas.data.network.model.auth.SignupInput
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.gestor.Gestor
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface GestorRepository {

    fun getGestor(): Flow<GestorEntity?>

    suspend fun cadastrar(signupInput: SignupInput): Result<Gestor>

    suspend fun login(loginInput: LoginInput): Result<Gestor>

    fun getUltimasMovimentacoes(): Flow<List<MovimentacaoItem>>

}
