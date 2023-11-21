package com.neuralnet.maisfinancas.data.datasource

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Audiotrack
import androidx.compose.material.icons.outlined.BakeryDining
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.BrunchDining
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Cases
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Healing
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.LocalMovies
import androidx.compose.material.icons.outlined.LocalPharmacy
import androidx.compose.material.icons.outlined.LocalPolice
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material.icons.outlined.VideogameAsset
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.Wifi
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.model.despesa.Frequencia
import com.neuralnet.maisfinancas.model.despesa.Recorrencia
import com.neuralnet.maisfinancas.model.objetivo.Objetivo
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import java.math.BigDecimal

object SugestoesDatasource {

    private val despesasAlimentacao = listOf(
        ItemDespesa(icone = Icons.Outlined.Coffee, nome = "Café"),
        ItemDespesa(icone = Icons.Outlined.BakeryDining, nome = "Lanches"),
        ItemDespesa(
            icone = Icons.Outlined.LocalDining, nome = "Almoço",
            recorrencia = Recorrencia(Frequencia.DIARIA)
        ),
        ItemDespesa(icone = Icons.Outlined.BrunchDining, nome = "Jantar"),
        ItemDespesa(icone = Icons.Outlined.Fastfood, nome = "FastFood"),
    )

    private val despesasEssenciais = listOf(
        ItemDespesa(
            icone = Icons.Outlined.ShoppingCart, nome = "Compras",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.WaterDrop, nome = "Água",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.ElectricBolt, nome = "Energia",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.Wifi, nome = "Internet",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.PhoneAndroid, nome = "Celular",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
    )

    private val despesasEducacaoo = listOf(
        ItemDespesa(
            icone = Icons.Outlined.Abc, nome = "Escola",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(icone = Icons.Outlined.Book, nome = "Livros"),
        ItemDespesa(icone = Icons.Outlined.School, nome = "Graduação"),
        ItemDespesa(
            icone = Icons.Outlined.CollectionsBookmark, nome = "Cursos",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
    )

    private val despesasEntretenimento = listOf(
        ItemDespesa(icone = Icons.Outlined.LocalMovies, nome = "Cinema"),
        ItemDespesa(
            icone = Icons.Outlined.LiveTv, nome = "Streaming",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.Audiotrack, nome = "Música",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(icone = Icons.Outlined.TheaterComedy, nome = "Teatro"),
        ItemDespesa(icone = Icons.Outlined.VideogameAsset, nome = "Jogos"),
    )

    private val despesasTransporte = listOf(
        ItemDespesa(icone = Icons.Outlined.Cases, nome = "Viagens"),
        ItemDespesa(
            icone = Icons.Outlined.DirectionsBus, nome = "Transporte Público",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.LocalGasStation, nome = "Combustível",
            recorrencia = Recorrencia(Frequencia.SEMANAL)
        ),
        ItemDespesa(icone = Icons.Outlined.Build, nome = "Manutenção"),
        ItemDespesa(icone = Icons.Outlined.LocalPolice, nome = "Seguro"),
    )

    private val despesasDividas = listOf(
        ItemDespesa(
            icone = Icons.Outlined.AttachMoney, nome = "Empréstimos",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.CreditCard, nome = "Cartão de Crédito",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.Apartment, nome = "Hipoteca",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(icone = Icons.Outlined.AccountBalance, nome = "Débitos Diversos"),
    )

    private val despesasSaude = listOf(
        ItemDespesa(icone = Icons.Outlined.LocalHospital, nome = "Consultas"),
        ItemDespesa(
            icone = Icons.Outlined.LocalPharmacy, nome = "Medicamentos",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
        ItemDespesa(Icons.Outlined.Healing, "Exames"),
        ItemDespesa(
            icone = Icons.Outlined.HealthAndSafety, nome = "Checkup",
            recorrencia = Recorrencia(Frequencia.ANUAL)
        ),
        ItemDespesa(
            icone = Icons.Outlined.Spa, nome = "Tratamentos",
            recorrencia = Recorrencia(Frequencia.MENSAL)
        ),
    )


    val despesas = listOf(
        despesasEssenciais,
        despesasTransporte,
        despesasAlimentacao,
        despesasEntretenimento,
        despesasSaude,
        despesasEducacaoo,
        despesasDividas,
    )

    val objetivos: List<Objetivo> = listOf(
        Objetivo(descricao = "Reserva de Emergência", valor = BigDecimal.ZERO),
        Objetivo(descricao = "Plano de Aposentadoria", valor = BigDecimal.ZERO),
        Objetivo(descricao = "Casa Própria", valor = BigDecimal.ZERO),
        Objetivo(descricao = "Sonho de Consumo", valor = BigDecimal.ZERO),
        Objetivo(descricao = "Quitar minhas Dívidas", valor = BigDecimal.ZERO),
    )

    val defaultDespesas = mapOf(
        Categoria(nome = "Alimentação") to despesasAlimentacao,
        Categoria(nome = "Essenciais") to despesasEssenciais,
        Categoria(nome = "Educação") to despesasEducacaoo,
        Categoria(nome = "Entretenimento") to despesasEntretenimento,
        Categoria(nome = "Transporte") to despesasTransporte,
        Categoria(nome = "Dívidas") to despesasDividas,
        Categoria(nome = "Saúde") to despesasSaude,
    )

}