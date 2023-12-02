package com.cmc.curtaincall.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.common.navigation.NavGraphLabel
import com.cmc.curtaincall.common.navigation.destination.AuthDestination
import com.cmc.curtaincall.feature.auth.login.LoginScreen
import com.cmc.curtaincall.feature.auth.signup.screen.SignUpInputScreen
import com.cmc.curtaincall.feature.auth.signup.screen.SignUpTermsScreen
import com.cmc.curtaincall.feature.auth.welcome.WelComeScreen
import com.cmc.curtaincall.feature.home.navigation.HomeDestination

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = AuthDestination.Login.route,
        route = NavGraphLabel.AUTH
    ) {
        composable(route = AuthDestination.Login.route) {
            LoginScreen(
                onNavigateSignUpTerms = {
                    navHostController.navigate(AuthDestination.SignUpTerms.route)
                },
                onNavigateHome = {
                    navHostController.navigate(HomeDestination.Home.route) {
                        popUpTo(AuthDestination.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AuthDestination.SignUpTerms.route) {
            SignUpTermsScreen(
                onNavigateSignUpInput = {
                    navHostController.navigate(AuthDestination.SignUpInput.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpInput.route) {
            SignUpInputScreen(
                onNavigateWelcome = {
                    navHostController.navigate(AuthDestination.Welcome.route) {
                        popUpTo(AuthDestination.SignUpInput.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.Welcome.route) {
            WelComeScreen {
                navHostController.navigate(HomeDestination.Home.route) {
                    popUpTo(AuthDestination.Login.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}
