package com.cmc.curtaincall.data.source.local

import com.cmc.curtaincall.core.local.db.dao.LostPropertySearchDao
import com.cmc.curtaincall.core.local.db.entity.LostPropertySearchWordEntity
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertySearchWordModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LostPropertyLocalSource @Inject constructor(
    private val lostItemSearchDao: LostPropertySearchDao
) {
    fun getLostItemSearchWordList(): Flow<List<LostPropertySearchWordEntity>> = lostItemSearchDao.getAll()

    suspend fun insertLostItemSearchWord(lostItemSearchWordModel: LostPropertySearchWordModel) =
        lostItemSearchDao.insertLostItemSearch(
            lostItemSearchWordEntity = LostPropertySearchWordEntity(
                lostItemSearchWordModel.word,
                lostItemSearchWordModel.searchAt
            )
        )

    suspend fun deleteLostItemSearchWord(lostItemSearchWordModel: LostPropertySearchWordModel) =
        lostItemSearchDao.deleteLostItemSearchEntity(
            lostItemSearchWordEntity = LostPropertySearchWordEntity(
                lostItemSearchWordModel.word,
                lostItemSearchWordModel.searchAt
            )
        )

    suspend fun deleteLostItemSearchWordList() = lostItemSearchDao.deleteAll()
}
