package com.neuralnet.maisfinancas.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.ui.screens.ConnectionState
import com.neuralnet.maisfinancas.ui.screens.auth.AuthState
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val gestorRepository: GestorRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginFormState> = MutableStateFlow(LoginFormState())
    val uiState: StateFlow<LoginFormState> = _uiState.asStateFlow()

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.NotLoggedIn)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _connectionState: MutableStateFlow<ConnectionState> =
        MutableStateFlow(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    fun updateLoginFormState(loginFormState: LoginFormState) {
        _uiState.update { loginFormState }
    }

    fun isFormValid(): Boolean = with(uiState.value) {
        if (email.isBlank()) {
            _uiState.value =
                _uiState.value.copy(emailErrorMessage = FieldValidationError.NUMERO_INVALIDO)
        }
        if (senha.isBlank()) {
            _uiState.value = _uiState.value.copy(senhaErrorMessage = FieldValidationError.VAZIO)
        }
        return _uiState.value.isFormValid()
    }

    fun login() = viewModelScope.launch {
        _connectionState.value = ConnectionState.Loading
        val gestor = uiState.value.toLoginInput()

        gestorRepository.login(gestor).onSuccess {
            _connectionState.value = ConnectionState.Idle
            _authState.value = AuthState.LoggedIn
        }.onFailure { throwable ->
            when (throwable) {
                is IllegalStateException -> {
                    _connectionState.value = ConnectionState.Idle
                    _authState.value = AuthState.NotLoggedIn
                    _uiState.update {
                        it.copy(
                            emailErrorMessage = FieldValidationError.FALHA_LOGIN,
                            senhaErrorMessage = FieldValidationError.FALHA_LOGIN
                        )
                    }
                }

                is SocketTimeoutException -> _connectionState.value =
                    ConnectionState.ServerUnavailable

                is IOException -> _connectionState.value = ConnectionState.NoInternet
                else -> _connectionState.value = ConnectionState.Error
            }
        }
    }

}
