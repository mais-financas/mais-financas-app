package com.neuralnet.maisfinancas.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuralnet.maisfinancas.data.repository.GestorRepository
import com.neuralnet.maisfinancas.data.room.model.GestorEntity
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

    fun cadastrarUsuario() = viewModelScope.launch {
        gestorRepository.inserirGestor(
            GestorEntity(
                id = UUID.randomUUID(),
                nome = _uiState.value.nome,
                orcamento = BigDecimal.TEN
            )
        )
    }
}
