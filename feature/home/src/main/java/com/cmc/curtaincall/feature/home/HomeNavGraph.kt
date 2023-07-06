package com.cmc.curtaincall.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.feature.home.ui.HomeBottomBar
import com.cmc.curtaincall.feature.home.ui.HomeScreen
import com.cmc.curtaincall.feature.livetalk.LIVETALK
import com.cmc.curtaincall.feature.livetalk.LiveTalkDestination
import com.cmc.curtaincall.feature.livetalk.livetalkNavGraph
import com.cmc.curtaincall.feature.livetalk.ui.LiveTalkScreen
import com.cmc.curtaincall.feature.mypage.MyPageDestination
import com.cmc.curtaincall.feature.mypage.mypageNavGraph
import com.cmc.curtaincall.feature.partymember.PARTYMEMBER
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.partymember.partymemberNavGraph
import com.cmc.curtaincall.feature.partymember.ui.PartymemberScreen
import com.cmc.curtaincall.feature.performance.PERFORMANCE
import com.cmc.curtaincall.feature.performance.PerformanceDestination
import com.cmc.curtaincall.feature.performance.performanceNavGraph
import com.cmc.curtaincall.feature.performance.ui.PerformanceScreen

private const val HOME_GRAPH = "home_graph"
private const val HOME = "home"

sealed interface HomeDestination : BottomDestination {
    object Home : HomeDestination {
        override val route = HOME
        override val icon = R.drawable.ic_home
    }

    object Performance : HomeDestination {
        override val route = PERFORMANCE
        override val icon = R.drawable.ic_performance
    }

    object LiveTalk : HomeDestination {
        override val route = LIVETALK
        override val icon = R.drawable.ic_my
    }

    object PartyMember : HomeDestination {
        override val route = PARTYMEMBER
        override val icon = R.drawable.ic_partymember
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost(navHostController: NavHostController = rememberNavController()) {
//    Scaffold(
//        bottomBar = { HomeBottomBar(navHostController) }
//    ) { innerPadding ->
//
//    }

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

        composable(HomeDestination.Performance.route) {
            PerformanceScreen(
                onNavigateDetail = {
                    navHostController.navigate(PerformanceDestination.Detail.route)
                }
            )
        }

        performanceNavGraph(navHostController)
        livetalkNavGraph(navHostController)
        partymemberNavGraph(navHostController)
        mypageNavGraph(navHostController)
    }
}
