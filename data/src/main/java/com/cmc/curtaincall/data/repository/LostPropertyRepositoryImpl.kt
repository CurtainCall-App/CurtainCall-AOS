package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cmc.curtaincall.core.network.service.lostproperty.LostPropertyService
import com.cmc.curtaincall.data.source.local.LostPropertyLocalSource
import com.cmc.curtaincall.data.source.paging.LOSTITEM_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.LostItemPagingSource
import com.cmc.curtaincall.data.source.remote.LostPropertyRemoteSource
import com.cmc.curtaincall.domain.model.lostproperty.CreateLostPropertyModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyDetailModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertySearchWordModel
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LostPropertyRepositoryImpl @Inject constructor(
    private val lostPropertyRemoteSource: LostPropertyRemoteSource,
    private val lostPropertyLocalService: LostPropertyLocalSource,
    private val lostPropertyService: LostPropertyService
) : LostPropertyRepository {

    override fun getLostPropertySearchWordList(): Flow<List<LostPropertySearchWordModel>> =
        lostPropertyLocalService.getLostItemSearchWordList().map { lostItemSearchWordEntityList ->
            lostItemSearchWordEntityList.map { it.toModel() }
        }

    override suspend fun insertLostPropertySearchWord(lostItemSearchWordModel: LostPropertySearchWordModel) =
        lostPropertyLocalService.insertLostItemSearchWord(lostItemSearchWordModel)

    override suspend fun deleteLostPropertySearchWord(lostItemSearchWordModel: LostPropertySearchWordModel) =
        lostPropertyLocalService.deleteLostItemSearchWord(lostItemSearchWordModel)

    override suspend fun deleteLostPropertySearchWordList() =
        lostPropertyLocalService.deleteLostItemSearchWordList()

    override fun createLostProperty(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<CreateLostPropertyModel> =
        lostPropertyRemoteSource.createLostItem(
            title = title,
            type = type,
            facilityId = facilityId,
            foundPlaceDetail = foundPlaceDetail,
            foundDate = foundDate,
            foundTime = foundTime,
            particulars = particulars,
            imageId = imageId
        ).map { it.toModel() }

    override fun requestLostPropertyList(
        page: Int,
        size: Int,
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<List<LostPropertyModel>> =
        lostPropertyRemoteSource.requestLostItemList(
            page = page,
            size = size,
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        ).map { it.map { it.toModel() } }

    override fun fetchLostPropertyList(
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<PagingData<LostPropertyModel>> {
        return Pager(
            config = PagingConfig(pageSize = LOSTITEM_PAGE_SIZE),
            pagingSourceFactory = {
                LostItemPagingSource(
                    lostItemService = lostPropertyService,
                    facilityId = facilityId,
                    type = type,
                    foundDate = foundDate,
                    title = title
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { response ->
                    response.toModel()
                }
            }
    }

    override fun requestLostPropertyDetail(lostItemId: Int): Flow<LostPropertyDetailModel> =
        lostPropertyRemoteSource.requestLostItemDetail(lostItemId)
            .map { it.toModel() }

    override fun deleteLostProperty(lostItemId: Int): Flow<Boolean> =
        lostPropertyRemoteSource.deleteLostItem(lostItemId)

    override fun updateLostProperty(
        lostItemId: Int,
        title: String,
        type: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
        particulars: String,
        imageId: Int
    ): Flow<Boolean> =
        lostPropertyRemoteSource.updateLostItem(
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
