package com.cmc.curtaincall.domain.repository

import com.cmc.curtaincall.domain.model.chatting.ChattingTokenModel
import kotlinx.coroutines.flow.Flow

interface ChattingRepository {
    fun requestChattingToken(): Flow<ChattingTokenModel>
}
