package com.cmc.curtaincall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cmc.curtaincall.core.network.service.lostitem.LostItemService
import com.cmc.curtaincall.data.source.local.LostItemLocalSource
import com.cmc.curtaincall.data.source.paging.LOSTITEM_PAGE_SIZE
import com.cmc.curtaincall.data.source.paging.LostItemPagingSource
import com.cmc.curtaincall.data.source.remote.LostItemRemoteSource
import com.cmc.curtaincall.domain.model.lostItem.CreateLostItemModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemDetailModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.model.lostItem.LostItemSearchWordModel
import com.cmc.curtaincall.domain.repository.LostItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LostItemRepositoryImpl @Inject constructor(
    private val lostItemRemoteSource: LostItemRemoteSource,
    private val lostItemService: LostItemService,
    private val lostItemLocalSource: LostItemLocalSource
) : LostItemRepository {

    override fun getLostItemSearchWordList(): Flow<List<LostItemSearchWordModel>> =
        lostItemLocalSource.getLostItemSearchWordList().map { lostItemSearchWordEntityList ->
            lostItemSearchWordEntityList.map { it.toModel() }
        }

    override suspend fun insertLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel) =
        lostItemLocalSource.insertLostItemSearchWord(lostItemSearchWordModel)

    override suspend fun deleteLostItemSearchWord(lostItemSearchWordModel: LostItemSearchWordModel) =
        lostItemLocalSource.deleteLostItemSearchWord(lostItemSearchWordModel)

    override suspend fun deleteLostItemSearchWordList() =
        lostItemLocalSource.deleteLostItemSearchWordList()

    override fun createLostItem(
        title: String,
        type: String,
        facilityId: String,
        foundPlaceDetail: String,
        foundDate: String,
        foundTime: String?,
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
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<List<LostItemModel>> =
        lostItemRemoteSource.requestLostItemList(
            page = page,
            size = size,
            facilityId = facilityId,
            type = type,
            foundDate = foundDate,
            title = title
        ).map { it.map { it.toModel() } }

    override fun fetchLostItemList(
        facilityId: String,
        type: String?,
        foundDate: String?,
        title: String?
    ): Flow<PagingData<LostItemModel>> {
        return Pager(
            config = PagingConfig(pageSize = LOSTITEM_PAGE_SIZE),
            pagingSourceFactory = {
                LostItemPagingSource(
                    lostItemService = lostItemService,
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
        foundTime: String?,
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
