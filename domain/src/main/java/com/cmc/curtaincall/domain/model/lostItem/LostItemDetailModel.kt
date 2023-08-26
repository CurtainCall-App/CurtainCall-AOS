package com.cmc.curtaincall.domain.model.lostItem

data class LostItemDetailModel(
    val facilityId: String,
    val facilityName: String,
    val facilityPhone: String,
    val foundDate: String,
    val foundPlaceDetail: String,
    val foundTime: String,
    val id: Int,
    val imageId: Int,
    val imageUrl: String,
    val particulars: String,
    val title: String,
    val type: String
)
