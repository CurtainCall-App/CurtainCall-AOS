package com.cmc.curtaincall.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onNavigateOnBoarding: () -> Unit,
    onNavigateOnHome: () -> Unit
) {
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
    SystemUiStatusBar(CurtainCallTheme.colors.primary)
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(CurtainCallTheme.colors.primary)
    )
}
