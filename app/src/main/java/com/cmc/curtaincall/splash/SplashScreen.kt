package com.cmc.curtaincall.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onNavigateOnBoarding: () -> Unit,
    onNavigateOnHome: () -> Unit
) {
    SystemUiStatusBar(Cetacean_Blue)
    LaunchedEffect(true) {
        splashViewModel.effects.collectLatest { effect ->
            when (effect) {
                SplashSideEffect.NeedLogin -> {
                    onNavigateOnBoarding()
                }

                SplashSideEffect.AutoLogin -> {
                    onNavigateOnHome()
                }
            }
        }
    }
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(Cetacean_Blue)
    )
}
