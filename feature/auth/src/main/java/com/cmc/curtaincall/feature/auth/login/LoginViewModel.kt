package com.cmc.curtaincall.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
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

    fun fetchLogin(idToken: String) {
        authRepository.requestLogin(idToken)
            .onStart { tokenRepository.saveIdToken(idToken) }
            .onEach { resultModel ->
                if (resultModel.memberId != null) {
                    resultModel.memberId?.let { memberRepository.saveMemberId(it) }
                    tokenRepository.saveToken(resultModel)
                    sendSideEffect(LoginSideEffect.ExistMember)
                } else {
                    tokenRepository.saveToken(resultModel)
                    sendSideEffect(LoginSideEffect.SuccessLogin)
                }
            }.catch {
                if (it is HttpException) {
                    sendSideEffect(LoginSideEffect.SuccessLogin)
                }
            }
            .launchIn(viewModelScope)
    }
}
