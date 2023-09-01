package com.cmc.curtaincall.domain.model.favorite

import com.cmc.curtaincall.domain.model.show.ShowTimeModel

data class FavoriteShowModel(
    val id: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val facilityName: String = "",
    val poster: String = "",
    val genre: String = "",
    val showTimes: List<ShowTimeModel> = listOf(),
    val reviewCount: Int = 1,
    val reviewGradeSum: Int = 0,
    val runtime: String = "",
    val favorite: Boolean = false
)
