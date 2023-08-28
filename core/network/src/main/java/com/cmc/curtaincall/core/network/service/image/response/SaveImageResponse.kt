package com.cmc.curtaincall.core.network.service.image.response

import com.cmc.curtaincall.domain.model.image.SaveImageModel

data class SaveImageResponse(
    val id: Int
) {
    fun toModel() = SaveImageModel(
        id = id
    )
}
