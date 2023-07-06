package com.cmc.curtaincall.feature.partymember

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberDetailScreen
import com.cmc.curtaincall.feature.partymember.ui.PartymemberScreen

private const val PARTYMEMBER_GRAPH = "partymember_graph"
const val PARTYMEMBER = "partymember"
private const val PARTYMEMBER_DETAIL = "partymemeber_detail"

sealed interface PartyMemberDestination : CurtainCallDestination {
    object PartyMember : PartyMemberDestination {
        override val route = PARTYMEMBER
    }

    object Detail : PartyMemberDestination {
        override val route = PARTYMEMBER_DETAIL
    }
}

fun NavGraphBuilder.partymemberNavGraph(navHostController: NavHostController) {
    navigation(startDestination = PartyMemberDestination.PartyMember.route, route = PARTYMEMBER_GRAPH) {
        composable(route = PartyMemberDestination.PartyMember.route) {
            PartymemberScreen {
                navHostController.navigate(PartyMemberDestination.Detail.route)
            }
        }

        composable(route = PartyMemberDestination.Detail.route) {
            PartyMemberDetailScreen()
        }
    }
}
