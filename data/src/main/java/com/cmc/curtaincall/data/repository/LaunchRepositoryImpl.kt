package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.local.LaunchLocalSource
import com.cmc.curtaincall.domain.repository.LaunchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val launchLocalSource: LaunchLocalSource
) : LaunchRepository {

    override fun getIsFirstEntryShowList(): Flow<Boolean> =
        launchLocalSource.getIsFirstEntryShowList()

    override suspend fun setIsFirstEntryShowList() {
        launchLocalSource.setIsFirstEntryShowList()
    }

    override fun isShowPartyTooltip(): Flow<Boolean> =
        launchLocalSource.isShowPartyTooltip()

    override suspend fun stopShowPartyTooltip() {
        launchLocalSource.stopShowPartyTooltip()
    }
}
