package com.cmc.curtaincall.feature.performance

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.common.navigation.NavGraphLabel
import com.cmc.curtaincall.common.navigation.destination.PerformanceDestination
import com.cmc.curtaincall.feature.performance.detail.PerformanceDetailScreen
import com.cmc.curtaincall.feature.performance.lostitem.create.PerformanceLostItemCreateScreen
import com.cmc.curtaincall.feature.performance.lostitem.screen.PerformanceLostItemDetailScreen
import com.cmc.curtaincall.feature.performance.lostitem.screen.PerformanceLostItemScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewCreateScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewScreen
import com.cmc.curtaincall.feature.performance.search.PerformanceSearchScreen

fun NavGraphBuilder.performanceNavGraph(
    navHostController: NavHostController,
    onNavigateReport: (Int, String) -> Unit
) {
    navigation(
        startDestination = PerformanceDestination.Search.route,
        route = NavGraphLabel.PERFORMANCE
    ) {
        composable(route = PerformanceDestination.Search.route) {
            PerformanceSearchScreen { showId ->
                navHostController.navigate("${PerformanceDestination.Detail.route}/$showId")
            }
        }

        composable(
            route = PerformanceDestination.Detail.routeWithArgs,
            arguments = PerformanceDestination.Detail.arguments
        ) { entry ->
            val showIdArg = entry.arguments?.getString(PerformanceDestination.Detail.showIdArg)
            PerformanceDetailScreen(
                showId = showIdArg,
                onNavigateReview = { showId ->
                    navHostController.navigate("${PerformanceDestination.Review.route}/$showId")
                },
                onNavigateLostProperty = { facilityName ->
                    navHostController.navigate("${PerformanceDestination.LostProperty.route}/$facilityName")
                },
                onNavigateDetail = { showId ->
                    navHostController.navigate("${PerformanceDestination.Detail.route}/$showId")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(
            route = PerformanceDestination.Review.routeWithArgs,
            arguments = PerformanceDestination.Review.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(PerformanceDestination.Detail.routeWithArgs) }
            val showIdArg = entry.arguments?.getString(PerformanceDestination.Review.showIdArg)
            PerformanceReviewScreen(
                performanceDetailViewModel = hiltViewModel(parentEntry),
                showId = showIdArg,
                onNavigateReport = onNavigateReport,
                onNavigateReviewCreate = { reviewId ->
                    navHostController.navigate("${PerformanceDestination.ReviewCreate.route}/$showIdArg/$reviewId")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            route = PerformanceDestination.ReviewCreate.routeWithArgs,
            arguments = PerformanceDestination.ReviewCreate.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(PerformanceDestination.Detail.routeWithArgs) }
            val showIdArg = entry.arguments?.getString(PerformanceDestination.ReviewCreate.showIdArg)
            val reviewIdArg = entry.arguments?.getInt(PerformanceDestination.ReviewCreate.reviewIdArg)
            PerformanceReviewCreateScreen(
                performanceDetailViewModel = hiltViewModel(parentEntry),
                showId = showIdArg,
                reviewId = reviewIdArg,
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = PerformanceDestination.LostProperty.routeWithArgs,
            arguments = PerformanceDestination.LostProperty.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(PerformanceDestination.Detail.routeWithArgs) }
            val facilityNameArg = entry.arguments?.getString(PerformanceDestination.LostProperty.facilityNameArg)
            PerformanceLostItemScreen(
                performanceDetailViewModel = hiltViewModel(parentEntry),
                facilityName = facilityNameArg,
                onNavigateLostItemDetail = { lostPropertyId ->
                    navHostController.navigate("${PerformanceDestination.LostPropertyDetail.route}/$lostPropertyId")
                },
                onNavigateLostItemCreate = { facilityId, facilityName ->
                    navHostController.navigate(
                        "${PerformanceDestination.LostPropertyCreate.route}?" +
                            "${PerformanceDestination.LostPropertyCreate.facilityIdArg}=$facilityId&" +
                            "${PerformanceDestination.LostPropertyCreate.facilityNameArg}=$facilityName"
                    )
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            route = PerformanceDestination.LostPropertyCreate.routeWithArg,
            arguments = PerformanceDestination.LostPropertyCreate.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(PerformanceDestination.LostProperty.routeWithArgs) }
            val lostPropertyIdArg = entry.arguments?.getInt(PerformanceDestination.LostPropertyDetail.lostPropertyIdArg)
            PerformanceLostItemDetailScreen(
                performanceLostItemViewModel = hiltViewModel(parentEntry),
                lostPropertyId = lostPropertyIdArg,
                onNavigateReport = onNavigateReport,
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = PerformanceDestination.LostPropertyCreate.routeWithArg,
            arguments = PerformanceDestination.LostPropertyCreate.arguments
        ) { entry ->
            val lostPropertyIdArg = entry.arguments?.getInt(PerformanceDestination.LostPropertyCreate.lostPropertyIdArg)
            val facilityIdArg = entry.arguments?.getString(PerformanceDestination.LostPropertyCreate.facilityIdArg)
            val facilityNameArg = entry.arguments?.getString(PerformanceDestination.LostPropertyCreate.facilityNameArg)
            PerformanceLostItemCreateScreen(
                lostPropertyId = lostPropertyIdArg,
                facilityId = facilityIdArg,
                facilityName = facilityNameArg,
                onNavigateDetail = {
                    navHostController.navigate("${PerformanceDestination.LostPropertyDetail.route}/$it") {
                        navHostController.popBackStack(
                            route = PerformanceDestination.LostPropertyDetail.routeWithArg,
                            inclusive = true
                        )
                    }
                },
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}
