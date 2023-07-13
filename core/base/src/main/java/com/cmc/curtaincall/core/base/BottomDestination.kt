package com.cmc.curtaincall.core.base

import androidx.annotation.DrawableRes

interface BottomDestination {
    @get:DrawableRes
    val icon: Int

    @get:DrawableRes
    val selectIcon: Int

    val label: String
}
