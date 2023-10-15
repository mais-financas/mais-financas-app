package com.neuralnet.maisfinancas.ui.screens.depesas.adicionar

import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import com.neuralnet.maisfinancas.model.input.DespesaInput
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.toCalendar
import java.util.UUID

data class AddDespesaUiState(
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

fun AddDespesaUiState.toDespesaInput(
    gestorId: UUID,
    categoriaId: Int,
    dataInMillis: Long,
) = DespesaInput(
    Despesa(
        id = 0,
        nome = nome,
        categoria = categoria,
        recorrencia = Recorrencia(frequencia, quantidadeRecorrencia),
        definirLembrete = definirLembrete,
    ),
    gestorId = gestorId,
    categoriaId = categoriaId,
    registro = RegistroDespesa(
        id = 0,
        valor = valor.toBigDecimal(),
        data = dataInMillis.toCalendar()
    )
)
