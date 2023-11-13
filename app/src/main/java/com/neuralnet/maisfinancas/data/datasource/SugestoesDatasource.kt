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
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.LocalDining
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
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa

object SugestoesDatasource {

    private val despesasAlimentacao = listOf(
        ItemDespesa(Icons.Outlined.Coffee, "Café"),
        ItemDespesa(Icons.Outlined.BakeryDining, "Lanches"),
        ItemDespesa(Icons.Outlined.LocalDining, "Almoço"),
        ItemDespesa(Icons.Outlined.BrunchDining, "Jantar"),
        ItemDespesa(Icons.Outlined.Fastfood, "FastFood"),
    )

    private val despesasEssenciais = listOf(
        ItemDespesa(Icons.Outlined.ShoppingCart, "Compras"),
        ItemDespesa(Icons.Outlined.WaterDrop, "Água"),
        ItemDespesa(Icons.Outlined.ElectricBolt, "Energia"),
        ItemDespesa(Icons.Outlined.Wifi, "Internet"),
        ItemDespesa(Icons.Outlined.PhoneAndroid, "Celular"),
    )

    private val despesasEducacaoo = listOf(
        ItemDespesa(Icons.Outlined.Abc, "Esocla"),
        ItemDespesa(Icons.Outlined.Book, "Livros"),
        ItemDespesa(Icons.Outlined.School, "Graduação"),
        ItemDespesa(Icons.Outlined.CollectionsBookmark, "Cursos"),
    )

    private val despesasEntretenimento = listOf(
        ItemDespesa(Icons.Outlined.LocalMovies, "Cinema"),
        ItemDespesa(Icons.Outlined.LiveTv, "Streaming"),
        ItemDespesa(Icons.Outlined.Audiotrack, "Música"),
        ItemDespesa(Icons.Outlined.TheaterComedy, "Teatro"),
        ItemDespesa(Icons.Outlined.VideogameAsset, "Jogos"),
    )

    private val despesasTransporte = listOf(
        ItemDespesa(Icons.Outlined.Cases, "Viagens"),
        ItemDespesa(Icons.Outlined.DirectionsBus, "Transporte Público"),
        ItemDespesa(Icons.Outlined.Build, "Manutenção"),
        ItemDespesa(Icons.Outlined.LocalPolice, "Seguro"),
    )

    private val despesasDividas = listOf(
        ItemDespesa(Icons.Outlined.AttachMoney, "Empréstimos"),
        ItemDespesa(Icons.Outlined.CreditCard, "Cartão de Crédito"),
        ItemDespesa(Icons.Outlined.Apartment, "Hipoteca"),
        ItemDespesa(Icons.Outlined.AccountBalance, "Débitos Diversos"),
    )

    private val despesasSaude = listOf(
        ItemDespesa(Icons.Outlined.LocalHospital, "Consultas"),
        ItemDespesa(Icons.Outlined.LocalPharmacy, "Medicamentos"),
        ItemDespesa(Icons.Outlined.Healing, "Exames"),
        ItemDespesa(Icons.Outlined.Spa, "Tratamentos"),
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