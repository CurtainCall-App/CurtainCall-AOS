package com.cmc.curtaincall.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository,
    private val memberRepository: MemberRepository
) : BaseViewModel<LoginState, Nothing, LoginSideEffect>(
    initialState = LoginState
) {
    override fun reduceState(currentState: LoginState, event: Nothing): LoginState = currentState

    fun fetchLogin(registrationId: String, accessToken: String) {
        authRepository.requestLogin(registrationId, accessToken)
            .onEach { resultModel ->
                if (resultModel.memberId != null) {
                    resultModel.memberId?.let { id -> memberRepository.saveMemberId(id) }
                    tokenRepository.saveToken(resultModel)
                    sendSideEffect(LoginSideEffect.ExistMember)
                } else {
                    tokenRepository.saveToken(resultModel)
                    sendSideEffect(LoginSideEffect.SuccessLogin)
                }
            }
            .launchIn(viewModelScope)
    }
}
