package com.cmc.curtaincall.common.design.theme

import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class CurtainCallShape(
    val bottomButton: Shape
)

val LocalCurtainCallShape = staticCompositionLocalOf {
    CurtainCallShape(
        bottomButton = ShapeDefaults.Small
    )
}
