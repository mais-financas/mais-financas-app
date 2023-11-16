package com.neuralnet.maisfinancas.ui.screens

sealed class ConnectionState {
    data object Loading : ConnectionState()
    data object Idle : ConnectionState()
    data object NoInternet : ConnectionState()
    data object ServerUnavailable : ConnectionState()
    data object Error : ConnectionState()
}
