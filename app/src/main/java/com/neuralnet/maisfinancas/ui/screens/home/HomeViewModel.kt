package com.neuralnet.maisfinancas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    despesaRepository: DespesaRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = flow<HomeUiState> { }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000L
            ), HomeUiState()
        )

}