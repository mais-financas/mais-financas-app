package com.neuralnet.maisfinancas.ui.screens.rendas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddRendaViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<AddRendaUiState> = MutableStateFlow(AddRendaUiState())
    val uiState: StateFlow<AddRendaUiState> = _uiState.asStateFlow()

    fun updateUiState(uiState: AddRendaUiState) {
        _uiState.update { uiState }
    }

    fun salvarRenda(selectedDateInMillis: Long?) = Unit

    fun isFormValid(): Boolean = true
}
