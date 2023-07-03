package com.cmc.curtaincall.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.home.ui.HomeScreen

private const val HOME_GRAPH = "home_graph"
private const val HOME = "home"

sealed interface HomeDestination : CurtainCallDestination {
    object Home : HomeDestination {
        override val route = HOME
    }
}

fun NavGraphBuilder.homeNavGraph() {
    navigation(startDestination = HomeDestination.Home.route, route = HOME_GRAPH) {
        composable(route = HomeDestination.Home.route) {
            HomeScreen()
        }
    }
}
