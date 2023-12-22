package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.lostproperty.LostPropertyService
import com.cmc.curtaincall.core.network.service.lostproperty.request.CreateLostPropertyRequest
import com.cmc.curtaincall.core.network.service.lostproperty.request.UpdateLostPropertyRequest
import com.cmc.curtaincall.core.network.service.lostproperty.response.CreateLostPropertyResponse
import com.cmc.curtaincall.core.network.service.lostproperty.response.LostPropertyDetailResponse
import com.cmc.curtaincall.core.network.service.lostproperty.response.LostItemResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LostPropertyRemoteSource @Inject constructor(
    private val lostItemService: LostPropertyService
) {
    fun createLostItem(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostPropertyResponse> = flow {
        emit(
            lostItemService.createLostItem(
                createLostItemRequest = CreateLostPropertyRequest(
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
        )
    }

    fun requestLostItemList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<List<LostItemResponse>> = flow {
        emit(
            lostItemService.requestLostItemList(
                page = page,
                size = size,
                facilityId = facilityId,
                type = type,
                foundDate = foundDate,
                title = title
            ).lostItems
        )
    }

    fun requestLostItemDetail(
        lostItemId: Int
    ): Flow<LostPropertyDetailResponse> = flow {
        emit(
            lostItemService.requestLostItemDetail(
                lostItemId = lostItemId
            )
        )
    }

    fun deleteLostItem(
        lostItemId: Int
    ): Flow<Boolean> = flow {
        emit(
            lostItemService.deleteLostItem(lostItemId).isSuccessful
        )
    }

    fun updateLostItem(
        lostItemId: Int,
        title: String,
        type: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<Boolean> = flow {
        emit(
            lostItemService.updateLostItem(
                lostItemId = lostItemId,
                updateLostItemRequest = UpdateLostPropertyRequest(
                    title = title,
                    type = type,
                    foundPlaceDetail = foundPlaceDetail,
                    foundDate = foundDate,
                    foundTime = foundTime,
                    particulars = particulars,
                    imageId = imageId
                )
            ).isSuccessful
        )
    }
}
