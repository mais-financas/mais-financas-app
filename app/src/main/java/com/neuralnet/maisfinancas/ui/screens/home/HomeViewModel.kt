package com.neuralnet.maisfinancas.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.renda.Renda
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.math.BigDecimal
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    gestorRepository: GestorRepository,
    despesaRepository: DespesaRepository,
    rendaRepository: RendaRepository,
) : ViewModel() {

    private val gestorId: UUID? =
        savedStateHandle["gestor_id"] ?: UUID.fromString("00a7b810-9dad-11d1-80b4-00c04fd430c8")
    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor(gestorId)

    private val hasLoggedIn: Flow<Boolean> = gestor.map { it != null }

    val authState: StateFlow<AuthState> = hasLoggedIn.map { hasLoggedIn ->
        if (hasLoggedIn) {
            AuthState.LoggedIn
        } else {
            AuthState.NotLoggedIn
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AuthState.Loading
    )

    private val calendar = Calendar.getInstance()
    private val rendasDoMes: Flow<BigDecimal> = rendaRepository.getRendaPorMes(calendar)
    private val gastosDoMes: Flow<BigDecimal> = despesaRepository.getGastosPorMes(calendar)

    private val movimentacoes: Flow<List<MovimentacaoItem>> =
        gestorRepository.getUltimasMovimentacoes()

    val uiState: StateFlow<HomeUiState> =
        combine(gastosDoMes, rendasDoMes, movimentacoes) { gastos, renda, movimentacoes ->
            HomeUiState(
                saldoMensal = renda.minus(gastos),
                movimentacoes = movimentacoes
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeUiState()
        )

}
