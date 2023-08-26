package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.lostitem.LostItemService
import com.cmc.curtaincall.core.network.service.lostitem.request.CreateLostItemRequest
import com.cmc.curtaincall.core.network.service.lostitem.request.UpdateLostItemRequest
import com.cmc.curtaincall.core.network.service.lostitem.response.CreateLostItemResponse
import com.cmc.curtaincall.core.network.service.lostitem.response.LostItemDetailResponse
import com.cmc.curtaincall.core.network.service.lostitem.response.LostItemResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LostItemRemoteSource @Inject constructor(
    private val lostItemService: LostItemService
) {
    fun createLostItem(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostItemResponse> = flow {
        lostItemService.createLostItem(
            createLostItemRequest = CreateLostItemRequest(
                title = title,
                type = type,
                facilityId = facilityId,
                foundPlaceDetail = foundPlaceDetail,
                foundDate = foundDate,
                foundTime = foundTime,
                particulars = particulars,
                imageId = imageId
            )
        )
    }

    fun requestLostItemList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String,
        foundDate: String,
        title: String
    ): Flow<List<LostItemResponse>> = flow {
        lostItemService.requestLostItemList(
            page = page,
            size = size,
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        )
    }

    fun requestLostItemDetail(
        lostItemId: Int
    ): Flow<LostItemDetailResponse> = flow {
        lostItemService.requestLostItemDetail(
            lostItemId = lostItemId
        )
    }

    fun deleteLostItem(
        lostItemId: Int
    ): Flow<Boolean> = flow {
        lostItemService.deleteLostItem(lostItemId).isSuccessful
    }

    fun updateLostItem(
        lostItemId: Int,
        title: String,
        type: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String,
        particulars: String,
        imageId: Int
    ): Flow<Boolean> = flow {
        lostItemService.updateLostItem(
            lostItemId = lostItemId,
            updateLostItemRequest = UpdateLostItemRequest(
                title = title,
                type = type,
                foundPlaceDetail = foundPlaceDetail,
                foundDate = foundDate,
                foundTime = foundTime,
                particulars = particulars,
                imageId = imageId
            )
        ).isSuccessful
    }
}
