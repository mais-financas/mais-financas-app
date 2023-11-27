package com.neuralnet.maisfinancas.ui.screens.objetivos.detalhes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.repository.ObjetivoRepository
import com.neuralnet.maisfinancas.data.repository.SaldoRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.math.BigDecimal
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class DetalhesObjetivoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    gestorRepository: GestorRepository,
    saldoRepository: SaldoRepository,
    private val objetivoRepository: ObjetivoRepository,
) : ViewModel() {

    private val objetivoId: Int = checkNotNull(savedStateHandle["objetivo_id"])
    private val gestor: Flow<GestorEntity?> = gestorRepository.getGestor()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val saldoTotal = saldoRepository.getSaldoTotal()
    private val detalhesObjetivo = objetivoRepository.findById(objetivoId)

    private val _modoAtivo: MutableStateFlow<ModoAtivo> = MutableStateFlow(ModoAtivo.INVISIBLE)
    val modoAtivo: StateFlow<ModoAtivo> = _modoAtivo.asStateFlow()

    fun alternarModoAtivo(modoAtivo: ModoAtivo) {
        _modoAtivo.update { modoAtivo }
        viewModelScope.launch {
            val saldo = if (modoAtivo == ModoAtivo.RESGATAR) {
                detalhesObjetivo.first().valor
            } else {
                saldoTotal.first()
            }

            _updateObjetivoUiState.update { it.copy(saldo = saldo) }
        }
    }

    val detalhesObjetivoState: StateFlow<DetalhesObjetivoUiState> = detalhesObjetivo
        .combine(saldoTotal) { objetivo, saldo ->
            DetalhesObjetivoUiState(
                id = objetivo.id,
                descricao = objetivo.descricao,
                saldo = saldo,
                valor = objetivo.valor,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DetalhesObjetivoUiState()
        )

    private val _updateObjetivoUiState = MutableStateFlow(UpdateObjetivoUiState())
    val updateObjetivoUiState = _updateObjetivoUiState.asStateFlow()

    fun updateObjetivoState(updateObjetivoUiState: UpdateObjetivoUiState) {
        _updateObjetivoUiState.update { updateObjetivoUiState }
    }

    fun resgatar() {
        val valorAtual = detalhesObjetivoState.value.valor
        val valorResgate = updateObjetivoUiState.value.valor.toBigDecimal()

        val valorFinal = valorAtual - valorResgate

        atualizarObjetivo(valorFinal)
    }

    fun guardar() {
        val valorAtual = detalhesObjetivoState.value.valor
        val valorParaGuardar = updateObjetivoUiState.value.valor.toBigDecimal()

        val valorFinal = valorAtual + valorParaGuardar

        atualizarObjetivo(valorFinal)
    }

    private fun atualizarObjetivo(valorFinal: BigDecimal) = viewModelScope.launch {
        try {
            _connectionState.value = ConnectionState.Loading
            val gestorId = checkNotNull(gestor.first()?.id)
            val objetivo = with(detalhesObjetivo.first()) {
                Objetivo(
                    id = id,
                    descricao = descricao,
                    valor = valorFinal,
                )
            }

            objetivoRepository.atualizarObjetivo(objetivo, gestorId)
            _connectionState.value = ConnectionState.Success
            resetObjetivoState()
        } catch (e: SocketTimeoutException) {
            _connectionState.value = ConnectionState.ServerUnavailable
        } catch (e: IOException) {
            _connectionState.value = ConnectionState.NoInternet
            _modoAtivo.value = ModoAtivo.INVISIBLE
        } catch (e: Exception) {
            _connectionState.value = ConnectionState.Error
            _modoAtivo.value = ModoAtivo.INVISIBLE
        }
    }

    fun isFormValid(): Boolean {
        if (updateObjetivoUiState.value.valor.toBigDecimalOrNull() == null) {
            _updateObjetivoUiState.update {
                it.copy(valorErrorMessage = FieldValidationError.NUMERO_INVALIDO)
            }
        }
        return updateObjetivoUiState.value.isFormValid()
    }

    fun resetObjetivoState() {
        _updateObjetivoUiState.update { objetivoUiState ->
            objetivoUiState.copy(
                valor = "",
                saldo = BigDecimal.ZERO,
            )
        }
        _modoAtivo.value = ModoAtivo.INVISIBLE
    }

}