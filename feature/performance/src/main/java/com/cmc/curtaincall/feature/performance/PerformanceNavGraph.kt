package com.cmc.curtaincall.feature.performance

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.performance.ui.PerformanceDetailScreen
import com.cmc.curtaincall.feature.performance.ui.PerformanceReviewScreen

private const val PERFORMANCE_GRAPH = "performance_graph"
const val PERFORMANCE = "performance"
private const val PERFORMANCE_DETAIL = "performance_detail"
private const val PERFORMANCE_REVIEW = "performance_review"

sealed interface PerformanceDestination : CurtainCallDestination {
    object Detail : PerformanceDestination {
        override val route = PERFORMANCE_DETAIL
    }

    object Review : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW
    }
}

fun NavGraphBuilder.performanceNavGraph(navHostController: NavHostController) {
    navigation(startDestination = PerformanceDestination.Detail.route, route = PERFORMANCE_GRAPH) {
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