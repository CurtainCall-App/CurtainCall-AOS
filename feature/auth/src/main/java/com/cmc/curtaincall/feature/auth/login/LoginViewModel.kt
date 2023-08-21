package com.cmc.curtaincall.feature.auth.login

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
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
            }.zip(tokenRepository.getRefreshToken()) { loginResultModel, refreshToken ->
                loginResultModel.copy(refreshToken = refreshToken)
            }.zip(tokenRepository.getRefreshTokenExpiresAt()) { loginResultModel, refreshTokenExpiresAt ->
                loginResultModel.copy(refreshTokenExpiresAt = refreshTokenExpiresAt)
            }.onEach { loginResult ->
                Timber.d(
                    "isValidationToken init ${loginResult.accessToken} " +
                        "${loginResult.accessTokenExpiresAt} " +
                        "${loginResult.refreshToken} " +
                        loginResult.refreshTokenExpiresAt
                )
                if (loginResult.accessToken.isNotEmpty() && loginResult.accessTokenExpiresAt > today) {
                    sendSideEffect(LoginSideEffect.AutoLogin)
                }
            }.filter { loginResultModel ->
                loginResultModel.accessToken.isNotEmpty() && (loginResultModel.accessTokenExpiresAt < today) && loginResultModel.refreshTokenExpiresAt > today
            }.flatMapLatest {
                authRepository.requestReissue(it.refreshToken)
            }.onEach { loginResult ->
                tokenRepository.saveToken(loginResult)
                if (loginResult.accessToken.isNotEmpty() && loginResult.accessTokenExpiresAt > today) {
                    sendSideEffect(LoginSideEffect.AutoLogin)
                }
            }.launchIn(viewModelScope)
    }
}
