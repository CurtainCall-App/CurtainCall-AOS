package com.cmc.curtaincall.feature.livetalk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun LiveTalkDetailScreen() {
    Column {
        Text(
            text = "livetalk detail",
            modifier = Modifier.size(150.dp, 100.dp)
        )
    }
}
