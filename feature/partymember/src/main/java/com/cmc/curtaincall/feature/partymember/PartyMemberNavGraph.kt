package com.cmc.curtaincall.feature.partymember

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.partymember.ui.PartyMemberScreen
import com.cmc.curtaincall.feature.partymember.ui.create.screen.PartyMemberCreateScreen
import com.cmc.curtaincall.feature.partymember.ui.detail.PartyMemberDetailScreen
import com.cmc.curtaincall.feature.partymember.ui.edit.PartyMemberEditScreen
import com.cmc.curtaincall.feature.partymember.ui.list.PartyMemberListScreen
import com.cmc.curtaincall.feature.partymember.ui.upload.PartyMemberUploadScreen

private const val PARTYMEMBER_GRAPH = "partymember_graph"
const val PARTYMEMBER = "partymember"
private const val PARTYMEMBER_LABEL = "파티원"
private const val PARTYMEMBER_LIST = "partymember_list"
private const val PARTYMEMBER_DETAIL = "partymember_detail"
private const val PARTYMEMBER_CREATE = "partymember_create"
private const val PARTYMEMBER_UPLOAD = "partymember_upload"
private const val PARTYMEMBER_EDIT = "partymember_edit"

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
        const val myWritingArg = "myWriting"
        const val fromRecruitmentArg = "fromRecruitment"
        const val fromParticipationArg = "fromParticipation"
        val routeWithArgs = "$route?" +
            "$myWritingArg={$myWritingArg}&" +
            "$typeArg={$typeArg}&" +
            "$fromRecruitmentArg={$fromRecruitmentArg}&" +
            "$fromParticipationArg={$fromParticipationArg}"

        val arguments = listOf(
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

    object Upload : PartyMemberDestination {
        override val route = PARTYMEMBER_UPLOAD
        const val typeArg = "type"
        val routeWithArgs = "$route/{$typeArg}"
        val arguments = listOf(
            navArgument(typeArg) {
                type = NavType.EnumType(PartyType::class.java)
            }
        )
    }

    object Edit : PartyMemberDestination {
        override val route = PARTYMEMBER_EDIT
        const val typeArg = "type"
        const val fromRecruitmentArg = "fromRecruitment"
        const val fromParticipationArg = "fromParticipation"
        val routeWithArgs = "$route?" +
            "$typeArg={$typeArg}&" +
            "$fromRecruitmentArg={$fromRecruitmentArg}&" +
            "$fromParticipationArg={$fromParticipationArg}"

        val arguments = listOf(
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
}

fun NavGraphBuilder.partymemberNavGraph(
    navHostController: NavHostController,
    onNavigateHome: () -> Unit
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
                    onNavigateDetail = { partyType, myWriting ->
                        navHostController.navigate(
                            PartyMemberDestination.Detail.route + "?" +
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
            val myWriting = entry.arguments?.getBoolean(PartyMemberDestination.Detail.myWritingArg) ?: false
            val fromRecruitment = entry.arguments?.getBoolean(PartyMemberDestination.Detail.fromRecruitmentArg) ?: false
            val fromParticipation = entry.arguments?.getBoolean(PartyMemberDestination.Detail.fromParticipationArg) ?: false
            if (partyType != null) {
                PartyMemberDetailScreen(
                    myWriting = myWriting,
                    fromRecruitment = fromRecruitment,
                    fromParticipation = fromParticipation,
                    partyType = partyType,
                    onNavigateEdit = {
                        navHostController.navigate(
                            PartyMemberDestination.Edit.route + "?" +
                                "${PartyMemberDestination.Edit.typeArg}=$it" + "&" +
                                "${PartyMemberDestination.Edit.fromRecruitmentArg}=$fromRecruitment" + "&" +
                                "${PartyMemberDestination.Edit.fromParticipationArg}=$fromParticipation"
                        )
                    },
                    onBack = { navHostController.popBackStack() }
                )
            } else {
                PartyMemberDetailScreen(
                    fromRecruitment = fromRecruitment,
                    fromParticipation = fromParticipation,
                    partyType = PartyType.PERFORMANCE,
                    onNavigateEdit = {
                        navHostController.navigate(
                            PartyMemberDestination.Edit.route + "?" +
                                "${PartyMemberDestination.Edit.typeArg}=$it" + "&" +
                                "${PartyMemberDestination.Edit.fromRecruitmentArg}=$fromRecruitment" + "&" +
                                "${PartyMemberDestination.Edit.fromParticipationArg}=$fromParticipation"
                        )
                    },
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
                    onNavigateUpload = { navHostController.navigate("${PartyMemberDestination.Upload.route}/$it") },
                    onBack = { navHostController.popBackStack() }
                )
            }
        }

        composable(
            route = PartyMemberDestination.Upload.routeWithArgs,
            arguments = PartyMemberDestination.Upload.arguments
        ) { entry ->
            val partyType: PartyType? = getPartyType(entry.arguments)
            if (partyType != null) {
                PartyMemberUploadScreen(
                    partyType = partyType,
                    onNavigateList = {
                        navHostController.navigate("${PartyMemberDestination.List.route}/$it") {
                            popUpTo(PartyMemberDestination.List.routeWithArgs) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    onNavigateHome = onNavigateHome
                )
            }
        }

        composable(
            route = PartyMemberDestination.Edit.routeWithArgs,
            arguments = PartyMemberDestination.Edit.arguments
        ) { entry ->
            val partyType: PartyType? = getPartyType(entry.arguments)
            val fromRecruitment = entry.arguments?.getBoolean(PartyMemberDestination.Edit.fromRecruitmentArg) ?: false
            val fromParticipation = entry.arguments?.getBoolean(PartyMemberDestination.Edit.fromParticipationArg) ?: false
            if (partyType != null) {
                PartyMemberEditScreen(
                    fromRecruitment = fromRecruitment,
                    fromParticipation = fromParticipation,
                    partyType = partyType,
                    onBack = { navHostController.popBackStack() }
                )
            }
        }
    }
}

fun getPartyType(bundle: Bundle?): PartyType? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle?.getSerializable(PartyMemberDestination.List.typeArg, PartyType::class.java)
    } else {
        bundle?.getSerializable(PartyMemberDestination.List.typeArg) as? PartyType?
    }
