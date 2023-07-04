package com.cmc.curtaincall.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(
    onNavigateLoginUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    Column {
        Text(
            text = "splash",
            modifier = Modifier.size(100.dp, 100.dp)
        )

        Button(
            onClick = { onNavigateLoginUp() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "login")
        }

        Button(
            onClick = { onNavigateHome() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "home")
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        onNavigateLoginUp = {},
        onNavigateHome = {}
    )
}