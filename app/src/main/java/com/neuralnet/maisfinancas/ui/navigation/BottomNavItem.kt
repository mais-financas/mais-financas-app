package com.neuralnet.maisfinancas.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.ui.graphics.vector.ImageVector
import com.neuralnet.maisfinancas.R
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations

sealed class BottomNavItem(
    @StringRes val title: Int,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object Home :
        BottomNavItem(
            title = R.string.home,
            route = HomeDestinations.Home.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        )

    data object Despesas :
        BottomNavItem(
            title = R.string.despesas,
            route = HomeDestinations.DespesasGraph.route,
            selectedIcon = Icons.Rounded.AttachMoney,
            unselectedIcon = Icons.Outlined.AttachMoney,
        )

    data object Estatisticas :
        BottomNavItem(
            title = R.string.estatisticas,
            route = HomeDestinations.Estatisticas.route,
            selectedIcon = Icons.Filled.PieChart,
            unselectedIcon = Icons.Outlined.PieChart,
        )

    data object Objetivos :
        BottomNavItem(
            title = R.string.objetivos,
            route = HomeDestinations.FinancialGoals.route,
            selectedIcon = Icons.Filled.Savings,
            unselectedIcon = Icons.Outlined.Savings,
        )
}