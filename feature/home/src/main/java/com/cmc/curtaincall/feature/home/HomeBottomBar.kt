package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.gmarketsans
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.livetalk.LiveTalkDestination
import com.cmc.curtaincall.feature.mypage.MyPageDestination
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.performance.PerformanceDestination

@Composable
fun HomeBottomBar(navHostController: NavHostController) {
    val bottomDestinations = listOf(
        HomeDestination.Home,
        PerformanceDestination.Performance,
        LiveTalkDestination.LiveTalk,
        PartyMemberDestination.PartyMember,
        MyPageDestination.MyPage
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hasBottomNavigation = bottomDestinations.any { it.route == currentDestination?.route }

    if (hasBottomNavigation) {
        NavigationBar(containerColor = White) {
            bottomDestinations.forEach { bottomDestination ->
                if (bottomDestination.route == LiveTalkDestination.LiveTalk.route) {
                    Spacer(Modifier.weight(1f))
                } else {
                    HomeBottomBarItem(
                        bottomDestination = bottomDestination,
                        currentDestination = currentDestination,
                        navHostController = navHostController
                    )
                }
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

    val selected = bottomDestination.route == currentDestination?.route
    NavigationBarItem(
        selected = selected,
        onClick = { navHostController.navigate(bottomDestination.route) },
        icon = {
            Icon(
                painter = painterResource(
                    if (selected) {
                        bottomDestination.selectIcon
                    } else {
                        bottomDestination.icon
                    }
                ),
                contentDescription = null,
                tint = if (selected) Cetacean_Blue else Cetacean_Blue.copy(alpha = 0.2f)
            )
        },
        modifier = Modifier.background(Color.Transparent),
        label = {
            if (currentDestination?.route != LiveTalkDestination.LiveTalk.route) {
                Text(
                    text = bottomDestination.label,
                    color = if (selected) Cetacean_Blue else Cetacean_Blue.copy(alpha = 0.2f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = gmarketsans
                )
            }
        },
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = White
        )
    )
}
