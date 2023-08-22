package com.cmc.curtaincall.domain.model.show

data class FacilityDetailModel(
    val address: String,
    val characteristic: String,
    val hallNum: Int,
    val homepage: String,
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val openingYear: Int,
    val phone: String,
    val seatNum: Int
)
