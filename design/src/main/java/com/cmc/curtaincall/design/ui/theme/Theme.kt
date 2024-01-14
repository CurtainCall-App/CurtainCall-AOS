package com.cmc.curtaincall.design.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTypography
import com.cmc.curtaincall.common.designsystem.theme.LocalCurtainCallTypography
import com.cmc.curtaincall.common.designsystem.theme.pretendard

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CurtainCallTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

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

    CompositionLocalProvider(
        LocalCurtainCallTypography provides curtainCallTypography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object CurtainCallTheme {
    val typography: CurtainCallTypography
        @Composable
        get() = LocalCurtainCallTypography.current
}
