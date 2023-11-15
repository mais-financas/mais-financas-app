package com.neuralnet.maisfinancas.util

import androidx.annotation.StringRes
import com.neuralnet.maisfinancas.R

enum class FieldValidationError(@StringRes val message: Int) {

    VAZIO(R.string.message_campo_vazio),
    NUMERO_INVALIDO(R.string.message_numero_invalido),
    NOME_INVALIDO(R.string.message_nome_invalido),
    EMAIL_INVALIDO(R.string.message_email_invalido),
    SENHA_INVALIDA(R.string.message_senha_invalida),
    CONFIRMAR_SENHA_INVALIDA(R.string.message_confirmar_senha_invalida),
    TERMOS_INVALIDOS(R.string.termos_invalidos),
    USUARIO_EXISTE(R.string.usuario_existe),
    FALHA_LOGIN(R.string.falha_autenticar),

}