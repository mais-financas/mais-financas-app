package com.neuralnet.maisfinancas.ui.screens.rendas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.RendaRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class AddRendaViewModel @Inject constructor(
    private val rendaRepository: RendaRepository,
    gestorRepository: GestorRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<AddRendaUiState> = MutableStateFlow(AddRendaUiState())
    val uiState: StateFlow<AddRendaUiState> = _uiState.asStateFlow()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

    fun updateUiState(uiState: AddRendaUiState) {
        _uiState.update { uiState }
    }

    fun salvarRenda(selectedDateInMillis: Long?) = viewModelScope.launch {
        val gestorId = checkNotNull(gestor.first()?.id)
        val renda = uiState.value.toModel(
            dataEmMillis = selectedDateInMillis ?: Instant.now().toEpochMilli()
        )

        try {
            _connectionState.value = ConnectionState.Loading
            rendaRepository.insertRenda(renda, gestorId)
            _connectionState.value = ConnectionState.Success
        } catch (e: SocketTimeoutException) {
            _connectionState.value = ConnectionState.ServerUnavailable
        } catch (e: IOException) {
            _connectionState.value = ConnectionState.NoInternet
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.Error
        }
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
