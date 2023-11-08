package com.neuralnet.maisfinancas.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.neuralnet.maisfinancas.ui.navigation.graphs.DespesasDestinations
import com.neuralnet.maisfinancas.ui.navigation.graphs.HomeDestinations

val screens = listOf(
    BottomNavItem.Home,
    BottomNavItem.Despesas,
    BottomNavItem.Estatisticas,
    BottomNavItem.Objetivos,
)

val allowedRoutes = listOf(
    *screens.map { it.route }.toTypedArray(),
    DespesasDestinations.DetalhesDespesa.route,
    DespesasDestinations.Overview.route,
    DespesasDestinations.AddDespesa.route,
    HomeDestinations.AddRenda.route,
    HomeDestinations.Saldo.route,
)

@Composable
fun FinancasNavigationBar(navController: NavHostController, navBackStackEntry: NavBackStackEntry?) {
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            BottomNavItem(
                item = screen,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onItemClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Composable
fun RowScope.BottomNavItem(
    item: BottomNavItem,
    selected: Boolean,
    onItemClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onItemClick,
        label = { Text(stringResource(item.title), maxLines = 1, overflow = TextOverflow.Ellipsis) },
        alwaysShowLabel = false,
        icon = {
            Icon(
                imageVector = if (selected) {
                    item.selectedIcon
                } else item.unselectedIcon,
                contentDescription = stringResource(id = item.title)
            )
        }
    )
}
