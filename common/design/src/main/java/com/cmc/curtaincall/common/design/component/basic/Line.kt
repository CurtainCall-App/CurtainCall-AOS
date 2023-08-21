package com.cmc.curtaincall.common.design.component.basic

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.theme.Bright_Gray

@Composable
fun DottedLine(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 2.dp.value,
    strokeColor: Color = Bright_Gray,
    intervals: FloatArray = floatArrayOf(5f, 10f),
    phase: Float = 15f
) {
    val pathEffect = PathEffect.dashPathEffect(intervals, phase)
    Canvas(modifier) {
        drawLine(
            color = strokeColor,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect,
            strokeWidth = strokeWidth
        )
    }
}
