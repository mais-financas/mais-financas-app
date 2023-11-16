package com.neuralnet.maisfinancas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.SaldoRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    gestorRepository: GestorRepository,
    saldoRepository: SaldoRepository,
) : ViewModel() {


    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

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
    private val saldoMensal = saldoRepository.getSaldoMensal(calendar)
    private val saldoTotal = saldoRepository.getSaldoTotal()

    private val movimentacoes: Flow<List<MovimentacaoItem>> =
        gestorRepository.getUltimasMovimentacoes()

    val uiState: StateFlow<HomeUiState> =
        combine(saldoMensal, saldoTotal, movimentacoes) { saldoMensal, saldoTotal, movimentacoes ->
            HomeUiState(
                saldoMensal = saldoMensal,
                saldoTotal = saldoTotal,
                movimentacoes = movimentacoes
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = HomeUiState()
        )

    init {
        viewModelScope.launch {
            val gestorId = checkNotNull(gestor.first()?.id)

            try {
                gestorRepository.sincronizar(gestorId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
