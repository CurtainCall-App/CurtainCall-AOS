package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.home.ui.HomeScreen
import com.cmc.curtaincall.feature.performance.PERFORMANCE
import com.cmc.curtaincall.feature.performance.PerformanceDestination
import com.cmc.curtaincall.feature.performance.performanceNavGraph
import com.cmc.curtaincall.feature.performance.ui.PerformanceScreen

private const val HOME_GRAPH = "home_graph"
private const val HOME = "home"

sealed interface HomeDestination : CurtainCallDestination {
    object Home : HomeDestination {
        override val route = HOME
    }

    object Performance : HomeDestination {
        override val route = PERFORMANCE
    }
}

@Composable
fun HomeNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = HomeDestination.Home.route,
        modifier = Modifier.fillMaxSize(),
        route = HOME_GRAPH
    ) {
        composable(HomeDestination.Home.route) {
            HomeScreen(
                onNavigatePerformance = {
                    navHostController.navigate(HomeDestination.Performance.route)
                }
            )
        }

        composable(HomeDestination.Performance.route) {
            PerformanceScreen(
                onNavigateDetail = {
                    navHostController.navigate(PerformanceDestination.Detail.route)
                }
            )
        }

        performanceNavGraph(navHostController)
    }
}