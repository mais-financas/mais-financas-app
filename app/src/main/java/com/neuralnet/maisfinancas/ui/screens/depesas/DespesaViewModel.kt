package com.neuralnet.maisfinancas.ui.screens.depesas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.room.model.CategoriaEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DespesaViewModel @Inject constructor(
    despesaRepository: DespesaRepository,
) : ViewModel() {

    private val gestorId: UUID = UUID.fromString("00a7b810-9dad-11d1-80b4-00c04fd430c8")

    val uiState: StateFlow<DespesasUiState> = despesaRepository.getDespesas(gestorId)
        .map { DespesasUiState(despesas = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DespesasUiState()
        )
}
