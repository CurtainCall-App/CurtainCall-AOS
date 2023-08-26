package com.cmc.curtaincall.domain.model.show

data class ShowInfoModel(
    val endDate: String = "",
    val facilityName: String = "",
    val genre: String = "",
    val id: String = "",
    val name: String = "",
    val poster: String = "",
    val reviewCount: Int = 1,
    val reviewGradeSum: Int = 0,
    val runtime: String = "",
    val showTimes: List<ShowTimeModel> = listOf(),
    val startDate: String = "",
    val favorite: Boolean = false
)
