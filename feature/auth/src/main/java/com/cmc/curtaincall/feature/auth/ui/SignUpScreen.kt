package com.cmc.curtaincall.feature.auth.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.AppBarWithBack
import com.cmc.curtaincall.common.design.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onNavigateHome: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            AppBarWithBack(title = "약관 동의", onBack = onNavigateBack)
        }
    ) {
        Column {
            Text(
                text = "signup",
                modifier = Modifier.size(100.dp, 100.dp)
            )

            Button(
                onClick = { onNavigateHome() },
                modifier = Modifier.size(100.dp, 50.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "home")
            }
        }
    }
}