package com.cmc.curtaincall.feature.show

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.common.navigation.NavGraphLabel
import com.cmc.curtaincall.common.navigation.destination.ShowDestination
import com.cmc.curtaincall.domain.type.ReportType
import com.cmc.curtaincall.feature.show.detail.ShowDetailScreen
import com.cmc.curtaincall.feature.show.lostproperty.ShowLostPropertyScreen
import com.cmc.curtaincall.feature.show.lostproperty.create.ShowLostPropertyCreateScreen
import com.cmc.curtaincall.feature.show.lostproperty.detail.ShowLostPropertyDetailScreen
import com.cmc.curtaincall.feature.show.review.ShowReviewCreateScreen
import com.cmc.curtaincall.feature.show.review.ShowReviewScreen
import com.cmc.curtaincall.feature.show.search.ShowSearchScreen

fun NavGraphBuilder.showNavGraph(
    navHostController: NavHostController,
    onNavigateReport: (Int, ReportType) -> Unit
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
                onNavigateLostProperty = { facilityId, facilityName ->
                    navHostController.navigate("${ShowDestination.LostProperty.route}/$facilityId/$facilityName")
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
                onNavigateReviewCreate = { reviewId ->
                    navHostController.navigate("${ShowDestination.ReviewCreate.route}/$showIdArg/$reviewId")
                },
                onNavigateReport = onNavigateReport,
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
            val facilityIdArg = entry.arguments?.getString(ShowDestination.LostProperty.facilityIdArg)
            val facilityNameArg = entry.arguments?.getString(ShowDestination.LostProperty.facilityNameArg)
            ShowLostPropertyScreen(
                facilityId = facilityIdArg,
                facilityName = facilityNameArg,
                onNavigateLostPropertyDetail = { lostPropertyId, fromCreateArg ->
                    navHostController.navigate("${ShowDestination.LostPropertyDetail.route}/$lostPropertyId/$fromCreateArg")
                },
                onNavigateLostPropertyCreate = { facilityId, facilityName ->
                    navHostController.navigate(
                        "${ShowDestination.LostPropertyCreate.route}?" +
                            "${ShowDestination.LostPropertyCreate.facilityIdArg}=$facilityId&" +
                            "${ShowDestination.LostPropertyCreate.facilityNameArg}=$facilityName"
                    )
                },
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = ShowDestination.LostPropertyDetail.routeWithArg,
            arguments = ShowDestination.LostPropertyDetail.arguments
        ) { entry ->
            val lostPropertyIdArg = entry.arguments?.getInt(ShowDestination.LostPropertyDetail.lostPropertyIdArg)
            val fromCreateArg = entry.arguments?.getBoolean(ShowDestination.LostPropertyDetail.fromCreateArg)
            ShowLostPropertyDetailScreen(
                lostPropertyId = lostPropertyIdArg,
                fromCreate = fromCreateArg,
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
            ShowLostPropertyCreateScreen(
                lostPropertyId = lostPropertyIdArg,
                facilityId = facilityIdArg,
                facilityName = facilityNameArg,
                onNavigateDetail = { lostPropertyId, fromCreateArg ->
                    navHostController.navigate("${ShowDestination.LostPropertyDetail.route}/$lostPropertyId/$fromCreateArg") {
                        navHostController.popBackStack(
                            route = ShowDestination.LostPropertyCreate.routeWithArg,
                            inclusive = true
                        )
                    }
                },
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}
