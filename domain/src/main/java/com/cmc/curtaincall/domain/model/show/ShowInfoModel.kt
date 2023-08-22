package com.cmc.curtaincall.domain.model.show

data class ShowInfoModel(
    val endDate: String,
    val facilityName: String,
    val genre: String,
    val id: String,
    val name: String,
    val poster: String,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val runtime: String,
    val showTimes: List<ShowTimeModel>,
    val startDate: String
)
