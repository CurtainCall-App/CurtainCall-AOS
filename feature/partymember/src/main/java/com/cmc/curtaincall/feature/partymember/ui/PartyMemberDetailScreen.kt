package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun PartyMemberDetailScreen() {
    Column {
        Text(
            text = "partymember detail",
            modifier = Modifier.size(100.dp, 100.dp)
        )
    }
}