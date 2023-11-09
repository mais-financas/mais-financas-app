package com.neuralnet.maisfinancas.ui.screens.rendas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class AddRendaViewModel @Inject constructor(
    private val rendaRepository: RendaRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddRendaUiState> = MutableStateFlow(AddRendaUiState())
    val uiState: StateFlow<AddRendaUiState> = _uiState.asStateFlow()

    fun updateUiState(uiState: AddRendaUiState) {
        _uiState.update { uiState }
    }

    fun salvarRenda(selectedDateInMillis: Long?) = viewModelScope.launch {
        val renda = uiState.value.toModel(
            dataEmMillis = selectedDateInMillis ?: Instant.now().toEpochMilli()
        )

        rendaRepository.insertRenda(renda)
    }

    fun isFormValid(): Boolean = with(uiState.value) {
        if (descricao.isBlank()) {
            _uiState.value = _uiState.value.copy(descricaoErrorField = FieldValidationError.VAZIO)
        }
        if (valor.toBigDecimalOrNull() == null) {
            _uiState.value =
                _uiState.value.copy(valorErrorField = FieldValidationError.NUMERO_INVALIDO)
        }
        return _uiState.value.isFormValid()
    }

}
