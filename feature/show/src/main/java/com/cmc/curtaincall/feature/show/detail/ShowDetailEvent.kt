package com.cmc.curtaincall.feature.show.detail

import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.enum.ShowDetailMenuTab
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowDetailModel
import com.cmc.curtaincall.domain.model.show.SimilarShowInfoModel

sealed class ShowDetailEvent : BaseEvent {

    data class GetMemberId(
        val memberId: Int
    ) : ShowDetailEvent()

    data class ShowDetail(
        val showDetailModel: ShowDetailModel
    ) : ShowDetailEvent()

    data class FacilityDetail(
        val facilityDetailModel: FacilityDetailModel
    ) : ShowDetailEvent()

    data class ShowReviewList(
        val showReviews: List<ShowReviewModel>
    ) : ShowDetailEvent()

    data class LostItemList(
        val lostItems: List<LostPropertyModel>
    ) : ShowDetailEvent()

    data class ChangeTabType(
        val tabType: ShowDetailMenuTab
    ) : ShowDetailEvent()

    data class SimilarShowList(
        val similarShows: List<SimilarShowInfoModel>
    ) : ShowDetailEvent()

    object FavoriteShow : ShowDetailEvent()
    object DeleteFavoriteShow : ShowDetailEvent()
}
