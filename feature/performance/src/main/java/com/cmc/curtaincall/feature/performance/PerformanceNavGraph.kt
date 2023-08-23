package com.cmc.curtaincall.feature.performance

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.performance.detail.PerformanceDetailScreen
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemCreateScreen
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemDetailScreen
import com.cmc.curtaincall.feature.performance.lostitem.PerformanceLostItemScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewCreateScreen
import com.cmc.curtaincall.feature.performance.review.PerformanceReviewScreen
import com.cmc.curtaincall.feature.performance.upload.PerformanceUploadScreen

private const val PERFORMANCE_GRAPH = "performance_graph"
const val PERFORMANCE = "performance"
private const val PERFORMANCE_LABEL = "작품"
private const val PERFORMANCE_DETAIL = "performance_detail"
private const val PERFORMANCE_REVIEW = "performance_review"
private const val PERFORMANCE_REVIEW_CREATE = "performance_review_create"
private const val PERFORMANCE_LOST_ITEM = "performance_lost_item"
private const val PERFORMANCE_LOST_ITEM_DETAIL = "performance_lost_item_detail"
private const val PERFORMANCE_LOST_ITEM_CREATE = "performance_lost_item_create"
private const val PERFORMANCE_UPLOAD = "performance_upload"

sealed interface PerformanceDestination : CurtainCallDestination {
    object Performance : PerformanceDestination, BottomDestination {
        override val route = PERFORMANCE
        override val icon = R.drawable.ic_performance
        override val selectIcon = R.drawable.ic_performance_sel
        override val label = PERFORMANCE_LABEL
    }

    object Detail : PerformanceDestination {
        override val route = PERFORMANCE_DETAIL
        const val showIdArg = "showId"
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object Review : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW
        const val showIdArg = "showId"
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object ReviewCreate : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW_CREATE
        const val showIdArgs = "showId"
        const val fromMypageArg = "fromMypage"
        const val posterUrlArg = "posterUrl"
        const val genreArg = "genre"
        const val titleArg = "title"
        val routeWithArgs = "$route?" +
            "$showIdArgs={$showIdArgs}&" +
            "$fromMypageArg={$fromMypageArg}&" +
            "$posterUrlArg={$posterUrlArg}&" +
            "$genreArg={$genreArg}&" +
            "$titleArg={$titleArg}"

        val arguments = listOf(
            navArgument(showIdArgs) {
                type = NavType.StringType
            },
            navArgument(fromMypageArg) {
                type = NavType.BoolType
            },
            navArgument(posterUrlArg) {
                type = NavType.StringType
            },
            navArgument(genreArg) {
                type = NavType.StringType
            },
            navArgument(titleArg) {
                type = NavType.StringType
            }
        )
    }

    object LostItem : PerformanceDestination {
        override val route = PERFORMANCE_LOST_ITEM
        const val facilityNameArg = "facilityName"
        val routeWithArgs = "$route/{$facilityNameArg}"

        val arguments = listOf(
            navArgument(facilityNameArg) {
                type = NavType.StringType
            }
        )
    }

    object LostItemDetail : PerformanceDestination {
        override val route = PERFORMANCE_LOST_ITEM_DETAIL
    }

    object LostItemCreate : PerformanceDestination {
        override val route = PERFORMANCE_LOST_ITEM_CREATE
    }

    object Upload : PerformanceDestination {
        override val route = PERFORMANCE_UPLOAD
    }
}

fun NavGraphBuilder.performanceNavGraph(
    navHostController: NavHostController,
    onNavigateHome: () -> Unit
) {
    navigation(startDestination = PerformanceDestination.Performance.route, route = PERFORMANCE_GRAPH) {
        composable(route = PerformanceDestination.Performance.route) {
            PerformanceScreen {
                navHostController.navigate("${PerformanceDestination.Detail.route}/$it")
            }
        }
        composable(
            route = PerformanceDestination.Detail.routeWithArgs,
            arguments = PerformanceDestination.Detail.arguments
        ) { entry ->
            val showIdType = entry.arguments?.getString(PerformanceDestination.Detail.showIdArg) ?: ""
            PerformanceDetailScreen(
                showId = showIdType,
                onNavigateReview = {
                    navHostController.navigate("${PerformanceDestination.Review.route}/$it")
                },
                onNavigateLostItem = {
                    navHostController.navigate("${PerformanceDestination.LostItem.route}/$it")
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
            val showId = entry.arguments?.getString(PerformanceDestination.Review.showIdArg) ?: ""
            PerformanceReviewScreen(
                performanceDetailViewModel = hiltViewModel(parentEntry),
                showId = showId,
                onNavigateReviewCreate = { posterUrl, genre, title ->
                    navHostController.navigate(
                        PerformanceDestination.ReviewCreate.route + "?" +
                            "${PerformanceDestination.ReviewCreate.showIdArgs}=$showId" + "&" +
                            "${PerformanceDestination.ReviewCreate.fromMypageArg}=false" + "&" +
                            "${PerformanceDestination.ReviewCreate.posterUrlArg}=$posterUrl" + "&" +
                            "${PerformanceDestination.ReviewCreate.genreArg}=$genre" + "&" +
                            "${PerformanceDestination.ReviewCreate.titleArg}=$title"
                    )
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
            val showId = entry.arguments?.getString(PerformanceDestination.ReviewCreate.showIdArgs) ?: ""
            val fromMypage = entry.arguments?.getBoolean(PerformanceDestination.ReviewCreate.fromMypageArg) ?: false
            val posterUrl = entry.arguments?.getString(PerformanceDestination.ReviewCreate.posterUrlArg)
            val genre = entry.arguments?.getString(PerformanceDestination.ReviewCreate.genreArg) ?: "PLAY"
            val title = entry.arguments?.getString(PerformanceDestination.ReviewCreate.titleArg) ?: ""

            PerformanceReviewCreateScreen(
                showId = showId,
                fromMypage = fromMypage,
                posterUrl = posterUrl,
                genre = genre,
                title = title,
                onBack = { navHostController.popBackStack() }
            )
        }
        composable(
            route = PerformanceDestination.LostItem.routeWithArgs,
            arguments = PerformanceDestination.LostItem.arguments
        ) { entry ->
            val facilityName = entry.arguments?.getString(PerformanceDestination.LostItem.facilityNameArg) ?: ""
            PerformanceLostItemScreen(
                facilityName = facilityName,
                onNavigateLostItemDetail = {
                    navHostController.navigate(PerformanceDestination.LostItemDetail.route)
                },
                onNavigateLostItemCreate = {
                    navHostController.navigate(PerformanceDestination.LostItemCreate.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = PerformanceDestination.LostItemDetail.route) {
            PerformanceLostItemDetailScreen {
                navHostController.popBackStack()
            }
        }
        composable(route = PerformanceDestination.LostItemCreate.route) {
            PerformanceLostItemCreateScreen(
                fromMyPage = navHostController.previousBackStackEntry?.destination?.route != PerformanceDestination.LostItem.route,
                onNavigateUpload = {
                    navHostController.navigate(PerformanceDestination.Upload.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = PerformanceDestination.Upload.route) {
            PerformanceUploadScreen(
                onNavigateLostItem = {
                    navHostController.navigate(PerformanceDestination.LostItem.route) {
                        popUpTo(PerformanceDestination.LostItem.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateHome = onNavigateHome
            )
        }
    }
}
