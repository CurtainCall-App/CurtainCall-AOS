package com.cmc.curtaincall.feature.mypage

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.navigation.destination.ShowDestination
import com.cmc.curtaincall.core.navigation.BottomDestination
import com.cmc.curtaincall.core.navigation.CurtainCallDestination
import com.cmc.curtaincall.feature.mypage.editprofile.MyPageProfileEditScreen
import com.cmc.curtaincall.feature.mypage.main.MyPageScreen
import com.cmc.curtaincall.feature.mypage.notice.MyPageNoticeDetailScreen
import com.cmc.curtaincall.feature.mypage.notice.MyPageNoticeScreen
import com.cmc.curtaincall.feature.mypage.party.participation.MyPageParticipationScreen
import com.cmc.curtaincall.feature.mypage.party.recruitment.MyPageRecruitmentScreen
import com.cmc.curtaincall.feature.mypage.question.MyPageQuestionScreen
import com.cmc.curtaincall.feature.mypage.saveperformance.MyPageSavedPerformanceScreen
import com.cmc.curtaincall.feature.mypage.setting.MyPageDeleteMemberScreen
import com.cmc.curtaincall.feature.mypage.setting.MyPageSettingScreen
import com.cmc.curtaincall.feature.mypage.write.MyPageWriteScreen
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination2

private const val MYPAGE_GRAPH = "mypage_graph"
const val MYPAGE = "mypage"
private const val MYPAGE_LABEL = "MY"
private const val MYPAGE_PROFILE_EDIT = "mypage_profile_edit"
private const val MYPAGE_SAVED_PERFORMANCE = "mypage_saved_performance"
private const val MYPAGE_WRITE = "mypage_write"
private const val MYPAGE_SETTING = "mypage_setting"
private const val MYPAGE_DELETE_MEMBER = "mypage_delete_member"
private const val MYPAGE_NOTICE = "mypage_notice"
private const val MYPAGE_NOTICE_DETAIL = "mypage_notice_detail"
private const val MYPAGE_RECRUITMENT = "mypage_recruitment"
private const val MYPAGE_PARTICIPATION = "mypage_participantion"
private const val MYPAGE_QUESTIONS = "mypage_questions"

sealed interface MyPageDestination : CurtainCallDestination {
    object MyPage : MyPageDestination, BottomDestination {
        override val route = MYPAGE
        override val icon = R.drawable.ic_my
        override val selectIcon = R.drawable.ic_my_sel
        override val label = MYPAGE_LABEL
    }

    object ProfileEdit : MyPageDestination {
        override val route = MYPAGE_PROFILE_EDIT
        const val profileUrlArg = "profileUrl"
        val routeWithArgs = "$route?" +
            "$profileUrlArg={$profileUrlArg}"
        val arguments = listOf(
            navArgument(profileUrlArg) {
                type = NavType.StringType
            }
        )
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

    object DeleteMember : MyPageDestination {
        override val route = MYPAGE_DELETE_MEMBER
    }

    object Notice : MyPageDestination {
        override val route = MYPAGE_NOTICE
    }

    object NoticeDetail : MyPageDestination {
        override val route = MYPAGE_NOTICE_DETAIL
        const val noticeIdArg = "noticeId"
        val routeWithArgs = "$route/{$noticeIdArg}"
        val arguments = listOf(
            navArgument(noticeIdArg) {
                type = NavType.IntType
            }
        )
    }

    object Recruitment : MyPageDestination {
        override val route = MYPAGE_RECRUITMENT
    }

    object Participation : MyPageDestination {
        override val route = MYPAGE_PARTICIPATION
    }

