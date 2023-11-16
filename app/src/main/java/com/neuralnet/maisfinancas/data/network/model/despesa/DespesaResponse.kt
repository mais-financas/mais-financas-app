package com.neuralnet.maisfinancas.data.network.model.despesa

import com.neuralnet.maisfinancas.data.network.model.despesa.input.RecorrenciaNetwork
import com.neuralnet.maisfinancas.model.despesa.Despesa
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.despesa.RegistroDespesa
import com.neuralnet.maisfinancas.model.input.DespesaInput
import com.neuralnet.maisfinancas.util.toCalendar
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DespesaResponse(
    val id: Long,

    val nome: String,

    @SerialName("definir_lembrete")
    val definirLembrete: Boolean,

    @SerialName("gestor_id")
    val gestorId: String,

    @SerialName("categoria_id")
    val categoriaId: Int,

    val recorrencia: RecorrenciaNetwork,

    val registros: List<RegistroResponse>
)

fun DespesaResponse.toDespesaInput() = DespesaInput(
    despesa = Despesa(
        id = id,
        nome = nome,
        definirLembrete = definirLembrete,
        categoria = "",
        recorrencia = with(recorrencia) { Recorrencia(frequencia, quantidade) },
        registros = registros.map { registroResponse -> registroResponse.toEntity(despesaId = id) }
    ),
    gestorId = UUID.fromString(gestorId),
    categoriaId = categoriaId,
    registro = with(registros.first()) {
        RegistroDespesa(
            id = id,
            valor = valor.toBigDecimal(),
            data = data.toCalendar()
        )
    }
)