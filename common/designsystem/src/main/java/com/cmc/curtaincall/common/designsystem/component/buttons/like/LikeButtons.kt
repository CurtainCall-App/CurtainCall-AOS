package com.cmc.curtaincall.common.designsystem.component.buttons.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey6
import com.cmc.curtaincall.common.designsystem.theme.Grey8

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

@Preview
@Composable
private fun CurtainCallLikeButtonPreview() {
    CurtainCallTheme {
        CurtainCallLikeButton(
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
fun CurtainCallReviewLikeButton(
    modifier: Modifier = Modifier,
    num: Int = 0,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected) {
                    CurtainCallTheme.colors.primary
                } else {
                    Grey8
                },
                shape = RoundedCornerShape(6.dp)
            )
            .padding(vertical = 3.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_thumb_up),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = if (isSelected) {
                    CurtainCallTheme.colors.secondary
                } else {
                    Grey6
                }
            )
            Text(
                text = num.toString(),
                modifier = Modifier.padding(start = 6.dp),
                style = CurtainCallTheme.typography.body4.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) {
                        CurtainCallTheme.colors.secondary
                    } else {
                        Grey6
                    }
                )
            )
        }
    }
}

@Preview
@Composable
private fun CurtainCallReviewLikeButtonPreview() {
    CurtainCallTheme {
        CurtainCallReviewLikeButton(
            num = 37,
            isSelected = false
        )
    }
}
