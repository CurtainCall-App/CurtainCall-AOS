package com.cmc.curtaincall.feature.home.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.feature.home.HomeDestination

@Composable
fun HomeBottomBar(navHostController: NavHostController) {
    val bottomDestinations = listOf(
        HomeDestination.Home,
        HomeDestination.Performance,
        HomeDestination.LiveTalk,
        HomeDestination.PartyMember
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hasBottomNavigation = bottomDestinations.any { it.route == currentDestination?.route }

    if (hasBottomNavigation) {
        BottomNavigation {
            bottomDestinations.forEach { bottomDestination ->
                HomeBottomBarItem(
                    bottomDestination = bottomDestination,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }
}

@Composable
fun RowScope.HomeBottomBarItem(
    bottomDestination: BottomDestination,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    BottomNavigationItem(
        selected = bottomDestination.route == currentDestination?.route,
        onClick = {
        },
        icon = {
            Icon(painter = painterResource(bottomDestination.icon), contentDescription = null)
        }
    )
}
