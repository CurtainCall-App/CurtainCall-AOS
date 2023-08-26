package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.LostItemRemoteSource
import com.cmc.curtaincall.domain.model.lostItem.CreateLostItemModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.repository.LostItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LostItemRepositoryImpl @Inject constructor(
    private val lostItemRemoteSource: LostItemRemoteSource
) : LostItemRepository {

    override fun createLostItem(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostItemModel> =
        lostItemRemoteSource.createLostItem(
            title = title,
            type = type,
            facilityId = facilityId,
            foundPlaceDetail = foundPlaceDetail,
            foundDate = foundDate,
            foundTime = foundTime,
            particulars = particulars,
            imageId = imageId
        ).map { it.toModel() }

    override fun requestLostItemList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String,
        foundDate: String,
        title: String
    ): Flow<List<LostItemModel>> =
        lostItemRemoteSource.requestLostItemList(
            page = page,
            size = size,
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        ).map { it.map { it.toModel() } }

    override fun requestLostItemDetail(lostItemId: Int): Flow<LostItemDetailModel> =
        lostItemRemoteSource.requestLostItemDetail(lostItemId)
            .map { it.toModel() }

    override fun deleteLostItem(lostItemId: Int): Flow<Boolean> =
        lostItemRemoteSource.deleteLostItem(lostItemId)

    override fun updateLostItem(
        lostItemId: Int,
        title: String,
        type: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String,
        particulars: String,
        imageId: Int
    ): Flow<Boolean> =
        lostItemRemoteSource.updateLostItem(
            lostItemId = lostItemId,
            title = title,
            type = type,
            foundPlaceDetail = foundPlaceDetail,
            foundDate = foundDate,
            foundTime = foundTime,
            particulars = particulars,
            imageId = imageId
        )
}
