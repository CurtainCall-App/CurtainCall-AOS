package com.cmc.curtaincall.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cmc.curtaincall.core.base.CurtainCallDestination
import com.cmc.curtaincall.feature.auth.ui.LoginScreen
import com.cmc.curtaincall.feature.auth.ui.SignUpScreen

private const val LOGIN = "login"
private const val SIGNUP = "signup"
private const val AUTH_GRAPH = "auth_graph"

object Login : CurtainCallDestination {
    override val route = LOGIN
}

internal object SignUp: CurtainCallDestination{
    override val route = SIGNUP
}

fun NavGraphBuilder.authGraph() {
    navigation(startDestination = Login.route, route = AUTH_GRAPH) {
        composable(route = Login.route) {
            LoginScreen()
        }

        composable(route = SignUp.route) {
            SignUpScreen()
        }
    }
}