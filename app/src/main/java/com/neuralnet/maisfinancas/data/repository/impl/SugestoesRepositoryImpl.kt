package com.neuralnet.maisfinancas.data.repository.impl

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
import androidx.compose.material.icons.outlined.ChildCare
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
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.data.datasource.SugestoesDatasource
import com.neuralnet.maisfinancas.data.repository.DespesaRepository
import com.neuralnet.maisfinancas.data.repository.SugestoesRepository
import com.neuralnet.maisfinancas.model.despesa.Categoria
import com.neuralnet.maisfinancas.ui.screens.setup.ItemDespesa
import kotlinx.coroutines.flow.first

class SugestoesRepositoryImpl(
    private val despesaRepository: DespesaRepository,
) : SugestoesRepository {

    override suspend fun getDespesasComuns(): Map<Categoria, List<ItemDespesa>> {
        val categorias = despesaRepository.getCategorias().first()

        return categorias.zip(SugestoesDatasource.despesas).toMap()
    }
}
