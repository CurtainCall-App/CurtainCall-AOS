package com.cmc.curtaincall.common.navigation.destination

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.navigation.NavRouteLabel
import com.cmc.curtaincall.core.navigation.BottomDestination
import com.cmc.curtaincall.core.navigation.CurtainCallDestination

private const val SHOW_LABEL = "작품"
private const val SHOW_ID_ARG = "showId"
private const val REVIEW_ID_ARG = "reviewId"
private const val FACILITY_ID_ARG = "facilityId"
private const val FACILITY_NAME_ARG = "facilityName"
private const val LOST_PROPERTY_ID_ARG = "lostPropertyId"
const val DEFAULT_REVIEW_ID = Int.MIN_VALUE
const val DEFAULT_LOST_PROPERTY_ID = Int.MIN_VALUE

sealed class ShowDestination : CurtainCallDestination {
    object Search : ShowDestination(), BottomDestination {
        override val icon = R.drawable.ic_show
        override val selectIcon = R.drawable.ic_show_sel
        override val label = SHOW_LABEL
        override val route = NavRouteLabel.SHOW_SEARCH
    }

    object Detail : ShowDestination() {
        override val route = NavRouteLabel.SHOW_DETAIL
        const val showIdArg = SHOW_ID_ARG
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object Review : ShowDestination() {
        override val route = NavRouteLabel.SHOW_REVIEW
        const val showIdArg = SHOW_ID_ARG
        val routeWithArgs = "$route/{$showIdArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            }
        )
    }

    object ReviewCreate : ShowDestination() {
        override val route = NavRouteLabel.SHOW_REVIEW_CREATE
        const val showIdArg = SHOW_ID_ARG
        const val reviewIdArg = REVIEW_ID_ARG
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

    object LostProperty : ShowDestination() {
        override val route = NavRouteLabel.SHOW_LOST_PROPERTY
        const val facilityNameArg = FACILITY_NAME_ARG
        val routeWithArgs = "$route/{$facilityNameArg}"
        val arguments = listOf(
            navArgument(facilityNameArg) {
                type = NavType.StringType
            }
        )
    }

    object LostPropertyDetail : ShowDestination() {
        override val route = NavRouteLabel.SHOW_LOST_PROPERTY_DETAIL
        const val lostPropertyIdArg = LOST_PROPERTY_ID_ARG
        val routeWithArg = "$route/{$lostPropertyIdArg}"
        val arguments = listOf(
            navArgument(lostPropertyIdArg) {
                type = NavType.IntType
            }
        )
    }

    object LostPropertyCreate : ShowDestination() {
        override val route = NavRouteLabel.SHOW_LOST_PROPERTY_CREATE
        const val lostPropertyIdArg = LOST_PROPERTY_ID_ARG
        const val facilityIdArg = FACILITY_ID_ARG
        const val facilityNameArg = FACILITY_NAME_ARG
        val routeWithArg = "$route?" +
            "${LostPropertyCreate.lostPropertyIdArg}={${LostPropertyCreate.lostPropertyIdArg}}&" +
            "${LostPropertyCreate.facilityIdArg}={${LostPropertyCreate.facilityIdArg}}&" +
            "${LostPropertyCreate.facilityNameArg}={${LostPropertyCreate.facilityNameArg}}"

        val arguments = listOf(
            navArgument(lostPropertyIdArg) {
                type = NavType.IntType
                defaultValue = DEFAULT_LOST_PROPERTY_ID
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
