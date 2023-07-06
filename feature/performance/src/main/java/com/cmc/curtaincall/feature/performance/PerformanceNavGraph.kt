package com.cmc.curtaincall.feature.performance

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.feature.performance.ui.PerformanceDetailScreen
import com.cmc.curtaincall.feature.performance.ui.PerformanceReviewScreen
import com.cmc.curtaincall.feature.performance.ui.PerformanceScreen

private const val PERFORMANCE_GRAPH = "performance_graph"
const val PERFORMANCE = "performance"
private const val PERFORMANCE_DETAIL = "performance_detail"
private const val PERFORMANCE_REVIEW = "performance_review"

sealed interface PerformanceDestination : CurtainCallDestination {
    object Performance : PerformanceDestination, BottomDestination {
        override val route = PERFORMANCE
        override val icon = R.drawable.ic_performance
    }

    object Detail : PerformanceDestination {
        override val route = PERFORMANCE_DETAIL
    }

    object Review : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW
    }
}

fun NavGraphBuilder.performanceNavGraph(navHostController: NavHostController) {
    navigation(startDestination = PerformanceDestination.Performance.route, route = PERFORMANCE_GRAPH) {
        composable(route = PerformanceDestination.Performance.route) {
            PerformanceScreen {
                navHostController.navigate(PerformanceDestination.Detail.route)
            }
        }

        composable(route = PerformanceDestination.Detail.route) {
            PerformanceDetailScreen(
                onNavigateReview = {
                    navHostController.navigate(PerformanceDestination.Review.route)
                }
            )
        }

        composable(route = PerformanceDestination.Review.route) {
            PerformanceReviewScreen()
        }
    }
}
