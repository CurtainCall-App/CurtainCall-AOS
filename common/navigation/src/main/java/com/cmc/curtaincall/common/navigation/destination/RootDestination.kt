package com.cmc.curtaincall.common.navigation.destination

import com.cmc.curtaincall.common.navigation.NavRouteLabel
import com.cmc.curtaincall.core.navigation.CurtainCallDestination

sealed interface RootDestination : CurtainCallDestination {
    object Splash : RootDestination {
        override val route = NavRouteLabel.SPLASH
    }

    object OnBoarding : RootDestination {
        override val route = NavRouteLabel.ONBOARDING
    }
}
