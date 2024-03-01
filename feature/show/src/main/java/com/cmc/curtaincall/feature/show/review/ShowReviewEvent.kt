package com.cmc.curtaincall.feature.show.review

import androidx.paging.PagingData
import com.cmc.curtaincall.core.base.BaseEvent
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import kotlinx.coroutines.flow.Flow

sealed class ShowReviewEvent : BaseEvent {

    data class GetMemberID(
        val memberId: Int
    ) : ShowReviewEvent()

    data class FetchShowReviewList(
        val showReviewModels: Flow<PagingData<ShowReviewModel>>
    ) : ShowReviewEvent()

    data class CheckMyReview(
        val hasMyReview: Boolean
    ) : ShowReviewEvent()
}
