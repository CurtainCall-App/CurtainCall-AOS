package com.cmc.curtaincall.common.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Sunglow = Color(0xFFFFD52F)
val Sunset_Pink = Color(0xFFF24B86)
val White = Color(0xFFFFFFFF)
val Ash = Color(0xFFF5F5F5)
val Smoke = Color(0xFFEEEEEE)
val Silver = Color(0xFFBFBFBF)
val Coal = Color(0xFF242424)
val Black = Color(0xFF000000)
val Gunmetal = Color(0xFF273041)
val French_Rose = Color(0xFFF04E87)
val Eerie_Black = Color(0xFF1C1B1F)
val Chinese_Black = Color(0xFF111111)
val Slate_Gray = Color(0xFF717A90)
val Me_Pink = Color(0xFFFF7CAB)
val Cultured = Color(0xFFF5F6F8)
val Charcoal = Color(0xFF333C53)
val Anti_Flash_White = Color(0xFFF2F3F5)
val Cadet_Grey = Color(0xFF99A1B2)
val Philippine_Gray = Color(0xFF909090)
val Platinum = Color(0xFFE1E4E9)
val Gainsboro = Color(0xFFDCDEE1)
val Solitude = Color(0xFFEEF0F3)
val Cetacean_Blue = Color(0xFF051840)
val Corn = Color(0xFFFEEA61)
val Silver_Chalice = Color(0xFFADADAD)
val Silver_Sand = Color(0xFFBEC2CA)
val Bright_Gray = Color(0xFFE4E7EC)
val Black_Coral = Color(0xFF5A6271)
val French_Pink = Color(0xFFFF659C)
val Fiery_Rose = Color(0xFFFD517A)
val Roman_Silver = Color(0xFF828996)
val Arsenic = Color(0xFF3B4350)
val Red_Salsa = Color(0xFFFF334B)
val Ultramarine_Blue = Color(0xFF3D50FF)
val Nero = Color(0xFF1A1A1A)
val Black_Pearl = Color(0xFF161A20)
val Whisper = Color(0xFFE7E7E7)
val Raisin_Black = Color(0xFF252323)
val Ghost_White = Color(0xFFF9F9FA)
val Dark_Slate_Grey = Color(0xFF2B313A)
val Granite_Gray = Color(0xFF636363)
val Navajo_White = Color(0xFFFFE4DC)
val Pale_Lavendar = Color(0xFFD2D7FF)
val Water = Color(0xFFD9F4FF)
val Red = Color(0xFFFF0000)
val Cheery_Paddle_Pop = Color(0xFFFF334B)
val Green = Color(0xFF00C271)
val Bright_Grey = Color(0xFF525457)
val Eggplant = Color(0xFF4B5975)
val Lavendar_Phlox = Color(0xFF1E2F53)
val Harmonies = Color(0xFF374666)
val Electric_Indigo = Color(0xFF11244A)

@Immutable
data class CurtainCallColors(
    val primary: Color,
    val onPrimary: Color
)

val LocalCurtainCallColors = staticCompositionLocalOf {
    CurtainCallColors(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified
    )
}
