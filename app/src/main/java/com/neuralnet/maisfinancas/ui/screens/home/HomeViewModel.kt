package com.neuralnet.maisfinancas.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    val uiState: StateFlow<HomeUiState> = flow<HomeUiState> { }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(
                5000L
            ), HomeUiState()
        )

}