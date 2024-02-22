package com.cmc.curtaincall.feature.show.review

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.core.base.RootViewModel
import com.cmc.curtaincall.domain.model.review.ShowReviewModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowReviewViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val reviewRepository: ReviewRepository
) : RootViewModel<ShowReviewSideEffect>() {

    private val _showReviewModels = MutableStateFlow<PagingData<ShowReviewModel>>(PagingData.empty())
    val showReviewModel = _showReviewModels.asStateFlow()

    private val _memberId = MutableStateFlow(Int.MAX_VALUE)
    val memberId = _memberId.asStateFlow()

    private val _isRefresh = MutableSharedFlow<Boolean>()
    val isRefresh = _isRefresh.asSharedFlow()

    init {
        getMemberId()
    }

    private fun getMemberId() {
        memberRepository.getMemberId()
            .onEach { _memberId.value = it }
            .launchIn(viewModelScope)
    }

    fun fetchShowReviewList(showId: String) {
        reviewRepository.fetchShowReviewList(showId)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .onEach { _showReviewModels.value = it }
            .launchIn(viewModelScope)
    }

    fun selectLikeReview(
        reviewId: Int,
        isFavorite: Boolean
    ) {
        if (isFavorite) {
            reviewRepository.requestLikeReview(reviewId)
                .onEach { _isRefresh.emit(true) }
                .launchIn(viewModelScope)
        } else {
            reviewRepository.requestDislikeReview(reviewId)
                .onEach { _isRefresh.emit(true) }
                .launchIn(viewModelScope)
        }
    }

    fun createShowReview(
        showId: String,
        grade: Int,
        content: String
    ) {
        reviewRepository.createShowReview(showId, grade, content)
            .onEach { sendSideEffect(ShowReviewSideEffect.ReviewCreated) }
            .launchIn(viewModelScope)
    }

    // ///

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
}
