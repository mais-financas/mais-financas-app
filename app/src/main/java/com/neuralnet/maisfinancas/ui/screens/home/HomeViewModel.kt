package com.neuralnet.maisfinancas.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    gestorRepository: GestorRepository,
    despesaRepository: DespesaRepository,
) : ViewModel() {

    private val gestor: UUID? = savedStateHandle["gestor_id"]

    private val hasLoggedIn: Flow<Boolean> = gestorRepository.hasLoggedIn(gestor)

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

    val uiState: StateFlow<HomeUiState> = flow<HomeUiState> { }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000L
            ), HomeUiState()
        )

}