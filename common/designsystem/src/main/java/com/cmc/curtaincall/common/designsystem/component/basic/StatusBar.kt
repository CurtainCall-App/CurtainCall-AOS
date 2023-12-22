package com.cmc.curtaincall.common.designsystem.component.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiStatusBar(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color)
}
