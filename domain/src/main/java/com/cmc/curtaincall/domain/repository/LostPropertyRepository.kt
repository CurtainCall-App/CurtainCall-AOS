package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.lostproperty.CreateLostPropertyModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertySearchWordModel
import kotlinx.coroutines.flow.Flow

interface LostPropertyRepository {

    fun getLostPropertySearchWordList(): Flow<List<LostPropertySearchWordModel>>

    suspend fun insertLostPropertySearchWord(lostItemSearchWordModel: LostPropertySearchWordModel)

    suspend fun deleteLostPropertySearchWord(lostItemSearchWordModel: LostPropertySearchWordModel)

    suspend fun deleteLostPropertySearchWordList()

    fun createLostProperty(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostPropertyModel>

    fun fetchLostPropertyList(
        facilityId: String,
        type: String?,
        foundDate: String?
    ): Flow<PagingData<LostPropertyModel>>

    fun requestLostPropertyList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String?,
        foundDate: String?
    ): Flow<List<LostPropertyModel>>

    fun requestLostPropertyDetail(
        lostItemId: Int
    ): Flow<LostPropertyDetailModel>

    fun deleteLostProperty(
        lostItemId: Int
    ): Flow<Boolean>

    fun updateLostProperty(
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
