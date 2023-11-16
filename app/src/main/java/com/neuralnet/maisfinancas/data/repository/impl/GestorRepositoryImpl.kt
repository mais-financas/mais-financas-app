package com.neuralnet.maisfinancas.data.repository.impl

import com.neuralnet.maisfinancas.data.network.MaisFinancasApi
import com.neuralnet.maisfinancas.data.network.model.auth.LoginInput
import com.neuralnet.maisfinancas.data.network.model.auth.SignupInput
import com.neuralnet.maisfinancas.data.network.model.auth.toEntity
import com.neuralnet.maisfinancas.data.network.model.auth.toModel
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.dao.GestorDao
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.data.room.model.despesa.relationships.RegistroAndDespesa
import com.neuralnet.maisfinancas.model.gestor.Gestor
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.ui.screens.home.MovimentacaoItem
import com.neuralnet.maisfinancas.ui.screens.home.toMovimentacoesDespesa
import com.neuralnet.maisfinancas.ui.screens.home.toMovimentacoesRenda
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.UUID

class GestorRepositoryImpl(
    private val gestorDao: GestorDao,
    private val despesaRepository: DespesaRepository,
    private val rendaRepository: RendaRepository,
    private val maisFinancasApi: MaisFinancasApi,
) : GestorRepository {

    override fun getGestor(): Flow<GestorEntity?> = gestorDao.getGestor().map { it.firstOrNull() }

    override suspend fun cadastrar(signupInput: SignupInput): Result<Gestor> {
        return try {
            val response = maisFinancasApi.signup(signupInput)

            response.body()?.let { gestorResponse ->
                gestorDao.insertGestor(gestorResponse.toEntity())
                Result.success(gestorResponse.toModel())
            } ?: Result.failure(IllegalStateException("Usuário já existe"))
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    override suspend fun login(loginInput: LoginInput): Result<Gestor> {
        return try {
            val response = maisFinancasApi.login(loginInput)

            response.body()?.let { gestorResponse ->
                gestorDao.insertGestor(gestorResponse.toEntity())
                sincronizar(UUID.fromString(gestorResponse.id))
                Result.success(gestorResponse.toModel())
            } ?: Result.failure(IllegalStateException("Login ou senha incorretos"))
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    override fun getUltimasMovimentacoes(): Flow<List<MovimentacaoItem>> {
        val ultimasRendas = rendaRepository.getUltimasRendas()
            .map(List<Renda>::toMovimentacoesRenda)

        val ultimasDespesas = despesaRepository.getUltimasDespeas()
            .map(List<RegistroAndDespesa>::toMovimentacoesDespesa)

        return ultimasDespesas.combine(ultimasRendas) { despesas, rendas ->
            val movimentacoes = despesas + rendas

            movimentacoes.sortedBy { it.data }
                .reversed()
                .take(5)
        }
    }

    override suspend fun sincronizar(gestorId: UUID) {
        despesaRepository.fetchDespesas(gestorId)
        rendaRepository.fetchRendas(gestorId)
    }
}