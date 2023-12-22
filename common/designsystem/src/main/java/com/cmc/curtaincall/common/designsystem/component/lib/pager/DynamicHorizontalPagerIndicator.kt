package com.cmc.curtaincall.common.designsystem.component.lib.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DynamicHorizontalPagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier,
    pageIndexMapping: (Int) -> Int = { it },
    activeColor: Color = Color.Unspecified,
    activeIndicatorWidth: Dp = 8.dp,
    activeIndicatorHeight: Dp = activeIndicatorWidth,
    inactiveColor: Color = Color.Unspecified,
    inactiveIndicatorWidth: Dp = 8.dp,
    inactiveIndicatorHeight: Dp = inactiveIndicatorWidth,
    spacing: Dp = 8.dp,
    indicatorShape: Shape = CircleShape,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val position = pageIndexMapping(pagerState.currentPage)
            (0 until pageCount).forEach { index ->
                val indicatorModifier = Modifier
                    .size(
                        width = if (index == position) activeIndicatorWidth else inactiveIndicatorWidth,
                        height = if (index == position) activeIndicatorHeight else inactiveIndicatorHeight
                    )
                    .background(
                        color = if (index == position) activeColor else inactiveColor,
                        shape = indicatorShape
                    )
                Box(indicatorModifier)
            }
        }
    }
}
