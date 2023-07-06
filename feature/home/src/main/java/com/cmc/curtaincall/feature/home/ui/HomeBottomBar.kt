package com.cmc.curtaincall.feature.home.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.home.HomeDestination
import com.cmc.curtaincall.feature.livetalk.LiveTalkDestination
import com.cmc.curtaincall.feature.mypage.MyPageDestination
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.performance.PerformanceDestination

@Composable
fun HomeBottomBar(navHostController: NavHostController) {
    val bottomDestinations = listOf(
        HomeDestination,
        PerformanceDestination.Performance,
        LiveTalkDestination.LiveTalk,
        PartyMemberDestination.PartyMember,
        MyPageDestination.MyPage
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hasBottomNavigation = bottomDestinations.any { it.route == currentDestination?.route }

    if (hasBottomNavigation) {
        NavigationBar {
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
    bottomDestination: CurtainCallDestination,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    require(bottomDestination is BottomDestination)
    NavigationBarItem(
        selected = bottomDestination.route == currentDestination?.route,
        onClick = { navHostController.navigate(bottomDestination.route) },
        icon = { Icon(painter = painterResource(bottomDestination.icon), contentDescription = null) }
    )
}
