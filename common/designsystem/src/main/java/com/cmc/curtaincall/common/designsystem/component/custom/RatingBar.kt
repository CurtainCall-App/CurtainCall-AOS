package com.cmc.curtaincall.common.designsystem.component.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    tint: Color = Color.Unspecified,
    canChange: Boolean = false,
    onChangeRate: (Int) -> Unit = {}
) {
    Row {
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(1)
                }
            },
            tint = if (rating >= 1) tint else Bright_Gray
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(2)
                }
            },
            tint = if (rating >= 2) tint else Bright_Gray
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(3)
                }
            },
            tint = if (rating >= 3) tint else Bright_Gray
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(4)
                }
            },
            tint = if (rating >= 4) tint else Bright_Gray
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(5)
                }
            },
            tint = if (rating >= 5) tint else Bright_Gray
        )
    }
}
