package com.cmc.curtaincall.feature.auth.login

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) : BaseViewModel<LoginState, LoginEvent, LoginSideEffect>(
    initialState = LoginState()
) {

    override fun reduceState(currentState: LoginState, event: LoginEvent): LoginState =
        when (event) {
            is LoginEvent.SuccessLogin -> {
                currentState.copy(loginResultModel = event.loginResultModel)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchLogin(registrationId: String, accessToken: String) {
        authRepository.requestLogin(registrationId, accessToken)
            .onEach {
                tokenRepository.saveToken(it)
                sendAction(LoginEvent.SuccessLogin(it))
                sendSideEffect(LoginSideEffect.SuccessLogin)
            }
            .launchIn(viewModelScope)
    }

    @SuppressLint("SimpleDateFormat")
    fun isValidationToken() {
        tokenRepository.getAccessToken()
            .zip(tokenRepository.getAccessTokenExpiresAt()) { accessToken, accessTokenExpiresAt ->
                val today = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().time)
                accessToken.isNotEmpty() && today < accessTokenExpiresAt
            }.onEach { isValidation ->
                if (isValidation) sendSideEffect(LoginSideEffect.AutoLogin)
            }
            .launchIn(viewModelScope)
    }
}
