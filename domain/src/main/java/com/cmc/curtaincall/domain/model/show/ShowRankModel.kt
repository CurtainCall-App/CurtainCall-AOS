package com.cmc.curtaincall.domain.model.show

data class ShowRankModel(
    val endDate: String = "",
    val genre: String = "",
    val id: String = "",
    val name: String = "",
    val poster: String = "",
    val rank: Int = 0,
    val reviewCount: Int = 1,
    val reviewGradeSum: Int = 0,
    val startDate: String = ""
)
