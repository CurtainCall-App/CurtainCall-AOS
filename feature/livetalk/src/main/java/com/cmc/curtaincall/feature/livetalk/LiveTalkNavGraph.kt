package com.cmc.curtaincall.feature.livetalk

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
import com.cmc.curtaincall.feature.livetalk.ui.LiveTalkDetailScreen
import com.cmc.curtaincall.feature.livetalk.ui.LiveTalkScreen
import io.getstream.chat.android.client.ChatClient

private const val LIVETALK_GRAPH = "livetalk_graph"
const val LIVETALK = "livetalk"
private const val LIVETALK_DETAIL = "livetalk_detail"

sealed interface LiveTalkDestination : CurtainCallDestination {
    object LiveTalk : LiveTalkDestination, BottomDestination {
        override val route = LIVETALK
        override val icon = R.drawable.ic_livetalk
        override val selectIcon = R.drawable.ic_livetalk
        override val label = ""
    }

    object Detail : LiveTalkDestination {
        override val route = LIVETALK_DETAIL
        const val showIdArg = "showId"
        const val showNameArg = "showName"
        const val showAtArg = "showAt"
        val routeWithArg = "$route/{$showIdArg}/{$showNameArg}/{$showAtArg}"
        val arguments = listOf(
            navArgument(showIdArg) {
                type = NavType.StringType
            },
            navArgument(showNameArg) {
                type = NavType.StringType
            },
            navArgument(showAtArg) {
                type = NavType.StringType
            }
        )
    }
}

fun NavGraphBuilder.livetalkNavGraph(
    navHostController: NavHostController,
    chatClient: ChatClient
) {
    navigation(startDestination = LiveTalkDestination.LiveTalk.route, route = LIVETALK_GRAPH) {
        composable(route = LiveTalkDestination.LiveTalk.route) {
            LiveTalkScreen { showId, showName, showAt ->
                navHostController.navigate("${LiveTalkDestination.Detail.route}/$showId/$showName/$showAt")
            }
        }

        composable(
            route = LiveTalkDestination.Detail.routeWithArg,
            arguments = LiveTalkDestination.Detail.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(LiveTalkDestination.LiveTalk.route) }
            val showId = entry.arguments?.getString(LiveTalkDestination.Detail.showIdArg) ?: ""
            val showName = entry.arguments?.getString(LiveTalkDestination.Detail.showNameArg) ?: ""
            val showAt = entry.arguments?.getString(LiveTalkDestination.Detail.showAtArg) ?: ""
            LiveTalkDetailScreen(
                liveTalkViewModel = hiltViewModel(parentEntry),
                chatClient = chatClient,
                showId = showId,
                showName = showName,
                showAt = showAt,
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}
