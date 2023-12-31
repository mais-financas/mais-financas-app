package com.neuralnet.maisfinancas.ui.screens.depesas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DespesaViewModel @Inject constructor(
    despesaRepository: DespesaRepository,
) : ViewModel() {

    val uiState: StateFlow<DespesasUiState> = despesaRepository.getDespesas()
        .map { DespesasUiState(despesas = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DespesasUiState()
        )
}
