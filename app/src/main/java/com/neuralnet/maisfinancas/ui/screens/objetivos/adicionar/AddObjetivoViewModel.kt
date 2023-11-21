package com.neuralnet.maisfinancas.ui.screens.objetivos.adicionar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import com.neuralnet.maisfinancas.data.repository.SaldoRepository
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
import javax.inject.Inject

@HiltViewModel
class AddObjetivoViewModel @Inject constructor(
    gestorRepository: GestorRepository,
    saldoRepository: SaldoRepository,
    private val objetivoRepository: ObjetivoRepository,
) : ViewModel() {

    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _uiState: MutableStateFlow<AddObjetivoUiState> = MutableStateFlow(AddObjetivoUiState())
    val uiState: StateFlow<AddObjetivoUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val saldo = saldoRepository.getSaldoTotal().first()

            _uiState.update {
                it.copy(saldo = saldo)
            }
        }
    }

    fun updateState(addObjetivoUiState: AddObjetivoUiState) {
        _uiState.update { addObjetivoUiState }
    }

    fun inserirObjetivo() = viewModelScope.launch {
        try {
            _connectionState.value = ConnectionState.Loading
            val gestorId = checkNotNull(gestor.first()?.id)
            val objetivo = uiState.value.toModel()

            objetivoRepository.insertObjetivo(objetivo, gestorId)
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
        if (valor.toBigDecimalOrNull() == null) {
            _uiState.update { it.copy(valorErrorMessage = FieldValidationError.NUMERO_INVALIDO) }
        }
        return@with isFormValid()
    }

}
