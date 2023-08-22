package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.ShowTimeModel

data class ShowDetailResponse(
    val age: String,
    val cast: String,
    val crew: String,
    val endDate: String,
    val enterprise: String,
    val facilityId: String,
    val facilityName: String,
    val genre: String,
    val id: String,
    val introductionImages: List<String>,
    val name: String,
    val poster: String,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val runtime: String,
    val showTimes: List<ShowTimeResponse>,
    val startDate: String,
    val story: String,
    val ticketPrice: String
) {
    fun toModel() = ShowDetailModel(
        age = age,
        cast = cast,
        crew = crew,
        endDate = endDate,
        enterprise = enterprise,
        facilityId = facilityId,
        facilityName = facilityName,
        genre = genre,
        id = id,
        introductionImages = introductionImages,
        name = name,
        poster = poster,
        reviewCount = reviewCount,
        reviewGradeSum = reviewGradeSum,
        runtime = runtime,
        showTimes = showTimes.map { it.toModel() },
        startDate = startDate,
        story = story,
        ticketPrice = ticketPrice
    )
}