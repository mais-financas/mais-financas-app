package com.neuralnet.maisfinancas.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val gestorRepository: GestorRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginFormState> = MutableStateFlow(LoginFormState())
    val uiState: StateFlow<LoginFormState> = _uiState.asStateFlow()

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

    // TODO: Implementar login
    fun login(): Boolean {
        return true
    }

}
