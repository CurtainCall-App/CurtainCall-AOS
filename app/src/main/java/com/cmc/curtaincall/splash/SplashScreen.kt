package com.cmc.curtaincall.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onNavigateOnBoarding: () -> Unit,
    onNavigateOnHome: () -> Unit
) {
    SystemUiStatusBar(Cetacean_Blue)
    LaunchedEffect(true) {
        splashViewModel.isAutoLogin.collectLatest { isAutoLogin ->
            if (isAutoLogin) {
                onNavigateOnHome()
            } else {
                onNavigateOnBoarding()
            }
        }
    }
}
