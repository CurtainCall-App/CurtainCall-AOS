package com.cmc.curtaincall.feature.mypage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.BottomDestination
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.feature.mypage.announcemnet.MyPageAnnouncementScreen
import com.cmc.curtaincall.feature.mypage.editprofile.MyPageProfileEditScreen
import com.cmc.curtaincall.feature.mypage.saveperformance.MyPageSavedPerformanceScreen
import com.cmc.curtaincall.feature.mypage.setting.MyPageSettingScreen
import com.cmc.curtaincall.feature.mypage.write.MyPageWriteScreen
import com.cmc.curtaincall.feature.performance.PerformanceDestination

private const val MYPAGE_GRAPH = "mypage_graph"
const val MYPAGE = "mypage"
private const val MYPAGE_LABEL = "MY"
private const val MYPAGE_PROFILE_EDIT = "mypage_profile_edit"
private const val MYPAGE_SAVED_PERFORMANCE = "mypage_saved_performance"
private const val MYPAGE_WRITE = "mypage_write"
private const val MYPAGE_SETTING = "mypage_setting"
private const val MYPAGE_ANNOUNCEMENT = "mypage_announcement"

private const val MYPAGE_RECRUITMENT = "mypage_recruitment"
private const val MYPAGE_PARTICIPANT = "mypage_participant"
private const val MYPAGE_CONTACT = "mypage_contact"
private const val MYPAGE_LOGOUT = "mypage_logout"

sealed interface MyPageDestination : CurtainCallDestination {
    object MyPage : MyPageDestination, BottomDestination {
        override val route = MYPAGE
        override val icon = R.drawable.ic_my
        override val selectIcon = R.drawable.ic_my_sel
        override val label = MYPAGE_LABEL
    }

    object ProfileEdit : MyPageDestination {
        override val route = MYPAGE_PROFILE_EDIT
    }

    object SavedPerformance : MyPageDestination {
        override val route = MYPAGE_SAVED_PERFORMANCE
    }

    object Write : MyPageDestination {
        override val route = MYPAGE_WRITE
    }

    object Setting : MyPageDestination {
        override val route = MYPAGE_SETTING
    }

    object Announcement : MyPageDestination {
        override val route = MYPAGE_ANNOUNCEMENT
    }

    object Recruitment : MyPageDestination {
        override val route = MYPAGE_RECRUITMENT
    }

    object Participant : MyPageDestination {
        override val route = MYPAGE_PARTICIPANT
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
                onNavigateSetting = {
                    navHostController.navigate(MyPageDestination.Setting.route)
                },
                onNavigateProfileEdit = {
                    navHostController.navigate(MyPageDestination.ProfileEdit.route)
                },
                onNavigateSavedPerformance = {
                    navHostController.navigate(MyPageDestination.SavedPerformance.route)
                },
                onNavigateWrite = {
                    navHostController.navigate(MyPageDestination.Write.route)
                },
                onNavigateAnnouncement = {
                    navHostController.navigate(MyPageDestination.Announcement.route)
                }
            )
        }

        composable(MyPageDestination.ProfileEdit.route) {
            MyPageProfileEditScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.SavedPerformance.route) {
            MyPageSavedPerformanceScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Write.route) {
            MyPageWriteScreen(
                onNavigateLostItemEdit = {
                    navHostController.navigate(PerformanceDestination.LostItemCreate.route)
                },
                onNavigateReviewEdit = {
                    navHostController.navigate(PerformanceDestination.ReviewCreate.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Setting.route) {
            MyPageSettingScreen(
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Announcement.route) {
            MyPageAnnouncementScreen(
                onBack = {
                    navHostController.popBackStack()
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
