package com.cmc.curtaincall.feature.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ParticipantScreen() {
    Column {
        Text(
            text = "participant",
            modifier = Modifier.size(100.dp, 100.dp)
        )
    }
}
