package com.neuralnet.maisfinancas.ui.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
import com.neuralnet.maisfinancas.util.FieldValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val gestorRepository: GestorRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignUpFormState> = MutableStateFlow(SignUpFormState())
    val uiState: StateFlow<SignUpFormState> = _uiState.asStateFlow()

    fun updateState(signUpFormState: SignUpFormState) {
        _uiState.update { signUpFormState }
    }

    fun signUp(): Boolean {
        viewModelScope.launch {
            gestorRepository.inserirGestor(
                GestorEntity(
                    id = UUID.randomUUID(),
                    nome = _uiState.value.nome,
                    orcamento = BigDecimal.TEN
                )
            )
        }
        return true
    }

    fun isFormValid(): Boolean = with(uiState.value) {
        if (nome.isBlank()) {
            _uiState.value = _uiState.value.copy(nomeErrorMessage = FieldValidationError.VAZIO)
        }
        if (email.isBlank()) {
            _uiState.value =
                _uiState.value.copy(emailErrorMessage = FieldValidationError.NUMERO_INVALIDO)
        }
        if (senha.isBlank()) {
            _uiState.value = _uiState.value.copy(senhaErrorMessage = FieldValidationError.VAZIO)
        }
        if (confirmarSenha.isBlank()) {
            _uiState.value = _uiState.value.copy(confirmarSenhaErrorMessage = FieldValidationError.VAZIO)
        }
        if (confirmarSenha != senha) {
            _uiState.value = _uiState.value.copy(
                confirmarSenhaErrorMessage = FieldValidationError.CONFIRMAR_SENHA_INVALIDA
            )
        }
        if (!agreeWithTermsAndConditions) {
            _uiState.value =
                uiState.value.copy(agreeWithTermsErrorMessage = FieldValidationError.TERMOS_INVALIDOS)
        }
        return _uiState.value.isFormValid()
    }

}
