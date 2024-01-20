package com.cmc.curtaincall.common.designsystem.component.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    height: Dp = 1.dp,
    background: Color = Color.Unspecified
) {
    Spacer(
        modifier = modifier
            .height(height)
            .background(background)
    )
}

@Preview
@Composable
private fun Arrangement.HorizontalDividerPreview() {
    CurtainCallTheme {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
    }
}
