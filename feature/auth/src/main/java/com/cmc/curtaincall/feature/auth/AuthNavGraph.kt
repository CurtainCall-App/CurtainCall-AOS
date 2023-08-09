package com.cmc.curtaincall.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.auth.ui.LoginScreen
import com.cmc.curtaincall.feature.auth.ui.SignUpInputScreen
import com.cmc.curtaincall.feature.auth.ui.SignUpTermsScreen
import com.cmc.curtaincall.feature.auth.ui.WelComeScreen
import com.cmc.curtaincall.feature.home.HomeDestination

private const val AUTH_GRAPH = "auth_graph"
private const val LOGIN = "login"
private const val SIGNUP_TERMS = "signup_terms"
private const val SIGNUP_INPUT = "signup_input"
private const val WELCOME = "welcome"

sealed interface AuthDestination : CurtainCallDestination {
    object Login : AuthDestination {
        override val route = LOGIN
    }

    object SignUpTerms : AuthDestination {
        override val route = SIGNUP_TERMS
    }

    object SignUpInput : AuthDestination {
        override val route = SIGNUP_INPUT
    }

    object Welcome : AuthDestination {
        override val route = WELCOME
    }
}

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(startDestination = AuthDestination.Login.route, route = AUTH_GRAPH) {
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
