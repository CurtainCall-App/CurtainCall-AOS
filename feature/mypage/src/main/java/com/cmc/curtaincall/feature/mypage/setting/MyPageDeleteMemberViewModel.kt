package com.cmc.curtaincall.feature.mypage.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

enum class DeleteReason(val value: String) {
    RECORD_DELETION("기록을 삭제하기 위해"),
    INCONVENIENCE_FREQUENT_ERROR("이용이 불편하고 장애가 잦아서"),
    BETTER_OTHER_SERVICE("타 서비스가 더 좋아서"),
    LOW_USAGE_FREQUENCY("사용빈도가 낮아서"),
    NOT_USEFUL("앱 기능이 유용하지 않아서"),
    ETC("기타"),
    NONE("")
}

@HiltViewModel
class MyPageDeleteMemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {
    private var _deleteReason = MutableStateFlow<DeleteReason>(DeleteReason.NONE)
    val deleteReason = _deleteReason.asStateFlow()

    private var _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    private var _isSuccessDelete = MutableStateFlow(false)
    val isSuccessDelete = _isSuccessDelete.asStateFlow()

    fun setDeleteReason(deleteReason: DeleteReason) {
        _deleteReason.value = deleteReason
        if (deleteReason != DeleteReason.ETC) _content.value = ""
    }

    fun setContent(content: String) {
        _content.value = content
    }

    fun deleteMember() {
        memberRepository.deleteMember(
            reason = deleteReason.value.name,
            content = content.value
        ).onEach {
            _isSuccessDelete.value = it
        }.launchIn(viewModelScope)
    }
}
