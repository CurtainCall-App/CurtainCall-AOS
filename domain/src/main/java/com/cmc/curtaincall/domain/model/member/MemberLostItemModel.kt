package com.cmc.curtaincall.domain.model.member

data class MemberLostItemModel(
    val id: Int,
    val facilityId: String,
    val facilityName: String,
    val title: String,
    val foundDate: String,
    val foundTime: String?,
    val type: String,
    val imageUrl: String,
    val createdAt: String
)
