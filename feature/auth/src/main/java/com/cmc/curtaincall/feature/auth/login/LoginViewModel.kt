package com.cmc.curtaincall.feature.auth.login

import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel<LoginState, LoginEvent,Nothing>(
    initialState = LoginState()
){
    override fun reduceState(currentState: LoginState, event: LoginEvent): LoginState {
        return currentState
    }
}