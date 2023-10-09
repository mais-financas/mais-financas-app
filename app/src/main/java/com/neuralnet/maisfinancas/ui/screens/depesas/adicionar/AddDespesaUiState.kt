package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import android.icu.util.UniversalTimeScale.toBigDecimal
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.toCalendar

data class AddDespesaUiState(
    val id: Long = 0,
    val nome: String = "",
    val nomeErrorField: FieldValidationError? = null,
    val valor: String = "",
    val valorErrorField: FieldValidationError? = null,
    val categoria: String = "",
    val categoriaErrorField: FieldValidationError? = null,
    val frequencia: Frequencia = Frequencia.NENHUMA,
    val quantidadeRecorrencia: Int = 1,
    val definirLembrete: Boolean = false,
) {
    fun isFormValid(): Boolean = nomeErrorField == null &&
            valorErrorField == null && categoriaErrorField == null
}

fun AddDespesaUiState.toDespesaModel(dataEmEpochMillis: Long) = Despesa(
    id = id,
    nome = nome,
    valor = valor.toBigDecimal(),
    categoria = categoria,
    recorrencia = Recorrencia(frequencia, quantidadeRecorrencia),
    definirLembrete = definirLembrete,
    data = dataEmEpochMillis.toCalendar(),
)
