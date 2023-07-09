package com.cmc.curtaincall.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.Gunmetal
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateOnBoarding: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Gunmetal)

    LaunchedEffect(systemUiController) {
        delay(1000)
        onNavigateOnBoarding()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gunmetal),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.splash_logo_image_description)
        )
    }
}
