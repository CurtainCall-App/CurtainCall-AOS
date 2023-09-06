package com.cmc.curtaincall.core.network.service.chatting

import com.cmc.curtaincall.core.network.service.chatting.response.ChattingTokenResponse
import retrofit2.http.GET

interface ChattingService {

    @GET("chat-token")
    suspend fun requestChattingToken(): ChattingTokenResponse
}
