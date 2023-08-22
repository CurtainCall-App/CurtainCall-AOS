package com.cmc.curtaincall.core.network.service.party.response

import com.cmc.curtaincall.domain.model.party.CreatePartyModel

data class CreatePartyResponse(
    val id: Int
) {
    fun toModel() = CreatePartyModel(id = id)
}
