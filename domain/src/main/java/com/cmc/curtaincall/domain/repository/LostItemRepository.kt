package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.lostItem.CreateLostItemModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemSearchWordModel
import kotlinx.coroutines.flow.Flow

interface LostItemRepository {

    fun getLostItemSearchWordList(): Flow<List<LostItemSearchWordModel>>

    suspend fun insertLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel)

    suspend fun deleteLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel)

    suspend fun deleteLostItemSearchWordList()

    fun createLostItem(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostItemModel>

    fun fetchLostItemList(
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<PagingData<LostItemModel>>

    fun requestLostItemList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<List<LostItemModel>>

    fun requestLostItemDetail(
        lostItemId: Int
    ): Flow<LostItemDetailModel>

    fun deleteLostItem(
        lostItemId: Int
    ): Flow<Boolean>

    fun updateLostItem(
        lostItemId: Int,
        title: String,
        type: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<Boolean>
}
