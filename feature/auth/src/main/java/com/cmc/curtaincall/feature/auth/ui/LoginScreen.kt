package com.cmc.curtaincall.feature.auth.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.feature.auth.R
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

@Composable
fun LoginScreen(
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val registerGoogleLogin =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { intent ->
                    val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    checkNotNull(task.result.idToken)
                    onNavigateHome()
                }
            }
        }

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
                    if (result is KaKaoLoginResult.Success) {
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
    }
}

private fun getGoogleSignInClient(context: Context): GoogleSignInClient =
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(R.string.google_web_client_id))
        .build()
        .let { GoogleSignIn.getClient(context, it) }

internal sealed interface KaKaoLoginResult {
    data class Success(val idToken: String) : KaKaoLoginResult
    data class Failure(val errorMsg: String) : KaKaoLoginResult
}

private suspend fun loginKaKao(context: Context): KaKaoLoginResult = suspendCancellableCoroutine { continuation ->
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                loginWithKaKaoAccount(context, continuation)
            } else if (token != null) {
                token.idToken?.let { continuation.resume(KaKaoLoginResult.Success(it)) }
            }
        }
    } else {
        loginWithKaKaoAccount(context, continuation)
    }
}

private fun loginWithKaKaoAccount(
    context: Context,
    continuation: CancellableContinuation<KaKaoLoginResult>
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            continuation.resume(KaKaoLoginResult.Failure(error.localizedMessage ?: NullPointerException().localizedMessage))
        } else if (token != null) {
            token.idToken?.let { continuation.resume(KaKaoLoginResult.Success(it)) }
        }
    }
}
