package com.cmc.curtaincall.domain.model.show

data class SimilarShowInfoModel(
    val id: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val facilityName: String,
    val poster: String,
    val genre: String,
    val showTimes: List<ShowTimeModel>,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val runtime: String
)
