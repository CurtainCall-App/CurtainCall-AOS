package com.cmc.curtaincall.common.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CurtainCallTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val subTitle1: TextStyle,
    val subTitle2: TextStyle,
    val subTitle3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
    val body5: TextStyle,
    val caption: TextStyle
)

val LocalCurtainCallTypography = staticCompositionLocalOf {
    CurtainCallTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        subTitle1 = TextStyle.Default,
        subTitle2 = TextStyle.Default,
        subTitle3 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        body3 = TextStyle.Default,
        body4 = TextStyle.Default,
        body5 = TextStyle.Default,
        caption = TextStyle.Default
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
