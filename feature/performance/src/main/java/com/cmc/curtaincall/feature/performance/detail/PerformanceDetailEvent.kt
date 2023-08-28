package com.cmc.curtaincall.feature.performance.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.lostItem.LostItemModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

sealed class PerformanceDetailEvent : BaseEvent {

    data class GetMemberId(
        val memberId: Int
    ) : PerformanceDetailEvent()

    data class ShowDetail(
        val showDetailModel: ShowDetailModel
    ) : PerformanceDetailEvent()

    data class FacilityDetail(
        val facilityDetailModel: FacilityDetailModel
    ) : PerformanceDetailEvent()

    data class ShowReviewList(
        val showReviews: List<ShowReviewModel>
    ) : PerformanceDetailEvent()

    data class LostItemList(
        val lostItems: List<LostItemModel>
    ) : PerformanceDetailEvent()

    data class ChangeTabType(
        val tabType: TabType
    ) : PerformanceDetailEvent()

    object FavoriteShow : PerformanceDetailEvent()
    object DeleteFavoriteShow : PerformanceDetailEvent()
}
