package com.cmc.curtaincall.feature.mypage.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cmc.curtaincall.domain.model.member.MemberLostItemModel
import com.cmc.curtaincall.domain.model.member.MemberReviewModel
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

enum class WriteType(val value: String) {
    REVIEW("한 줄 리뷰"), LOST_ITEM("분실물")
}

@HiltViewModel
class MyPageWriteViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val reviewRepository: ReviewRepository,
    private val lostItemRepository: LostPropertyRepository
) : ViewModel() {

    private var _writeTypeState = MutableStateFlow(WriteType.REVIEW)
    val writeTypeState = _writeTypeState.asStateFlow()

    var reviewItems: Flow<PagingData<MemberReviewModel>> = memberRepository
        .fetchMyReview()
        .cachedIn(viewModelScope)

    var lostItems: Flow<PagingData<MemberLostItemModel>> = memberRepository
        .fetchMyLostItems()
        .cachedIn(viewModelScope)

    fun changeWriteType(writeType: WriteType) {
        _writeTypeState.value = writeType
    }

    fun deleteShowReview(reviewId: Int) {
        reviewRepository
            .deleteShowReview(reviewId)
            .launchIn(viewModelScope)
    }

    fun deleteLostItem(lostItemId: Int) {
        lostItemRepository
            .deleteLostProperty(lostItemId)
            .launchIn(viewModelScope)
    }

    fun loadReviewItems() {
        reviewItems = memberRepository
            .fetchMyReview()
            .cachedIn(viewModelScope)
    }

    fun loadLostItems() {
        lostItems = memberRepository
            .fetchMyLostItems()
            .cachedIn(viewModelScope)
    }
}
