package com.cmc.curtaincall.feature.show.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.type.ShowDetailMenuTab
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.SimilarShowInfoModel

data class ShowDetailUiState(
    val memberId: Int = -1,
    val menuTabType: ShowDetailMenuTab = ShowDetailMenuTab.DETAIL,
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val facilityDetailModel: FacilityDetailModel = FacilityDetailModel(),
    val showReviews: List<ShowReviewModel> = listOf(),
    val lostItems: List<LostPropertyModel> = listOf(),
    val similarShows: List<SimilarShowInfoModel> = listOf(),
    val isFavorite: Boolean = false
) : BaseState
