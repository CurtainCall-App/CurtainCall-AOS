package com.cmc.curtaincall.feature.mypage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.mypage.ui.MyPageScreen
import com.cmc.curtaincall.feature.mypage.ui.ParticipantScreen
import com.cmc.curtaincall.feature.mypage.ui.RecruitmentScreen

private const val MYPAGE_GRAPH = "mypage_graph"
const val MYPAGE = "mypage"
private const val MYPAGE_RECRUITMENT = "mypage_recruitment"
private const val MYPAGE_PARTICIPANT = "mypage_participant"
private const val MYPAGE_WRITE = "mypage_write"
private const val MYPAGE_SAVE = "mypage_save"
private const val MYPAGE_ANNOUNCEMENT = "mypage_announcement"
private const val MYPAGE_CONTACT = "mypage_contact"
private const val MYPAGE_LOGOUT = "mypage_logout"

sealed interface MyPageDestination : CurtainCallDestination {
    object MyPage : MyPageDestination {
        override val route = MYPAGE
    }

    object Recruitment : MyPageDestination {
        override val route = MYPAGE_RECRUITMENT
    }

    object Participant : MyPageDestination {
        override val route = MYPAGE_PARTICIPANT
    }

    object Write : MyPageDestination {
        override val route = MYPAGE_WRITE
    }

    object Save : MyPageDestination {
        override val route = MYPAGE_SAVE
    }

    object Announcement : MyPageDestination {
        override val route = MYPAGE_ANNOUNCEMENT
    }

    object Contact : MyPageDestination {
        override val route = MYPAGE_CONTACT
    }

    object Logout : MyPageDestination {
        override val route = MYPAGE_LOGOUT
    }
}

fun NavGraphBuilder.mypageNavGraph(navHostController: NavHostController) {
    navigation(startDestination = MyPageDestination.MyPage.route, MYPAGE_GRAPH) {
        composable(MyPageDestination.MyPage.route) {
            MyPageScreen(
                onNavigateRecruitment = {
                    navHostController.navigate(MyPageDestination.Recruitment.route)
                },
                onNavigateParticipant = {
                    navHostController.navigate(MyPageDestination.Participant.route)
                }
            )
        }

        composable(MyPageDestination.Recruitment.route) {
            RecruitmentScreen()
        }

        composable(MyPageDestination.Participant.route) {
            ParticipantScreen()
        }
    }
}