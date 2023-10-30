package com.neuralnet.maisfinancas.ui.screens.estatisticas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.EstatisticaRepository
import com.neuralnet.maisfinancas.util.formattedMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class EstatisticaViewModel @Inject constructor(
    private val estatisticaRepository: EstatisticaRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EstatistacaUiState())
    val uiState: StateFlow<EstatistacaUiState> = _uiState.asStateFlow()

    private val calendar: Calendar = Calendar.getInstance()

    init {
        refreshData()
    }

    fun previousMonth() {
        calendar.add(Calendar.MONTH, -1)
        refreshData()
    }

    fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
        refreshData()
    }

    private fun refreshData() = viewModelScope.launch {
        estatisticaRepository.getDespesasPorCategoriaPeloMes(calendar)
            .collect { valorTotalPorCategoria ->
                val periodo = Calendar.getInstance().apply { timeInMillis = calendar.timeInMillis }

                _uiState.update {
                    it.copy(periodo = periodo, dados = valorTotalPorCategoria.toModel())
                }
            }
    }

}
