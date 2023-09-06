package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.chatting.ChattingService
import com.cmc.curtaincall.core.network.service.chatting.response.ChattingTokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChattingRemoteSource @Inject constructor(
    private val chattingService: ChattingService
) {
    fun requestChattingToken(): Flow<ChattingTokenResponse> = flow {
        emit(
            chattingService.requestChattingToken()
        )
    }
}
