package com.cmc.curtaincall.feature.auth.signup

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<SignUpState, SignUpEvent, SignUpSideEffect>(
    initialState = SignUpState()
) {
    override fun reduceState(currentState: SignUpState, event: SignUpEvent): SignUpState =
        when (event) {
            is SignUpEvent.ChangeCheckState -> {
                currentState.copy(checkState = event.checkState)
            }

            is SignUpEvent.CheckDuplicateNickname -> {
                currentState.copy(
                    checkState = if (event.result) CheckState.Duplicate else CheckState.Validate
                )
            }
        }

    fun changeCheckState(checkState: CheckState) {
        sendAction(SignUpEvent.ChangeCheckState(checkState))
    }

    fun checkDuplicateNickname(nickname: String) {
        memberRepository.checkDuplicateNickname(nickname)
            .onEach { sendAction(SignUpEvent.CheckDuplicateNickname(it)) }
            .launchIn(viewModelScope)
    }
}
