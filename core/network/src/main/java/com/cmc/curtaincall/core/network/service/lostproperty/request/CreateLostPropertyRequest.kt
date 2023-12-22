package com.cmc.curtaincall.core.network.service.lostproperty.request

data class CreateLostPropertyRequest(
    val title: String,
    val type: String,
    val facilityId: String,
    val foundPlaceDetail: String,
    val foundDate: String,
    val foundTime: String?,
    val particulars: String,
    val imageId: Int
)
