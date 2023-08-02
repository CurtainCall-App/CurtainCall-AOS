package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.home.ui.HomeBottomBar
import com.cmc.curtaincall.feature.home.ui.HomeFloatingButton
import com.cmc.curtaincall.feature.home.ui.HomeScreen
import com.cmc.curtaincall.feature.livetalk.LiveTalkDestination
import com.cmc.curtaincall.feature.livetalk.livetalkNavGraph
import com.cmc.curtaincall.feature.mypage.MyPageDestination
import com.cmc.curtaincall.feature.mypage.mypageNavGraph
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.partymember.partymemberNavGraph
import com.cmc.curtaincall.feature.performance.PerformanceDestination
import com.cmc.curtaincall.feature.performance.performanceNavGraph

private const val HOME_GRAPH = "home_graph"
private const val HOME = "home"
private const val HOME_LABEL = "홈"

object HomeDestination : CurtainCallDestination, BottomDestination {
    override val route = HOME
    override val icon = R.drawable.ic_home
    override val selectIcon = R.drawable.ic_home_sel
    override val label = HOME_LABEL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { HomeBottomBar(navHostController) },
        floatingActionButton = { HomeFloatingButton(navHostController) },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = HomeDestination.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            route = HOME_GRAPH
        ) {
            composable(route = HomeDestination.route) {
                HomeScreen(
                    onNavigatePerformance = {
                        navHostController.navigate(PerformanceDestination.Performance.route)
                    },
                    onNavigateLiveTalk = {
                        navHostController.navigate(LiveTalkDestination.LiveTalk.route)
                    },
                    onNavigatePartyMember = {
                        navHostController.navigate(PartyMemberDestination.PartyMember.route)
                    },
                    onNavigateMyPage = {
                        navHostController.navigate(MyPageDestination.MyPage.route)
                    }
                )
            }

            performanceNavGraph(
                navHostController = navHostController,
                onNavigateHome = {
                    navHostController.navigate(HomeDestination.route) {
                        popUpTo(HomeDestination.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
            livetalkNavGraph(navHostController)
            partymemberNavGraph(
                navHostController = navHostController,
                onNavigateHome = {
                    navHostController.navigate(HomeDestination.route) {
                        popUpTo(HomeDestination.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
            mypageNavGraph(navHostController)
        }
    }
}
