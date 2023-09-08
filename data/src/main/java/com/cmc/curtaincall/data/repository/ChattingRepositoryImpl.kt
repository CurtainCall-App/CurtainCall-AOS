package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.ChattingRemoteSource
import com.cmc.curtaincall.domain.model.chatting.ChattingTokenModel
import com.cmc.curtaincall.domain.repository.ChattingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val chattingRemoteSource: ChattingRemoteSource
) : ChattingRepository {
    override fun requestChattingToken(): Flow<ChattingTokenModel> =
        chattingRemoteSource.requestChattingToken().map { it.toModel() }
}
