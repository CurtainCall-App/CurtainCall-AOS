package com.cmc.curtaincall.domain.model.show

data class ShowRecommendationModel(
    val id: Int = Int.MIN_VALUE,
    val description: String = "",
    val showId: String = "",
    val name: String = "",
    val genre: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val poster: String? = null
)
