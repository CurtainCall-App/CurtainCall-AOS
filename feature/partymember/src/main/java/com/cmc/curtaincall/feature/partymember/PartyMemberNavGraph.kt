package com.cmc.curtaincall.feature.partymember

import android.os.Build
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberDetailScreen
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberListScreen
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberScreen

private const val PARTYMEMBER_GRAPH = "partymember_graph"
const val PARTYMEMBER = "partymember"
private const val PARTYMEMBER_LABEL = "파티원"
private const val PARTYMEMBER_LIST = "partymember_list"
private const val PARTYMEMBER_DETAIL = "partymemeber_detail"

enum class PartyType {
    PERFORMANCE, MEAL, ETC
}

sealed interface PartyMemberDestination : CurtainCallDestination {
    object PartyMember : PartyMemberDestination, BottomDestination {
        override val route = PARTYMEMBER
        override val icon = R.drawable.ic_partymember
        override val selectIcon = R.drawable.ic_partymember_sel
        override val label = PARTYMEMBER_LABEL
    }

    object List : PartyMemberDestination {
        override val route = PARTYMEMBER_LIST
        const val typeArg = "type"
        val routeWithArgs = "$route/{$typeArg}"
        val arguments = listOf(
            navArgument(typeArg) {
                type = NavType.EnumType(PartyType::class.java)
            }
        )
    }

    object Detail : PartyMemberDestination {
        override val route = PARTYMEMBER_DETAIL
    }
}

fun NavGraphBuilder.partymemberNavGraph(navHostController: NavHostController) {
    navigation(startDestination = PartyMemberDestination.PartyMember.route, route = PARTYMEMBER_GRAPH) {
        composable(route = PartyMemberDestination.PartyMember.route) {
            PartyMemberScreen { partyType ->
                navHostController.navigate("${PartyMemberDestination.List.route}/$partyType")
            }
        }

        composable(
            route = PartyMemberDestination.List.routeWithArgs,
            arguments = PartyMemberDestination.List.arguments
        ) {
            val partyType: PartyType? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getSerializable(PartyMemberDestination.List.typeArg, PartyType::class.java)
            } else {
                it.arguments?.getSerializable(PartyMemberDestination.List.typeArg) as? PartyType?
            }

            if (partyType != null) {
                PartyMemberListScreen(
                    partyType = partyType,
                    onNavigateDetail = {
                        navHostController.navigate(PartyMemberDestination.Detail.route)
                    },
                    onBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        }

        composable(route = PartyMemberDestination.Detail.route) {
            PartyMemberDetailScreen()
        }
    }
}
