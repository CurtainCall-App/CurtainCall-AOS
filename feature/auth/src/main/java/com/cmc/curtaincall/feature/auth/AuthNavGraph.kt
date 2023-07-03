package com.cmc.curtaincall.feature.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.auth.ui.LoginScreen
import com.cmc.curtaincall.feature.auth.ui.SignUpScreen

private const val AUTH_GRAPH = "auth_graph"
private const val LOGIN = "login"
private const val SIGNUP = "signup"

sealed interface AuthDestination : CurtainCallDestination {
    object Login : AuthDestination {
        override val route = LOGIN
    }

    object SignUp : AuthDestination {
        override val route = SIGNUP
    }
}

fun NavGraphBuilder.authNavGraph() {
    navigation(startDestination = AuthDestination.Login.route, route = AUTH_GRAPH) {
        composable(route = AuthDestination.Login.route) {
            LoginScreen()
        }

        composable(route = AuthDestination.SignUp.route) {
            SignUpScreen()
        }
    }
}