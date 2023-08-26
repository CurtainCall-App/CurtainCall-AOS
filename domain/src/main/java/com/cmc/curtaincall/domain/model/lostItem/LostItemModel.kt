package com.cmc.curtaincall.domain.model.lostItem

data class LostItemModel(
    val id: Int,
    val facilityId: String,
    val facilityName: String,
    val title: String,
    val foundDate: String,
    val foundTime: String,
    val imageUrl: String
)
