package com.cmc.curtaincall.feature.mypage

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
import com.cmc.curtaincall.feature.mypage.editprofile.MyPageProfileEditScreen
import com.cmc.curtaincall.feature.mypage.notice.MyPageNoticeDetailScreen
import com.cmc.curtaincall.feature.mypage.notice.MyPageNoticeScreen
import com.cmc.curtaincall.feature.mypage.party.participation.MyPageParticipationScreen
import com.cmc.curtaincall.feature.mypage.party.recruitment.MyPageRecruitmentScreen
import com.cmc.curtaincall.feature.mypage.saveperformance.MyPageSavedPerformanceScreen
import com.cmc.curtaincall.feature.mypage.setting.MyPageSettingScreen
import com.cmc.curtaincall.feature.mypage.terms.MyPagePrivacyTermsScreen
import com.cmc.curtaincall.feature.mypage.terms.MyPageServiceTermsScreen
import com.cmc.curtaincall.feature.mypage.write.MyPageWriteScreen
import com.cmc.curtaincall.feature.partymember.PartyMemberDestination
import com.cmc.curtaincall.feature.performance.PerformanceDestination

private const val MYPAGE_GRAPH = "mypage_graph"
const val MYPAGE = "mypage"
private const val MYPAGE_LABEL = "MY"
private const val MYPAGE_PROFILE_EDIT = "mypage_profile_edit"
private const val MYPAGE_SAVED_PERFORMANCE = "mypage_saved_performance"
private const val MYPAGE_WRITE = "mypage_write"
private const val MYPAGE_SETTING = "mypage_setting"
private const val MYPAGE_NOTICE = "mypage_notice"
private const val MYPAGE_NOTICE_DETAIL = "mypage_notice_detail"
private const val MYPAGE_RECRUITMENT = "mypage_recruitment"
private const val MYPAGE_PARTICIPATION = "mypage_participantion"
private const val MYPAGE_PRIVACY_TERMS = "mypage_privacy_terms"
private const val MYPAGE_SERVICE_TERMS = "mypage_service_terms"

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

    object Notice : MyPageDestination {
        override val route = MYPAGE_NOTICE
    }

    object NoticeDetail : MyPageDestination {
        override val route = MYPAGE_NOTICE_DETAIL
    }

    object Recruitment : MyPageDestination {
        override val route = MYPAGE_RECRUITMENT
    }

    object Participation : MyPageDestination {
        override val route = MYPAGE_PARTICIPATION
    }

    object PrivacyTerms : MyPageDestination {
        override val route = MYPAGE_PRIVACY_TERMS
    }

    object ServiceTerms : MyPageDestination {
        override val route = MYPAGE_SERVICE_TERMS
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
                onNavigatePrivacyTerms = {
                    navHostController.navigate(MyPageDestination.PrivacyTerms.route)
                },
                onNavigateServiceTerms = {
                    navHostController.navigate(MyPageDestination.ServiceTerms.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Notice.route) {
            MyPageNoticeScreen(
                onNavigateNoticeDetail = {
                    navHostController.navigate(MyPageDestination.NoticeDetail.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.NoticeDetail.route) {
            MyPageNoticeDetailScreen {
                navHostController.popBackStack()
            }
        }

        composable(MyPageDestination.Recruitment.route) { entry ->
            val parentEntry = remember(entry) { navHostController.getBackStackEntry(MyPageDestination.MyPage.route) }
            MyPageRecruitmentScreen(
                myPageViewModel = hiltViewModel(parentEntry),
                onNavigateRecruitmentDetail = {
                    navHostController.navigate(
                        PartyMemberDestination.Detail.route + "?" +
                            "${PartyMemberDestination.Detail.typeArg}=$it" + "&" +
                            "${PartyMemberDestination.Detail.fromRecruitmentArg}=true" + "&" +
                            "${PartyMemberDestination.Detail.fromParticipationArg}={${PartyMemberDestination.Detail.fromParticipationArg}}"
                    )
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(MyPageDestination.Participation.route) {
            MyPageParticipationScreen(
                onNavigateParticipationDetail = {
                    navHostController.navigate(
                        PartyMemberDestination.Detail.route + "?" +
                            "${PartyMemberDestination.Detail.typeArg}=$it" + "&" +
                            "${PartyMemberDestination.Detail.fromRecruitmentArg}=false" + "&" +
                            "${PartyMemberDestination.Detail.fromParticipationArg}=true"
                    )
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(MyPageDestination.PrivacyTerms.route) {
            MyPagePrivacyTermsScreen { navHostController.popBackStack() }
        }

        composable(MyPageDestination.ServiceTerms.route) {
            MyPageServiceTermsScreen { navHostController.popBackStack() }
        }
    }
}
