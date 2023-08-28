package com.cmc.curtaincall.core.network.service.lostitem.response

import com.cmc.curtaincall.domain.model.lostItem.CreateLostItemModel

data class CreateLostItemResponse(
    val id: Int
) {
    fun toModel() = CreateLostItemModel(
        id = id
    )
}
