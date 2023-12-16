package com.cmc.curtaincall.feature.show.review

sealed class PerformanceReviewSideEffect {
    object CreateSuccess : PerformanceReviewSideEffect()
    object UpdateSuccess : PerformanceReviewSideEffect()

    object DeleteSuccess : PerformanceReviewSideEffect()
}
