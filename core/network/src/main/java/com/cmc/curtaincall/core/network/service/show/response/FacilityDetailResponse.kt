package com.cmc.curtaincall.core.network.service.show.response

import com.cmc.curtaincall.domain.model.show.FacilityDetailModel

data class FacilityDetailResponse(
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
) {
    fun toModel() = FacilityDetailModel(
        address = address,
        characteristic = characteristic,
        hallNum = hallNum,
        homepage = homepage,
        id = id,
        latitude = latitude,
        longitude = longitude,
        name = name,
        openingYear = openingYear,
        phone = phone,
        seatNum = seatNum
    )
}