    object Questions : MyPageDestination {
        override val route = MYPAGE_QUESTIONS
    }
}

fun NavGraphBuilder.mypageNavGraph(
    navHostController: NavHostController,
    onLogout: () -> Unit,
    onDeleteMember: () -> Unit
) {
    navigation(startDestination = MyPageDestination.MyPage.route, MYPAGE_GRAPH) {
        composable(MyPageDestination.MyPage.route) {
            MyPageScreen(
                onNavigateSetting = {
                    navHostController.navigate(MyPageDestination.Setting.route)
                },
                onNavigateProfileEdit = {
                    navHostController.navigate(
                        MyPageDestination.ProfileEdit.route + "?" +
                            "${MyPageDestination.ProfileEdit.profileUrlArg}=$it"
                    )
                },
                onNavigateRecruitment = {
                    navHostController.navigate(MyPageDestination.Recruitment.route)
                },
                onNavigateParticipation = {
                    navHostController.navigate(MyPageDestination.Participation.route)
                },
                onNavigateSavedPerformance = {
                    navHostController.navigate(MyPageDestination.SavedPerformance.route)
                },
                onNavigateWrite = {
                    navHostController.navigate(MyPageDestination.Write.route)
                },
                onNavigateAnnouncement = {
                    navHostController.navigate(MyPageDestination.Notice.route)
                },
                onNavigateQuestion = {
                    navHostController.navigate(MyPageDestination.Questions.route)
                }
            )
        }

        composable(
            route = MyPageDestination.ProfileEdit.routeWithArgs,
            arguments = MyPageDestination.ProfileEdit.arguments
        ) { entry ->
            val profileUrl = entry.arguments?.getString(MyPageDestination.ProfileEdit.profileUrlArg)
            MyPageProfileEditScreen(
                profileUrl = profileUrl,
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.SavedPerformance.route) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(MyPageDestination.MyPage.route) }
            MyPageSavedPerformanceScreen(
                myPageViewModel = hiltViewModel(parentEntry),
                onNavigateShowDetail = {
                    navHostController.navigate("${ShowDestination.Detail.route}/$it")
                },
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(MyPageDestination.Write.route) { entry ->
            MyPageWriteScreen(
                onNavigateReviewEdit = { showId, reviewId ->
                    navHostController.navigate("${ShowDestination.ReviewCreate.route}/$showId/$reviewId")
                },
                onNavigateLostItemEdit = { lostPropertyId, facilityId, facilityName ->
                    navHostController.navigate(
                        "${ShowDestination.LostPropertyCreate.route}?" +
                            "${ShowDestination.LostPropertyCreate.lostPropertyIdArg}=$lostPropertyId&" +
                            "${ShowDestination.LostPropertyCreate.facilityIdArg}=$facilityId&" +
                            "${ShowDestination.LostPropertyCreate.facilityNameArg}=$facilityName"
                    )
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Setting.route) {
            MyPageSettingScreen(
                onLogout = onLogout,
                onNavigateDeleteMember = {
                    navHostController.navigate(MyPageDestination.DeleteMember.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.DeleteMember.route) {
            MyPageDeleteMemberScreen(
                onDeleteMember = onDeleteMember,
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(MyPageDestination.Notice.route) {
            MyPageNoticeScreen(
                onNavigateNoticeDetail = {
                    navHostController.navigate("${MyPageDestination.NoticeDetail.route}/$it")
                },
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(
            route = MyPageDestination.NoticeDetail.routeWithArgs,
            arguments = MyPageDestination.NoticeDetail.arguments
        ) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(MyPageDestination.Notice.route) }
            val noticeId = entry.arguments?.getInt(MyPageDestination.NoticeDetail.noticeIdArg) ?: 0
            MyPageNoticeDetailScreen(
                myPageNoticeViewModel = hiltViewModel(parentEntry),
                noticeId = noticeId,
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(MyPageDestination.Recruitment.route) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(MyPageDestination.MyPage.route) }
            MyPageRecruitmentScreen(
                myPageViewModel = hiltViewModel(parentEntry),
                onNavigateRecruitmentDetail = { partyType, partyId ->
                    navHostController.navigate(
                        PartyMemberDestination2.Detail.route + "?" +
                            "${PartyMemberDestination2.Detail.partyIdArg}=$partyId" + "&" +
                            "${PartyMemberDestination2.Detail.typeArg}=$partyType" + "&" +
                            "${PartyMemberDestination2.Detail.myWritingArg}=true" + "&" +
                            "${PartyMemberDestination2.Detail.fromRecruitmentArg}=true" + "&" +
                            "${PartyMemberDestination2.Detail.fromParticipationArg}=false"
                    )
                },
                onNavigatePartyMember = {
                    navHostController.navigate("${PartyMemberDestination2.List.route}/$it")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Participation.route) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(MyPageDestination.MyPage.route) }
            MyPageParticipationScreen(
                myPageViewModel = hiltViewModel(parentEntry),
                onNavigateParticipationDetail = { partyType, partyId ->
                    navHostController.navigate(
                        PartyMemberDestination2.Detail.route + "?" +
                            "${PartyMemberDestination2.Detail.partyIdArg}=$partyId" + "&" +
                            "${PartyMemberDestination2.Detail.typeArg}=$partyType" + "&" +
                            "${PartyMemberDestination2.Detail.myWritingArg}=false" + "&" +
                            "${PartyMemberDestination2.Detail.fromRecruitmentArg}=false" + "&" +
                            "${PartyMemberDestination2.Detail.fromParticipationArg}=true"
                    )
                },
                onNavigatePartyMember = {
                    navHostController.navigate("${PartyMemberDestination2.List.route}/$it")
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Questions.route) {
            MyPageQuestionScreen { navHostController.popBackStack() }
        }
    }
}
