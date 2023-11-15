package com.neuralnet.maisfinancas.ui.screens.auth

sealed class AuthState {
    data object Loading: AuthState()
    data object LoggedIn: AuthState()
    data object NotLoggedIn: AuthState()
    data object ConnectionError: AuthState()
    data object ServerUnavailableError: AuthState()
}
