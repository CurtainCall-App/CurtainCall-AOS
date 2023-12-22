package com.cmc.curtaincall.feature.auth.login.facebook

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.feature.auth.login.LoginResponse
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

private const val FACEBOOK_EMAIL = "email"
private const val FACEBOOK_PROFILE = "public_profile"

@Composable
private fun LoginFacebookButton(
    onNavigateSignUpTerms: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val loginManager = LoginManager.getInstance()
    val callbackManager = remember { CallbackManager.Factory.create() }
    val registerFacebookLogin = rememberLauncherForActivityResult(
        contract = loginManager.createLogInActivityResultContract(callbackManager),
        onResult = {}
    )

    Image(
        painter = painterResource(R.drawable.ic_facebook_login),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clickable {
                coroutineScope.launch {
                    when (val result = loginFacebook(loginManager, callbackManager, registerFacebookLogin)) {
                        is LoginResponse.Success -> {
                            onNavigateSignUpTerms()
                        }

                        is LoginResponse.Failure -> {
                            Timber.e(result.errorMsg)
                        }
                    }
                }
            }
    )
}

private suspend fun loginFacebook(
    loginManager: LoginManager,
    callbackManager: CallbackManager,
    launcher: ManagedActivityResultLauncher<Collection<String>, CallbackManager.ActivityResultParameters>
): LoginResponse = suspendCancellableCoroutine { continutation ->
    loginManager.registerCallback(
        callbackManager,
        object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                continutation.resume(LoginResponse.Failure(IllegalStateException().localizedMessage))
            }

            override fun onError(error: FacebookException) {
                continutation.resume(LoginResponse.Failure(error.localizedMessage))
            }

            override fun onSuccess(result: LoginResult) {
                continutation.resume(LoginResponse.Success(result.accessToken.token))
            }
        }
    )
    launcher.launch(listOf(FACEBOOK_EMAIL, FACEBOOK_PROFILE))
}
