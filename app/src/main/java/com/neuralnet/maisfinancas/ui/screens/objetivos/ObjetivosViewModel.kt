package com.neuralnet.maisfinancas.ui.screens.objetivos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ObjetivosViewModel @Inject constructor(
    objetivoRepository: ObjetivoRepository,
) : ViewModel() {

    val uiState: StateFlow<ObjetivosUiState> = objetivoRepository.getObjetivos()
        .map { ObjetivosUiState(objetivos = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ObjetivosUiState()
        )

}