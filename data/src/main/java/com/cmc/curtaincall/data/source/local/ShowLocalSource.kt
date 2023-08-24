package com.cmc.curtaincall.data.source.local

import com.cmc.curtaincall.core.local.db.dao.ShowSearchDao
import com.cmc.curtaincall.core.local.db.entity.ShowSearchWordEntity
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowLocalSource @Inject constructor(
    private val showSearchDao: ShowSearchDao
) {
    fun getShowSearchWordList(): Flow<List<ShowSearchWordEntity>> = showSearchDao.getAll()

    suspend fun insertShowSearchWord(showSearchWordModel: ShowSearchWordModel) =
        showSearchDao.insertShowSearch(
            showSearchWordEntity = ShowSearchWordEntity(
                showSearchWordModel.searchAt,
                showSearchWordModel.word
            )
        )

    suspend fun deleteShowSearchWord(showSearchWordModel: ShowSearchWordModel) =
        showSearchDao.deleteShowSearch(
            showSearchWordEntity = ShowSearchWordEntity(
                showSearchWordModel.searchAt,
                showSearchWordModel.word
            )
        )

    suspend fun deleteShowSearchWordList() = showSearchDao.deleteAll()
}