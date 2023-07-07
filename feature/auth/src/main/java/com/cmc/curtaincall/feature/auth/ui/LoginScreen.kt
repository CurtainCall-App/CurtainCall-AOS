package com.cmc.curtaincall.feature.auth.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.feature.auth.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

private sealed interface AuthResult {
    data class Success(val idToken: String) : AuthResult
    data class Failure(val errorMsg: String?) : AuthResult
}

@Composable
fun LoginScreen(
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val registerGoogleLogin = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { intent ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                checkNotNull(task.result.idToken)
                onNavigateHome()
            }
        }
    }

    val loginManager = LoginManager.getInstance()
    val callbackManager = remember { CallbackManager.Factory.create() }
    val registerFacebookLogin = rememberLauncherForActivityResult(loginManager.createLogInActivityResultContract(callbackManager)) {}

    Column {
        Text(
            text = "login",
            modifier = Modifier.size(100.dp, 100.dp)
        )

        Button(
            onClick = { onNavigateSignUp() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "signup")
        }

        Button(
            onClick = { onNavigateHome() },
            modifier = Modifier.size(100.dp, 50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "home")
        }

        Button(
            onClick = {
                val signInClient = getGoogleSignInClient(context)
                registerGoogleLogin.launch(signInClient.signInIntent)
            },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(top = 10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "google")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    val result = loginKaKao(context)
                    if (result is AuthResult.Success) {
                        onNavigateHome()
                    }
                }
            },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(top = 10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "kakao")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    val result = loginFacebook(
                        loginManager = loginManager,
                        callbackManager = callbackManager,
                        launcher = registerFacebookLogin
                    )
                    if (result is AuthResult.Success) {
                        onNavigateHome()
                    }
                }
            },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .padding(top = 10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "facebook")
        }
    }
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient =
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestIdToken(context.getString(R.string.google_web_client_id)).build()
        .let { GoogleSignIn.getClient(context, it) }

private suspend fun loginKaKao(context: Context): AuthResult = suspendCancellableCoroutine { continuation ->
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                loginWithKaKaoAccount(context, continuation)
            } else if (token != null) {
                token.idToken?.let { continuation.resume(AuthResult.Success(it)) }
            }
        }
    } else {
        loginWithKaKaoAccount(context, continuation)
    }
}

private fun loginWithKaKaoAccount(
    context: Context,
    continuation: CancellableContinuation<AuthResult>
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            continuation.resume(AuthResult.Failure(error.localizedMessage))
        } else if (token != null) {
            token.idToken?.let { continuation.resume(AuthResult.Success(it)) }
        }
    }
}

private suspend fun loginFacebook(
    loginManager: LoginManager,
    callbackManager: CallbackManager,
    launcher: ManagedActivityResultLauncher<Collection<String>, CallbackManager.ActivityResultParameters>
): AuthResult = suspendCancellableCoroutine { continutation ->
    loginManager.registerCallback(
        callbackManager,
        object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                continutation.resume(AuthResult.Failure(IllegalStateException().localizedMessage))
            }

            override fun onError(error: FacebookException) {
                continutation.resume(AuthResult.Failure(error.localizedMessage))
            }

            override fun onSuccess(result: LoginResult) {
                continutation.resume(AuthResult.Success(result.accessToken.token))
            }
        }
    )
    launcher.launch(listOf("email", "public_profile"))
}
