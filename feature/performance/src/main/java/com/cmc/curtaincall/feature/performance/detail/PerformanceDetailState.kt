package com.cmc.curtaincall.feature.performance.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

data class PerformanceDetailState(
    val memberId: Int = -1,
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val facilityDetailModel: FacilityDetailModel = FacilityDetailModel(),
    val showReviews: List<ShowReviewModel> = listOf()
) : BaseState
