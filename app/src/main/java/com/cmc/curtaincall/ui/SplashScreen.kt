package com.cmc.curtaincall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onNavigateOnBoarding: () -> Unit,
    onNavigateOnHome: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    LaunchedEffect(true) {
        splashViewModel.effects.collectLatest { effect ->
            if (effect == SplashSideEffect.AutoLogin) {
                onNavigateOnHome()
            } else {
                onNavigateOnBoarding()
            }
        }
    }
    Spacer(
        Modifier
            .fillMaxSize()
            .background(Cetacean_Blue)
    )
}
