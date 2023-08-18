package com.cmc.curtaincall.feature.auth.login

import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import com.cmc.curtaincall.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
}
