package com.cmc.curtaincall.domain.model.lostproperty

data class LostPropertyModel(
    val id: Int,
    val facilityId: String,
    val facilityName: String,
    val title: String,
    val foundDate: String,
    val foundTime: String?,
    val imageUrl: String
)
