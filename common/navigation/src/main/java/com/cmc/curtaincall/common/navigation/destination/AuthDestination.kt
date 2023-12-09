package com.cmc.curtaincall.common.navigation.destination

import com.cmc.curtaincall.common.navigation.NavRouteLabel
import com.cmc.curtaincall.core.navigation.CurtainCallDestination

sealed class AuthDestination : CurtainCallDestination {
    object Login : AuthDestination() {
        override val route = NavRouteLabel.LOGIN
    }

    object SignUpTerms : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_TERMS
    }

    object SignUpInput : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_INPUT
    }

    object Welcome : AuthDestination() {
        override val route = NavRouteLabel.WELCOME
    }
}
