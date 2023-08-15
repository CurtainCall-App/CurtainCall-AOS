package com.cmc.curtaincall.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen(onNavigateOnBoarding: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    LaunchedEffect(true) {
        onNavigateOnBoarding()
    }
    Spacer(
        Modifier
            .fillMaxSize()
            .background(Cetacean_Blue)
    )
}
