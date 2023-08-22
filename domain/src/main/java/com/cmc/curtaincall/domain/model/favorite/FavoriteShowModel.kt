package com.cmc.curtaincall.domain.model.favorite

data class FavoriteShowModel(
    val id: String,
    val name: String,
    val poster: String,
    val reviewCount: Int,
    val reviewGradeSum: Int,
    val story: String
)