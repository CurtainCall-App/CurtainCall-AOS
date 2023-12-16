package com.cmc.curtaincall.feature.show.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.SimilarShowInfoModel

data class PerformanceDetailState(
    val memberId: Int = -1,
    val tabType: TabType = TabType.DETAIL,
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val facilityDetailModel: FacilityDetailModel = FacilityDetailModel(),
    val showReviews: List<ShowReviewModel> = listOf(),
    val lostItems: List<LostItemModel> = listOf(),
    val similarShows: List<SimilarShowInfoModel> = listOf(),
    val isFavorite: Boolean = false
) : BaseState
