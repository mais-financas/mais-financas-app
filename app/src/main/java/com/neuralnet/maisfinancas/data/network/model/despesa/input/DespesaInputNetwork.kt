package com.neuralnet.maisfinancas.data.network.model.despesa.input

import com.neuralnet.maisfinancas.model.input.DespesaInput
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DespesaInputNetwork(
    val nome: String,

    @SerialName("definir_lembrete")
    val definirLembrete: Boolean,

    @SerialName("gestor_id")
    val gestorId: String,

    @SerialName("categoria_id")
    val categoriaId: Int,

    val recorrencia: RecorrenciaNetwork,

    val registro: RegistroInputNetwork,
)

fun DespesaInput.toNetwork() = DespesaInputNetwork(
    nome = despesa.nome,
    definirLembrete = despesa.definirLembrete,
    gestorId = gestorId.toString(),
    categoriaId = categoriaId,
    registro = registro.toNetwork(),
    recorrencia = with(despesa.recorrencia) { RecorrenciaNetwork(frequencia, quantidade) }
)
