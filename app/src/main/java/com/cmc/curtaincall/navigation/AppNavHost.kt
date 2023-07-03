package com.cmc.curtaincall.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.auth.authNavGraph
import com.cmc.curtaincall.feature.home.homeNavGraph
import com.cmc.curtaincall.ui.SplashScreen

private const val ROOT_GRAPH = "root_graph"
private const val SPLASH = "splash"

object Splash : CurtainCallDestination {
    override val route = SPLASH
}

@Composable
internal fun AppNavHost(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Splash.route,
        modifier = Modifier.fillMaxSize(),
        route = ROOT_GRAPH
    ) {
        composable(route = Splash.route) {
            SplashScreen()
        }
        authNavGraph()
        homeNavGraph()
    }
}
