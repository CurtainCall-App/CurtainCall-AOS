package com.cmc.curtaincall.feature.home.navigation

import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.navigation.destination.PerformanceDestination
import com.cmc.curtaincall.core.navigation.BottomDestination
import com.cmc.curtaincall.core.navigation.CurtainCallDestination
import com.cmc.curtaincall.feature.home.HomeScreen
import com.cmc.curtaincall.feature.home.guide.GuideScreen
import com.cmc.curtaincall.feature.home.guide.GuideType
import com.cmc.curtaincall.feature.home.report.HomeReportScreen
import com.cmc.curtaincall.feature.livetalk.LiveTalkDestination
import com.cmc.curtaincall.feature.livetalk.livetalkNavGraph
import com.cmc.curtaincall.feature.mypage.MyPageDestination
import com.cmc.curtaincall.feature.mypage.mypageNavGraph
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.partymember.partymemberNavGraph
import com.cmc.curtaincall.feature.performance.performanceNavGraph
import io.getstream.chat.android.client.ChatClient

private const val HOME_GRAPH = "home_graph"
private const val HOME = "home"
private const val HOME_LABEL = "홈"
private const val HOME_GUIDE = "home_guide"
private const val HOME_REPORT = "home_report"

sealed interface HomeDestination : CurtainCallDestination {
    object Home : HomeDestination, BottomDestination {
        override val route = HOME
        override val icon = R.drawable.ic_home
        override val selectIcon = R.drawable.ic_home_sel
        override val label = HOME_LABEL
    }

    object Guide : HomeDestination {
        override val route = HOME_GUIDE
        const val typeArg = "type"
        val routeWithArgs = "$route/{$typeArg}"
        val arguments = listOf(
            navArgument(typeArg) {
                type = NavType.EnumType(GuideType::class.java)
            }
        )
    }

    object Report : HomeDestination {
        override val route = HOME_REPORT
        const val reportIdArg = "reportId"
        const val typeArg = "type"
        val routeWithArgs = "$route/{$reportIdArg}/{$typeArg}"
        val arguments = listOf(
            navArgument(reportIdArg) {
                type = NavType.IntType
            },
            navArgument(typeArg) {
                type = NavType.StringType
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavHost(
    navHostController: NavHostController = rememberNavController(),
    chatClient: ChatClient,
    onLogout: () -> Unit = {},
    onDeleteMember: () -> Unit = {}
) {
    Scaffold(
        bottomBar = { HomeBottomBar(navHostController) },
        floatingActionButton = { HomeFloatingButton(navHostController) },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = HomeDestination.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            route = HOME_GRAPH
        ) {
            composable(route = HomeDestination.Home.route) {
                HomeScreen(
                    chatClient = chatClient,
                    onNavigateGuide = {
                        navHostController.navigate("${HomeDestination.Guide.route}/$it")
                    },
                    onNavigatePerformanceDetail = {
                        navHostController.navigate("${PerformanceDestination.Detail.route}/$it")
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
            composable(
                route = HomeDestination.Guide.routeWithArgs,
                arguments = HomeDestination.Guide.arguments
            ) { entry ->
                val guideType: GuideType? = getGuideType(entry.arguments)
                if (guideType != null) {
                    GuideScreen(
                        guideType = guideType,
                        onBack = { navHostController.popBackStack() }
                    )
                }
            }

            composable(
                route = HomeDestination.Report.routeWithArgs,
                arguments = HomeDestination.Report.arguments
            ) { entry ->
                val reportId = entry.arguments?.getInt(HomeDestination.Report.reportIdArg) ?: 0
                val reportType = entry.arguments?.getString(HomeDestination.Report.typeArg) ?: ""
                HomeReportScreen(
                    reportId = reportId,
                    reportType = reportType,
                    onNavigateHome = {
                        navHostController.navigate(HomeDestination.Home.route) {
                            popUpTo(HomeDestination.Home.route) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    onBack = { navHostController.popBackStack() }
                )
            }

            performanceNavGraph(
                navHostController = navHostController,
                onNavigateReport = { id, type ->
                    navHostController.navigate("${HomeDestination.Report.route}/$id/$type")
                }
            )
            livetalkNavGraph(
                navHostController = navHostController,
                chatClient = chatClient
            )
            partymemberNavGraph(
                navHostController = navHostController,
                chatClient = chatClient,
                onNavigateReport = { id, type ->
                    navHostController.navigate("${HomeDestination.Report.route}/$id/$type")
                }
            )
            mypageNavGraph(
                navHostController = navHostController,
                onLogout = onLogout,
                onDeleteMember = onDeleteMember
            )
        }
    }
}

private fun getGuideType(bundle: Bundle?): GuideType? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle?.getSerializable(HomeDestination.Guide.typeArg, GuideType::class.java)
    } else {
        bundle?.getSerializable(HomeDestination.Guide.typeArg) as? GuideType
    }
