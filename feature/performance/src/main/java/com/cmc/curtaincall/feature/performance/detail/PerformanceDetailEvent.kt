package com.cmc.curtaincall.feature.performance.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

sealed class PerformanceDetailEvent : BaseEvent {

    data class ShowDetail(
        val showDetailModel: ShowDetailModel
    ) : PerformanceDetailEvent()

    data class FacilityDetail(
        val facilityDetailModel: FacilityDetailModel
    ) : PerformanceDetailEvent()

    data class ShowReviewList(
        val showReviews: List<ShowReviewModel>
    ) : PerformanceDetailEvent()
}
