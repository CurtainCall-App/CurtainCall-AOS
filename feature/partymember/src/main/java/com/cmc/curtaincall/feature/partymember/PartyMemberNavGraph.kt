package com.cmc.curtaincall.feature.partymember

import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.core.navigation.BottomDestination
import com.cmc.curtaincall.core.navigation.CurtainCallDestination
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberScreen
import com.cmc.curtaincall.feature.partymember.ui.create.screen.PartyMemberCreateScreen
import com.cmc.curtaincall.feature.partymember.ui.detail.PartyMemberDetailScreen
import com.cmc.curtaincall.feature.partymember.ui.list.PartyMemberListScreen
import com.cmc.curtaincall.feature.partymember.ui.livetalk.PartyMemberLiveTalkScreen
import io.getstream.chat.android.client.ChatClient

private const val PARTYMEMBER_GRAPH = "partymember_graph"
const val PARTYMEMBER = "partymember"
private const val PARTYMEMBER_LABEL = "파티원"
private const val PARTYMEMBER_LIST = "partymember_list"
private const val PARTYMEMBER_DETAIL = "partymember_detail"
private const val PARTYMEMBER_CREATE = "partymember_create"
private const val PARTYMEMBER_LIVETALK = "partymember_livetalk"

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
        const val typeArg = "type"
        const val partyIdArg = "partyId"
        const val myWritingArg = "myWriting"
        const val fromRecruitmentArg = "fromRecruitment"
        const val fromParticipationArg = "fromParticipation"
        val routeWithArgs = "$route?" +
            "$partyIdArg={$partyIdArg}&" +
            "$myWritingArg={$myWritingArg}&" +
            "$typeArg={$typeArg}&" +
            "$fromRecruitmentArg={$fromRecruitmentArg}&" +
            "$fromParticipationArg={$fromParticipationArg}"

        val arguments = listOf(
            navArgument(partyIdArg) {
                type = NavType.IntType
            },
            navArgument(myWritingArg) {
                type = NavType.BoolType
            },
            navArgument(typeArg) {
                type = NavType.EnumType(PartyType::class.java)
                defaultValue = PartyType.PERFORMANCE
            },
            navArgument(fromRecruitmentArg) {
                type = NavType.BoolType
                defaultValue = false
            },
            navArgument(fromParticipationArg) {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    }

    object Create : PartyMemberDestination {
        override val route = PARTYMEMBER_CREATE
        const val typeArg = "type"
        val routeWithArgs = "$route/{$typeArg}"
        val arguments = listOf(
            navArgument(typeArg) {
                type = NavType.EnumType(PartyType::class.java)
            }
        )
    }

    object LiveTalk : PartyMemberDestination {
        override val route = PARTYMEMBER_LIVETALK
    }
}

fun NavGraphBuilder.partymemberNavGraph(
    navHostController: NavHostController,
    chatClient: ChatClient,
    onNavigateReport: (Int, String) -> Unit
) {
    navigation(startDestination = PartyMemberDestination.PartyMember.route, route = PARTYMEMBER_GRAPH) {
        composable(route = PartyMemberDestination.PartyMember.route) {
            PartyMemberScreen { navHostController.navigate("${PartyMemberDestination.List.route}/$it") }
        }

        composable(
            route = PartyMemberDestination.List.routeWithArgs,
            arguments = PartyMemberDestination.List.arguments
        ) { entry ->
            val partyType: PartyType? = getPartyType(entry.arguments)
            if (partyType != null) {
                PartyMemberListScreen(
                    partyType = partyType,
                    onNavigateDetail = { partyType, partyId, myWriting ->
                        navHostController.navigate(
                            PartyMemberDestination.Detail.route + "?" +
                                "${PartyMemberDestination.Detail.partyIdArg}=$partyId" + "&" +
                                "${PartyMemberDestination.Detail.typeArg}=$partyType" + "&" +
                                "${PartyMemberDestination.Detail.myWritingArg}=$myWriting" + "&" +
                                "${PartyMemberDestination.Detail.fromRecruitmentArg}=false" + "&" +
                                "${PartyMemberDestination.Detail.fromParticipationArg}=false"
                        )
                    },
                    onNavigateCreate = { navHostController.navigate("${PartyMemberDestination.Create.route}/$it") },
                    onBack = { navHostController.popBackStack() }
                )
            }
        }

        composable(
            route = PartyMemberDestination.Detail.routeWithArgs,
            arguments = PartyMemberDestination.Detail.arguments
        ) { entry ->
            val partyType: PartyType? = getPartyType(entry.arguments)
            val partyId = entry.arguments?.getInt(PartyMemberDestination.Detail.partyIdArg) ?: Int.MIN_VALUE
            val myWriting = entry.arguments?.getBoolean(PartyMemberDestination.Detail.myWritingArg) ?: false
            val fromRecruitment = entry.arguments?.getBoolean(PartyMemberDestination.Detail.fromRecruitmentArg) ?: false
            val fromParticipation = entry.arguments?.getBoolean(PartyMemberDestination.Detail.fromParticipationArg) ?: false
            if (partyType != null) {
                PartyMemberDetailScreen(
                    partyId = partyId,
                    myWriting = myWriting,
                    fromRecruitment = fromRecruitment,
                    fromParticipation = fromParticipation,
                    partyType = partyType,
                    onNavigateReport = onNavigateReport,
                    onNavigateLiveTalk = { navHostController.navigate(PartyMemberDestination.LiveTalk.route) },
                    onBack = { navHostController.popBackStack() }
                )
            } else {
                PartyMemberDetailScreen(
                    partyId = partyId,
                    myWriting = myWriting,
                    fromRecruitment = fromRecruitment,
                    fromParticipation = fromParticipation,
                    partyType = PartyType.PERFORMANCE,
                    onNavigateReport = onNavigateReport,
                    onNavigateLiveTalk = { navHostController.navigate(PartyMemberDestination.LiveTalk.route) },
                    onBack = { navHostController.popBackStack() }
                )
            }
        }

        composable(
            route = PartyMemberDestination.Create.routeWithArgs,
            arguments = PartyMemberDestination.Create.arguments
        ) { entry ->
            val partyType: PartyType? = getPartyType(entry.arguments)
            if (partyType != null) {
                PartyMemberCreateScreen(
                    partyType = partyType,
                    onBack = { navHostController.popBackStack() }
                )
            }
        }

        composable(route = PartyMemberDestination.LiveTalk.route) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(PartyMemberDestination.Detail.routeWithArgs) }
            PartyMemberLiveTalkScreen(
                partyMemberDetailViewModel = hiltViewModel(parentEntry),
                chatClient = chatClient,
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}

fun getPartyType(bundle: Bundle?): PartyType? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle?.getSerializable(PartyMemberDestination.List.typeArg, PartyType::class.java)
    } else {
        bundle?.getSerializable(PartyMemberDestination.List.typeArg) as? PartyType?
    }
