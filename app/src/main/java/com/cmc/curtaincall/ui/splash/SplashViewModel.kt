package com.cmc.curtaincall.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.common.utility.extensions.getTodayDate
import com.cmc.curtaincall.domain.model.auth.LoginResultModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.MemberRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
    private val memberRepository: MemberRepository
) : ViewModel() {

    init {
        isValidationToken()
    }

    private val _isAutoLogin = MutableStateFlow(false)
    val isAutoLogin: StateFlow<Boolean> = _isAutoLogin.asStateFlow()

    private fun isValidationToken() {
        val today = getTodayDate()
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
                    _isAutoLogin.value = loginResult.memberId != Int.MIN_VALUE
                } else {
                    _isAutoLogin.value = false
                }
            }.launchIn(viewModelScope)
    }
}
