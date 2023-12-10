package com.cmc.curtaincall.common.navigation.destination

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.navigation.NavRouteLabel
import com.cmc.curtaincall.core.navigation.BottomDestination
import com.cmc.curtaincall.core.navigation.CurtainCallDestination

private const val PERFORMANCE_LABEL = "작품"
private const val PERFORMANCE_SHOW_ID_ARG = "showId"
private const val PERFORMANCE_REVIEW_ID_ARG = "reviewId"
private const val PERFORMANCE_FACILITY_ID_ARG = "facilityId"
private const val PERFORMANCE_FACILITY_NAME_ARG = "facilityName"
private const val PERFORMANCE_LOST_PROPERTY_ID_ARG = "lostPropertyId"
const val PERFORMANCE_DEFAULT_REVIEW_ID = Int.MIN_VALUE
const val PERFORMANCE_DEFAULT_LOST_PROPERTY_ID = Int.MIN_VALUE

sealed class PerformanceDestination : CurtainCallDestination {
    object Search : PerformanceDestination(), BottomDestination {
        override val icon = R.drawable.ic_performance
        override val selectIcon = R.drawable.ic_performance_sel
        override val label = PERFORMANCE_LABEL
        override val route = NavRouteLabel.PERFORMANCE_SEARCH
    }

    object Detail : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_DETAIL
        const val showIdArg = PERFORMANCE_SHOW_ID_ARG
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object Review : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_REVIEW
        const val showIdArg = PERFORMANCE_SHOW_ID_ARG
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object ReviewCreate : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_REVIEW_CREATE
        const val showIdArg = PERFORMANCE_SHOW_ID_ARG
        const val reviewIdArg = PERFORMANCE_REVIEW_ID_ARG
        val routeWithArgs = "$route/{$showIdArg}/{$reviewIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            },
            navArgument(reviewIdArg) {
                type = NavType.IntType
            }
        )
    }

    object LostProperty : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_LOST_PROPERTY
        const val facilityNameArg = PERFORMANCE_FACILITY_NAME_ARG
        val routeWithArgs = "$route/{$facilityNameArg}"
        val arguments = listOf(
            navArgument(facilityNameArg) {
                type = NavType.StringType
            }
        )
    }

    object LostPropertyDetail : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_LOST_PROPERTY_DETAIL
        const val lostPropertyIdArg = PERFORMANCE_LOST_PROPERTY_ID_ARG
        val routeWithArg = "$route/{$lostPropertyIdArg}"
        val arguments = listOf(
            navArgument(lostPropertyIdArg) {
                type = NavType.IntType
            }
        )
    }

    object LostPropertyCreate : PerformanceDestination() {
        override val route = NavRouteLabel.PERFORMANCE_LOST_PROPERTY_CREATE
        const val lostPropertyIdArg = PERFORMANCE_LOST_PROPERTY_ID_ARG
        const val facilityIdArg = PERFORMANCE_FACILITY_ID_ARG
        const val facilityNameArg = PERFORMANCE_FACILITY_NAME_ARG
        val routeWithArg = "$route?" +
            "${LostPropertyCreate.lostPropertyIdArg}={${LostPropertyCreate.lostPropertyIdArg}}&" +
            "${LostPropertyCreate.facilityIdArg}={${LostPropertyCreate.facilityIdArg}}&" +
            "${LostPropertyCreate.facilityNameArg}={${LostPropertyCreate.facilityNameArg}}"

        val arguments = listOf(
            navArgument(lostPropertyIdArg) {
                type = NavType.IntType
                defaultValue = PERFORMANCE_DEFAULT_LOST_PROPERTY_ID
            },
            navArgument(facilityIdArg) {
                type = NavType.StringType
            },
            navArgument(facilityNameArg) {
                type = NavType.StringType
            }
        )
    }
}
