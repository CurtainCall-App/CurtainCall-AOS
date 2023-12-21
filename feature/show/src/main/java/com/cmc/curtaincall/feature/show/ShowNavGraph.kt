package com.cmc.curtaincall.feature.show

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.common.navigation.NavGraphLabel
import com.cmc.curtaincall.common.navigation.destination.ShowDestination
import com.cmc.curtaincall.feature.show.detail.ShowDetailScreen
import com.cmc.curtaincall.feature.show.lostitem.create.PerformanceLostItemCreateScreen
import com.cmc.curtaincall.feature.show.lostitem.screen.PerformanceLostItemDetailScreen
import com.cmc.curtaincall.feature.show.lostitem.screen.PerformanceLostItemScreen
import com.cmc.curtaincall.feature.show.review.ShowReviewCreateScreen
import com.cmc.curtaincall.feature.show.review.ShowReviewScreen
import com.cmc.curtaincall.feature.show.search.ShowSearchScreen

fun NavGraphBuilder.showNavGraph(
    navHostController: NavHostController,
    onNavigateReport: (Int, String) -> Unit
) {
    navigation(
        startDestination = ShowDestination.Search.route,
        route = NavGraphLabel.SHOW
    ) {
        composable(route = ShowDestination.Search.route) {
            ShowSearchScreen { showId ->
                navHostController.navigate("${ShowDestination.Detail.route}/$showId")
            }
        }

        composable(
            route = ShowDestination.Detail.routeWithArgs,
            arguments = ShowDestination.Detail.arguments
        ) { entry ->
            val showIdArg = entry.arguments?.getString(ShowDestination.Detail.showIdArg)
            ShowDetailScreen(
                showId = showIdArg,
                onNavigateReview = { showId ->
                    navHostController.navigate("${ShowDestination.Review.route}/$showId")
                },
                onNavigateLostProperty = { facilityName ->
                    navHostController.navigate("${ShowDestination.LostProperty.route}/$facilityName")
                },
                onNavigateDetail = { showId ->
                    navHostController.navigate("${ShowDestination.Detail.route}/$showId")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(
            route = ShowDestination.Review.routeWithArgs,
            arguments = ShowDestination.Review.arguments
        ) { entry ->
            val showIdArg = entry.arguments?.getString(ShowDestination.Review.showIdArg)
            ShowReviewScreen(
                showId = showIdArg,
                onNavigateReport = onNavigateReport,
                onNavigateReviewCreate = { reviewId ->
                    navHostController.navigate("${ShowDestination.ReviewCreate.route}/$showIdArg/$reviewId")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            route = ShowDestination.ReviewCreate.routeWithArgs,
            arguments = ShowDestination.ReviewCreate.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(ShowDestination.Detail.routeWithArgs) }
            val showIdArg = entry.arguments?.getString(ShowDestination.ReviewCreate.showIdArg)
            val reviewIdArg = entry.arguments?.getInt(ShowDestination.ReviewCreate.reviewIdArg)
            ShowReviewCreateScreen(
                showDetailViewModel = hiltViewModel(parentEntry),
                showId = showIdArg,
                reviewId = reviewIdArg,
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = ShowDestination.LostProperty.routeWithArgs,
            arguments = ShowDestination.LostProperty.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(ShowDestination.Detail.routeWithArgs) }
            val facilityNameArg = entry.arguments?.getString(ShowDestination.LostProperty.facilityNameArg)
            PerformanceLostItemScreen(
                performanceDetailViewModel = hiltViewModel(parentEntry),
                facilityName = facilityNameArg,
                onNavigateLostItemDetail = { lostPropertyId ->
                    navHostController.navigate("${ShowDestination.LostPropertyDetail.route}/$lostPropertyId")
                },
                onNavigateLostItemCreate = { facilityId, facilityName ->
                    navHostController.navigate(
                        "${ShowDestination.LostPropertyCreate.route}?" +
                            "${ShowDestination.LostPropertyCreate.facilityIdArg}=$facilityId&" +
                            "${ShowDestination.LostPropertyCreate.facilityNameArg}=$facilityName"
                    )
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(
            route = ShowDestination.LostPropertyCreate.routeWithArg,
            arguments = ShowDestination.LostPropertyCreate.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(ShowDestination.LostProperty.routeWithArgs) }
            val lostPropertyIdArg = entry.arguments?.getInt(ShowDestination.LostPropertyDetail.lostPropertyIdArg)
            PerformanceLostItemDetailScreen(
                performanceLostItemViewModel = hiltViewModel(parentEntry),
                lostPropertyId = lostPropertyIdArg,
                onNavigateReport = onNavigateReport,
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = ShowDestination.LostPropertyCreate.routeWithArg,
            arguments = ShowDestination.LostPropertyCreate.arguments
        ) { entry ->
            val lostPropertyIdArg = entry.arguments?.getInt(ShowDestination.LostPropertyCreate.lostPropertyIdArg)
            val facilityIdArg = entry.arguments?.getString(ShowDestination.LostPropertyCreate.facilityIdArg)
            val facilityNameArg = entry.arguments?.getString(ShowDestination.LostPropertyCreate.facilityNameArg)
            PerformanceLostItemCreateScreen(
                lostPropertyId = lostPropertyIdArg,
                facilityId = facilityIdArg,
                facilityName = facilityNameArg,
                onNavigateDetail = {
                    navHostController.navigate("${ShowDestination.LostPropertyDetail.route}/$it") {
                        navHostController.popBackStack(
                            route = ShowDestination.LostPropertyDetail.routeWithArg,
                            inclusive = true
                        )
                    }
                },
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}
