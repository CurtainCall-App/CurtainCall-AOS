package com.cmc.curtaincall.core.network.service.lostproperty.response

import com.cmc.curtaincall.domain.model.lostproperty.CreateLostPropertyModel

data class CreateLostPropertyResponse(
    val id: Int
) {
    fun toModel() = CreateLostPropertyModel(
        id = id
    )
}
