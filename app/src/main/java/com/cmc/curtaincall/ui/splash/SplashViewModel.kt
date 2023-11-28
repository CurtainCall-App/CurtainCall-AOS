package com.cmc.curtaincall.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
    private val memberRepository: MemberRepository
) : BaseViewModel<SplashState, Nothing, SplashSideEffect>(
    initialState = SplashState
) {

    init {
        isValidationToken()
    }

    override fun reduceState(currentState: SplashState, event: Nothing): SplashState {
        return currentState
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("SimpleDateFormat")
    fun isValidationToken() {
        val today = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().time)
        tokenRepository.getAccessToken()
            .zip(tokenRepository.getAccessTokenExpiresAt()) { accessToken, accessTokenExpiresAt ->
                LoginResultModel(
                    accessToken = accessToken,
                    accessTokenExpiresAt = accessTokenExpiresAt
                )
            }.zip(memberRepository.getMemberId()) { loginResultModel, memberId ->
                loginResultModel.copy(memberId = memberId)
            }.onEach { loginResult ->
                if (loginResult.accessToken.isNotEmpty() && loginResult.accessTokenExpiresAt > today) {
                    if (loginResult.memberId == Int.MIN_VALUE) {
                        sendSideEffect(SplashSideEffect.NeedLogin)
                    } else {
                        sendSideEffect(SplashSideEffect.AutoLogin)
                    }
                } else {
                    sendSideEffect(SplashSideEffect.NeedLogin)
                }
            }.launchIn(viewModelScope)
    }
}
