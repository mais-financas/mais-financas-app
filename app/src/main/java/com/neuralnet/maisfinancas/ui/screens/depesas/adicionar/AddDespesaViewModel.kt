package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class AddDespesaViewModel @Inject constructor(
    private val despesaRepository: DespesaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DespesaUiState())
    val uiState: StateFlow<DespesaUiState> = _uiState.asStateFlow()

    fun updateUiState(uiState: DespesaUiState) {
        _uiState.update { uiState }
    }

    fun salvarDespesa(dataEmEpochMillis: Long?) = viewModelScope.launch {
        val selectedDateInMillis = dataEmEpochMillis ?: Instant.now().toEpochMilli()
        val despesa = uiState.value.toDespesaModel(selectedDateInMillis)
        despesaRepository.salvarDespesa(despesa)
    }
}