package com.cmc.curtaincall.feature.show.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.enums.MenuTabType
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel

sealed class ShowDetailEvent : BaseEvent {

    data class ChangeMenuTabType(
        val menuTabType: MenuTabType
    ) : ShowDetailEvent()

    data class SelectFavorite(
        val isFavorite: Boolean
    ) : ShowDetailEvent()

    object CloseCoachMark : ShowDetailEvent()

    data class RequestShowDetail(
        val showDetailModel: ShowDetailModel
    ) : ShowDetailEvent()

    data class RequestFacilityDetail(
        val facilityDetailModel: FacilityDetailModel
    ) : ShowDetailEvent()

    data class RequestLostPropertyList(
        val lostProperties: List<LostPropertyModel>
    ) : ShowDetailEvent()

    data class RequestShowReviewList(
        val showReviews: List<ShowReviewModel>
    ) : ShowDetailEvent()
}
