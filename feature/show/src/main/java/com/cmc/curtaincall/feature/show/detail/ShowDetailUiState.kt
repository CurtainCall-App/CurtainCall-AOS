package com.cmc.curtaincall.feature.show.detail

import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.enums.MenuTabType
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

data class ShowDetailUiState(
    val memberId: Int = -1,
    val menuTabType: MenuTabType = MenuTabType.DETAIL,
    val showDetailModel: ShowDetailModel = ShowDetailModel(),
    val facilityDetailModel: FacilityDetailModel = FacilityDetailModel(),
    val showReviews: List<ShowReviewModel> = listOf(),
    val lostProperties: List<LostPropertyModel> = listOf(),
    val isFavorite: Boolean = false,
    val isShowCoachMark: Boolean = true
) : BaseState
