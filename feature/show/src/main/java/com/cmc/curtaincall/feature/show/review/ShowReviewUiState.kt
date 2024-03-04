package com.cmc.curtaincall.feature.show.review

import androidx.paging.PagingData
import com.cmc.curtaincall.core.base.BaseState
import com.cmc.curtaincall.domain.enums.ReviewSortType
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ShowReviewUiState(
    val showReviewModels: Flow<PagingData<ShowReviewModel>> = flowOf(),
    val memberId: Int = 0,
    val hasMyReview: Boolean = false,
    val sortType: ReviewSortType = ReviewSortType.RECENTLY
) : BaseState
