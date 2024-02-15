package com.cmc.curtaincall.feature.show.review

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.RootViewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
    private val memberRepository: MemberRepository
) : RootViewModel<ShowReviewSideEffect>() {

    private val _showReviewModels = MutableStateFlow<PagingData<ShowReviewModel>>(PagingData.empty())
    val showReviewModel = _showReviewModels.asStateFlow()

    private val _memberId = MutableStateFlow(Int.MAX_VALUE)
    val memberId = _memberId.asStateFlow()

    init {
        getMemberId()
    }

    var reviewItems: Flow<PagingData<ShowReviewModel>> = reviewRepository
        .fetchShowReviewList("")
        .cachedIn(viewModelScope)

    fun fetchShowReviewList(showId: String) {
        reviewRepository.fetchShowReviewList(showId)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { _showReviewModels.value = it }
            .launchIn(viewModelScope)
    }

    // ///
    fun requestShowReviewList(showId: String) {
        reviewItems = reviewRepository
            .fetchShowReviewList(showId)
            .cachedIn(viewModelScope)
    }

    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ) {
        reviewRepository.createShowReview(showId, grade, content)
            .onEach { sendSideEffect(ShowReviewSideEffect.CreateSuccess) }
            .launchIn(viewModelScope)
    }

    fun updateShowReview(
        reviewId: Int,
        content: String,
        grade: Int
    ) {
        reviewRepository.updateShowReview(reviewId, content, grade)
            .onEach { sendSideEffect(ShowReviewSideEffect.UpdateSuccess) }
            .launchIn(viewModelScope)
    }

    fun deleteShowReview(reviewId: Int) {
        reviewRepository.deleteShowReview(reviewId)
            .onEach { sendSideEffect(ShowReviewSideEffect.DeleteSuccess) }
            .launchIn(viewModelScope)
    }

    fun requestLikeReview(reviewId: Int) {
        reviewRepository.requestLikeReview(reviewId).launchIn(viewModelScope)
    }

    fun requestDislikeReview(reviewId: Int) {
        reviewRepository.requestDislikeReview(reviewId).launchIn(viewModelScope)
    }

    private fun getMemberId() {
        memberRepository.getMemberId()
            .onEach { _memberId.value = it }
            .launchIn(viewModelScope)
    }
}
