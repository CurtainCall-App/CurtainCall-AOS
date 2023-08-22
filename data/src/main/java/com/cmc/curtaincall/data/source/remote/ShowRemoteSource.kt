package com.cmc.curtaincall.data.source.remote

import com.cmc.curtaincall.core.network.service.show.ShowService
import com.cmc.curtaincall.core.network.service.show.response.FacilityDetailResponse
import com.cmc.curtaincall.core.network.service.show.response.ShowDetailResponse
import com.cmc.curtaincall.core.network.service.show.response.ShowInfoResponse
import com.cmc.curtaincall.core.network.service.show.response.ShowRankResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowRemoteSource @Inject constructor(
    private val showService: ShowService
) {
    fun requestShowList(
        page: Int,
        size: Int,
        genre: String
    ): Flow<List<ShowInfoResponse>> = flow {
        emit(
            showService.requestShowList(
                page = page,
                size = size,
                genre = genre
            ).showInfos
        )
    }

    fun searchShowList(
        page: Int,
        size: Int,
        keyword: String
    ): Flow<List<ShowInfoResponse>> = flow {
        emit(
            showService.searchShowList(
                page = page,
                size = size,
                keyword = keyword
            ).showInfos
        )
    }

    fun requestOpenShowList(
        page: Int,
        size: Int,
        startDate: String
    ): Flow<List<ShowInfoResponse>> = flow {
        emit(
            showService.requestOpenShowList(
                page = page,
                size = size,
                startDate = startDate
            ).showInfos
        )
    }

    fun requestShowDetail(
        showId: String
    ): Flow<ShowDetailResponse> = flow {
        emit(
            showService.requestShowDetail(showId)
        )
    }

    fun requestPopularShowList(
        type: String,
        size: Int,
        startDate: String
    ): Flow<List<ShowRankResponse>> = flow {
        emit(
            showService.requestPopularShowList(
                type = type,
                size = size,
                startDate = startDate
            ).showRanks
        )
    }

    fun requestFacilityDetail(
        facilityId: String
    ): Flow<FacilityDetailResponse> = flow {
        emit(
            showService.requestFacilityDetail(facilityId)
        )
    }
}
