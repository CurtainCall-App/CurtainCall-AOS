package com.cmc.curtaincall.data.repository

import com.cmc.curtaincall.data.source.remote.ShowRemoteSource
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.ShowInfoModel
import com.cmc.curtaincall.domain.model.show.ShowRankModel
import com.cmc.curtaincall.domain.repository.ShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(
    private val showRemoteSource: ShowRemoteSource
) : ShowRepository {
    override fun requestShowList(page: Int, size: Int, genre: String): Flow<List<ShowInfoModel>> =
        showRemoteSource.requestShowList(page, size, genre).map { showInfoResponses ->
            showInfoResponses.map { it.toModel() }
        }

    override fun searchShowList(page: Int, size: Int, keyword: String): Flow<List<ShowInfoModel>> =
        showRemoteSource.searchShowList(page, size, keyword).map { showInfoResponse ->
            showInfoResponse.map { it.toModel() }
        }

    override fun requestOpenShowList(page: Int, size: Int, startDate: String): Flow<List<ShowInfoModel>> =
        showRemoteSource.requestOpenShowList(page, size, startDate).map { showInfoResponse ->
            showInfoResponse.map { it.toModel() }
        }

    override fun requestShowDetail(showId: String): Flow<ShowDetailModel> =
        showRemoteSource.requestShowDetail(showId).map { it.toModel() }

    override fun requestPopularShowList(type: String, genre: String, baseDate: String): Flow<List<ShowRankModel>> =
        showRemoteSource.requestPopularShowList(type, genre, baseDate).map { showRankResponse ->
            showRankResponse.map { it.toModel() }
        }

    override fun requestFacilityDetail(facilityId: String): Flow<FacilityDetailModel> =
        showRemoteSource.requestFacilityDetail(facilityId).map { it.toModel() }
}
