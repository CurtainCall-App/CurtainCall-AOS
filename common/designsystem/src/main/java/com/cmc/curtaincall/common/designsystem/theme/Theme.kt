package com.cmc.curtaincall.common.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

private val curtainCallTypography = CurtainCallTypography(
    h1 = TextStyle.Default,
    h2 = TextStyle.Default,
    subTitle1 = TextStyle.Default,
    subTitle2 = TextStyle.Default,
    subTitle3 = TextStyle.Default,
    subTitle4 = TextStyle.Default,
    body1 = TextStyle.Default,
    body2 = TextStyle.Default,
    body3 = TextStyle.Default,
    body4 = TextStyle.Default,
    body5 = TextStyle.Default,
    caption = TextStyle.Default
)

private val curtainCallColors = CurtainCallColors(
    primary = Color.Unspecified,
    onPrimary = Color.Unspecified
)

private val curtainCallShapes = CurtainCallShapes(
    bottomButton = RoundedCornerShape(12.dp)
)

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
