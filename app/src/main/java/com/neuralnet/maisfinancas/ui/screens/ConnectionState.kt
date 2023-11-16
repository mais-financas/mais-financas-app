package com.neuralnet.maisfinancas.ui.screens

import androidx.annotation.StringRes
import com.neuralnet.maisfinancas.R

sealed class ConnectionState(@StringRes val message: Int? = null) {

    data object Loading : ConnectionState()
    data object Idle : ConnectionState()
    data object Success : ConnectionState()
    data object NoInternet : ConnectionState(R.string.sem_conexao)
    data object ServerUnavailable : ConnectionState(R.string.servidor_iniciando)
    data object Error : ConnectionState(R.string.sem_conexao)
}
