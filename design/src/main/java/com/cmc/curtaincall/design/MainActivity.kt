package com.cmc.curtaincall.design

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cmc.curtaincall.common.design.theme.NoRippleTheme
import com.cmc.curtaincall.design.ui.theme.CurtainCallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            CurtainCallTheme {
                CurtainCallDesignSystemApp()
            }
        }
    }
}

@Composable
private fun CurtainCallDesignSystemApp() {
    CurtainCallTheme {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost()
            }
        }
    }
}
