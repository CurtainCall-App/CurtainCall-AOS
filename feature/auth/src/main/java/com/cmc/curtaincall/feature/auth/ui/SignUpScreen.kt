package com.cmc.curtaincall.feature.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(onNavigateHome: () -> Unit) {
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