package com.cmc.curtaincall.domain.repository

import androidx.paging.PagingData
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.model.show.ShowRankModel
import com.cmc.curtaincall.domain.model.show.ShowSearchWordModel
import kotlinx.coroutines.flow.Flow

interface ShowRepository {

    fun getShowSearchWordList(): Flow<List<ShowSearchWordModel>>

    suspend fun insertShowSearchWord(showSearchWordModel: ShowSearchWordModel)

    suspend fun deleteShowSearchWord(showSearchWordModel: ShowSearchWordModel)

    suspend fun deleteShowSearchWordList()

    fun fetchShowList(
        genre: String
    ): Flow<PagingData<ShowInfoModel>>

    fun requestShowList(
        page: Int,
        size: Int,
        genre: String
    ): Flow<List<ShowInfoModel>>

    fun fetchSearchShowList(
        keyword: String
    ): Flow<PagingData<ShowInfoModel>>

    fun searchShowList(
        page: Int,
        size: Int,
        keyword: String
    ): Flow<List<ShowInfoModel>>

    fun requestOpenShowList(
        page: Int,
        size: Int,
        startDate: String
    ): Flow<List<ShowInfoModel>>

    fun requestShowDetail(
        showId: String
    ): Flow<ShowDetailModel>

    fun requestPopularShowList(
        type: String,
        genre: String,
        baseDate: String
    ): Flow<List<ShowRankModel>>

    fun requestFacilityDetail(
        facilityId: String
    ): Flow<FacilityDetailModel>
}
