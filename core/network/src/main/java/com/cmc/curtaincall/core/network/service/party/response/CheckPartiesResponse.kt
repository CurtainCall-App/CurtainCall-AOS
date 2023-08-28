package com.cmc.curtaincall.core.network.service.party.response

import com.cmc.curtaincall.domain.model.party.CheckPartyModel
import com.google.gson.annotations.SerializedName

data class CheckPartiesResponse(
    @SerializedName("content") val checkParties: List<CheckPartyResponse>
)

data class CheckPartyResponse(
    val partyId: Int,
    val participated: Boolean
) {
    fun toModel() = CheckPartyModel(
        partyId = partyId,
        participated = participated
    )
}
