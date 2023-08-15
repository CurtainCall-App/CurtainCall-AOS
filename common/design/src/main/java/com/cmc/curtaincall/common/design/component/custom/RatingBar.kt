package com.cmc.curtaincall.common.design.component.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.cmc.curtaincall.common.design.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    canChange: Boolean = false,
    onChangeRate: (Int) -> Unit = {}
) {
    Row {
        Icon(
            painter = painterResource(
                if (rating >= 1) R.drawable.ic_star else R.drawable.ic_unfilled_star
            ),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(1)
                }
            },
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(
                if (rating >= 2) R.drawable.ic_star else R.drawable.ic_unfilled_star
            ),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(2)
                }
            },
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(
                if (rating >= 3) R.drawable.ic_star else R.drawable.ic_unfilled_star
            ),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(3)
                }
            },
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(
                if (rating >= 4) R.drawable.ic_star else R.drawable.ic_unfilled_star
            ),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(4)
                }
            },
            tint = Color.Unspecified
        )
        Icon(
            painter = painterResource(
                if (rating == 5) R.drawable.ic_star else R.drawable.ic_unfilled_star
            ),
            contentDescription = null,
            modifier = modifier.clickable {
                if (canChange) {
                    onChangeRate(5)
                }
            },
            tint = Color.Unspecified
        )
    }
}
