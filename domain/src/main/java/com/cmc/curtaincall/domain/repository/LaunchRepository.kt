package com.cmc.curtaincall.domain.repository

import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    fun getIsFirstEntryShowList(): Flow<Boolean>
    suspend fun setIsFirstEntryShowList()
}
