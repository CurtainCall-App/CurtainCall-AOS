package com.cmc.curtaincall.domain.model.lostproperty

data class LostPropertyModel(
    val id: Int = Int.MIN_VALUE,
    val facilityId: String = "",
    val facilityName: String = "",
    val title: String = "",
    val foundDate: String = "",
    val foundTime: String? = null,
    val imageUrl: String = ""
)
