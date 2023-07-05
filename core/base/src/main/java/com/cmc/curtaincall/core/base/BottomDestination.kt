package com.cmc.curtaincall.core.base

import androidx.annotation.DrawableRes

interface BottomDestination {
    val route: String

    @get:DrawableRes
    val icon: Int
}
