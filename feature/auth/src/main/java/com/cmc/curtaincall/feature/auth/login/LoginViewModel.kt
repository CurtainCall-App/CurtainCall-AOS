package com.cmc.curtaincall.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.RootViewModel
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
) : RootViewModel<LoginSideEffect>() {
    fun fetchLogin(
        provider: String,
        token: String
    ) {
        authRepository.requestLogin(provider, token)
            // .onStart { tokenRepository.saveIdToken(idToken) }
            .onEach { resultModel ->
                tokenRepository.saveToken(resultModel)
                resultModel.memberId?.let {
                    memberRepository.saveMemberId(it)
                    sendSideEffect(LoginSideEffect.ExistMember)
                } ?: kotlin.run {
                    sendSideEffect(LoginSideEffect.SuccessLogin)
                }
            }.launchIn(viewModelScope)
    }
}
