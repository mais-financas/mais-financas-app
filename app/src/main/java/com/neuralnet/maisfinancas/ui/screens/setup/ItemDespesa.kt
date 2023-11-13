package com.neuralnet.maisfinancas.ui.screens.setup

import androidx.compose.ui.graphics.vector.ImageVector
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import com.neuralnet.maisfinancas.model.input.DespesaInput
import com.neuralnet.maisfinancas.util.FieldValidationError
import com.neuralnet.maisfinancas.util.toCalendar
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class ItemDespesa(
    val icone: ImageVector,
    val nome: String,
    val valor: String = "",
    val dataEmMillis: Long = Instant.now().toEpochMilli(),
    val valorErrorField: FieldValidationError? = null,
    val recorrencia: Recorrencia = Recorrencia(Frequencia.NENHUMA),
    val selecionado: Boolean = false
) {
    fun isItemValid() = valorErrorField == null
}

fun ItemDespesa.toDespesaInput(gestorId: UUID, categoriaId: Int): DespesaInput = DespesaInput(
    despesa = Despesa(
        id = 0,
        nome = nome,
        categoria = "",
        recorrencia = recorrencia,
        definirLembrete = false
    ), gestorId = gestorId, categoriaId = categoriaId,
    registro = RegistroDespesa(
        id = 0,
        valor = valor.toBigDecimal(),
        data = dataEmMillis.toCalendar()
    )
)