package com.cmc.curtaincall.data.source.local

import com.cmc.curtaincall.core.local.db.dao.LostItemSearchDao
import com.cmc.curtaincall.core.local.db.entity.LostItemSearchWordEntity
import com.cmc.curtaincall.domain.model.lostItem.LostItemSearchWordModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LostItemLocalSource @Inject constructor(
    private val lostItemSearchDao: LostItemSearchDao
) {
    fun getLostItemSearchWordList(): Flow<List<LostItemSearchWordEntity>> = lostItemSearchDao.getAll()

    suspend fun insertLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel) =
        lostItemSearchDao.insertLostItemSearch(
            lostItemSearchWordEntity = LostItemSearchWordEntity(
                lostItemSearchWordModel.word,
                lostItemSearchWordModel.searchAt
            )
        )

    suspend fun deleteLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel) =
        lostItemSearchDao.deleteLostItemSearchEntity(
            lostItemSearchWordEntity = LostItemSearchWordEntity(
                lostItemSearchWordModel.word,
                lostItemSearchWordModel.searchAt
            )
        )

    suspend fun deleteLostItemSearchWordList() = lostItemSearchDao.deleteAll()
}
