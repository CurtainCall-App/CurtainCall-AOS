package com.cmc.curtaincall.feature.auth.signup.input

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.RootViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

enum class NickNameCheck {
    None, Validate, Duplicate
}

@HiltViewModel
class SignUpInputViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val memberRepository: MemberRepository
) : RootViewModel<Nothing>() {

    private val _nickNameCheck = MutableStateFlow(NickNameCheck.None)
    val nickNameCheck = _nickNameCheck.asStateFlow()

    private val _signUpCompleted = MutableSharedFlow<Boolean>()
    val signUpCompleted = _signUpCompleted.asSharedFlow()

    fun initNicknameCheck() {
        _nickNameCheck.value = NickNameCheck.None
    }

    fun checkDuplicateNickname(nickname: String) {
        authRepository.checkDuplicateNickname(nickname)
            .onEach { isDuplicate ->
                _nickNameCheck.value = if (isDuplicate) NickNameCheck.Duplicate else NickNameCheck.Validate
            }.launchIn(viewModelScope)
    }

    fun createMember(nickname: String) {
        memberRepository.createMember(nickname)
            .onEach { memberId ->
                memberRepository.saveMemberId(memberId)
                memberRepository.saveMemberNickname(nickname)
                _signUpCompleted.emit(true)
            }.launchIn(viewModelScope)
    }
}
