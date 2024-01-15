package com.cmc.curtaincall.common.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.designsystem.extensions.toSp

@Composable
fun CurtainCallTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    val curtainCallTypography = CurtainCallTypography(
        h1 = TextStyle(
            color = Black,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = 31.sp
        ),
        h2 = TextStyle(
            color = Black,
            fontSize = 22.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = (28.6).sp
        ),
        subTitle1 = TextStyle(
            color = Black,
            fontSize = 20.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = 26.sp
        ),
        subTitle2 = TextStyle(
            color = Black,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = (23.4).sp
        ),
        subTitle3 = TextStyle(
            color = Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = (22.1).sp
        ),
        subTitle4 = TextStyle(
            color = Black,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = pretendard,
            lineHeight = (22.4).sp
        ),
        body1 = TextStyle(
            color = Black,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
            lineHeight = (22.4).sp
        ),
        body2 = TextStyle(
            color = Black,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
            lineHeight = 21.sp
        ),
        body3 = TextStyle(
            color = Black,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
            lineHeight = 21.sp
        ),
        body4 = TextStyle(
            color = Black,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
            lineHeight = (18.2).sp
        ),
        body5 = TextStyle(
            color = Black,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = pretendard,
            lineHeight = (16.8).sp
        ),
        caption = TextStyle(
            color = Black,
            fontSize = 10.dp.toSp(),
            fontWeight = FontWeight.Normal,
            fontFamily = pretendard,
            lineHeight = 14.sp
        )
    )

    val curtainCallColors = CurtainCallColors(
        primary = Color(0xFF0D1327),
        onPrimary = White,
        secondary = Color(0xFFD4C6FD),
        onSecondary = Black,
        thrtiay = Color(0xFFB9EBFA),
        onThrtiay = Black,
        quarternary = Color(0xFFFFFCAB),
        onQuarternary = Black,
        background = White,
        systemRed = Red,
        systemGreen = Green,
        systemYellow = Yellow
    )

    val curtainCallShapes = CurtainCallShapes(
        bottomButton = RoundedCornerShape(12.dp)
    )

    CompositionLocalProvider(
        LocalCurtainCallTypography provides curtainCallTypography,
        LocalCurtainCallColors provides curtainCallColors,
        LocalCurtainCallShapes provides curtainCallShapes
    ) {
        MaterialTheme(content = content)
    }
}

object CurtainCallTheme {
    val typography: CurtainCallTypography
        @Composable
        get() = LocalCurtainCallTypography.current

    val colors: CurtainCallColors
        @Composable
        get() = LocalCurtainCallColors.current

    val shapes: CurtainCallShapes
        @Composable
        get() = LocalCurtainCallShapes.current
}

object NoRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0f)
}
