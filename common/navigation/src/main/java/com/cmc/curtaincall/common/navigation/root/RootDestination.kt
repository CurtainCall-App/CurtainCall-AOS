package com.cmc.curtaincall.common.navigation.root

import com.cmc.curtaincall.common.navigation.NavRouteLabel
import com.cmc.curtaincall.core.base.CurtainCallDestination

sealed interface RootDestination : CurtainCallDestination {
    object Splash : RootDestination {
        override val route = NavRouteLabel.SPLASH
    }

    object OnBoarding : RootDestination {
        override val route = NavRouteLabel.ONBOARDING
    }
}
