package com.cmc.curtaincall.feature.performance

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.performance.ui.PerformanceScreen
import com.cmc.curtaincall.feature.performance.ui.detail.PerformanceDetailScreen
import com.cmc.curtaincall.feature.performance.ui.lostitem.PerformanceLostItemCreateScreen
import com.cmc.curtaincall.feature.performance.ui.lostitem.PerformanceLostItemDetailScreen
import com.cmc.curtaincall.feature.performance.ui.lostitem.PerformanceLostItemScreen
import com.cmc.curtaincall.feature.performance.ui.review.PerformanceReviewCreateScreen
import com.cmc.curtaincall.feature.performance.ui.review.PerformanceReviewScreen
import com.cmc.curtaincall.feature.performance.ui.upload.PerformanceUploadScreen

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
    }

    object Review : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW
    }

    object ReviewCreate : PerformanceDestination {
        override val route = PERFORMANCE_REVIEW_CREATE
    }

    object LostItem : PerformanceDestination {
        override val route = PERFORMANCE_LOST_ITEM
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
                navHostController.navigate(PerformanceDestination.Detail.route)
            }
        }
        composable(route = PerformanceDestination.Detail.route) {
            PerformanceDetailScreen(
                onNavigateReview = {
                    navHostController.navigate(PerformanceDestination.Review.route)
                },
                onNavigateLostItem = {
                    navHostController.navigate(PerformanceDestination.LostItem.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = PerformanceDestination.Review.route) {
            PerformanceReviewScreen(
                onNavigateReviewCreate = {
                    navHostController.navigate(PerformanceDestination.ReviewCreate.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = PerformanceDestination.ReviewCreate.route) {
            PerformanceReviewCreateScreen {
                navHostController.popBackStack()
            }
        }
        composable(route = PerformanceDestination.LostItem.route) {
            PerformanceLostItemScreen(
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
