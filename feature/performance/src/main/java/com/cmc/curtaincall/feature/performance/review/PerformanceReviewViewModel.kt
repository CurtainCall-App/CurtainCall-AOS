package com.cmc.curtaincall.feature.performance.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PerformanceReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _effect = MutableSharedFlow<PerformanceReviewSideEffect>()
    val effect = _effect.asSharedFlow()

    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ) {
        reviewRepository.createShowReview(showId, grade, content)
            .onEach { _effect.emit(PerformanceReviewSideEffect.CreateSuccess) }
            .launchIn(viewModelScope)
    }
}
