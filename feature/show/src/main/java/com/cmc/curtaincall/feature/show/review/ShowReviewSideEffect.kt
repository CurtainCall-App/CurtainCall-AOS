package com.cmc.curtaincall.feature.show.review

import com.cmc.curtaincall.core.base.BaseSideEffect

sealed class ShowReviewSideEffect : BaseSideEffect {
    object ReviewCreated : ShowReviewSideEffect()

    object UpdateSuccess : ShowReviewSideEffect()

    object DeleteSuccess : ShowReviewSideEffect()
}
