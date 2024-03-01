package com.cmc.curtaincall.feature.show.review

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class ShowReviewSideEffect : BaseSideEffect {
    object RefreshShowReview : ShowReviewSideEffect()

    object DeleteMyReview : ShowReviewSideEffect()

    object CreateMyReview : ShowReviewSideEffect()
}
