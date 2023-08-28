package com.cmc.curtaincall.domain.model.show

data class ShowDetailModel(
    val age: String = "",
    val cast: String = "",
    val crew: String = "",
    val endDate: String = "",
    val enterprise: String = "",
    val facilityId: String = "",
    val facilityName: String = "",
    val genre: String = "",
    val id: String = "",
    val introductionImages: List<String> = listOf(),
    val name: String = "",
    val poster: String = "",
    val reviewCount: Int = 0,
    val reviewGradeSum: Int = 0,
    val runtime: String = "",
    val showTimes: List<ShowTimeModel> = listOf(),
    val startDate: String = "",
    val story: String = "",
    val ticketPrice: String = "",
    val isFavorite: Boolean = false
)
