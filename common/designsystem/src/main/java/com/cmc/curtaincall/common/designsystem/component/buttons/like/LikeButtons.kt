package com.cmc.curtaincall.common.designsystem.component.buttons.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.cmc.curtaincall.common.designsystem.R

@Composable
fun CurtainCallLikeButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(modifier.clickable { onClick() }) {
        Image(
            painter = painterResource(
                if (isSelected) {
                    R.drawable.ic_like_filled
                } else {
                    R.drawable.ic_like_unfilled
                }
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
