package com.cmc.curtaincall.domain.model.show

data class ShowRankModel(
    val endDate: String,
    val genre: String,
    val id: String,
    val name: String,
    val poster: String,
    val rank: Int,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val startDate: String
)
