package com.cmc.curtaincall.feature.mypage.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyPageScreen(
    onNavigateRecruitment: () -> Unit,
    onNavigateParticipant: () -> Unit
) {
    Column {
        Text(
            text = "mypage",
            modifier = Modifier.size(100.dp, 100.dp)
        )

        Button(
            onClick = { onNavigateRecruitment() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "recruitment")
        }

        Button(
            onClick = { onNavigateParticipant() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "participant")
        }
    }
}