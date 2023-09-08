package com.cmc.curtaincall.core.network.service.chatting.response

import com.cmc.curtaincall.domain.model.chatting.ChattingTokenModel

data class ChattingTokenResponse(
    val value: String
) {
    fun toModel() = ChattingTokenModel(
        value = value
    )
}
