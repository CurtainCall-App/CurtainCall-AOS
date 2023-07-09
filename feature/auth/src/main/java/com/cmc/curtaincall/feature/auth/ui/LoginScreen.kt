package com.cmc.curtaincall.feature.auth.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.French_Rose
import com.cmc.curtaincall.common.design.theme.Gunmetal
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gunmetal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = stringResource(R.string.login_logo_image_description),
            modifier = Modifier.padding(top = 217.dp)
        )

        Text(
            text = stringResource(R.string.login_start),
            modifier = Modifier
                .padding(top = 111.dp)
                .background(French_Rose, RoundedCornerShape(22.dp))
                .padding(vertical = 11.dp, horizontal = 17.dp),
            color = White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 21.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginKaKao(onNavigateSignUp, onNavigateHome)
            Spacer(Modifier.width(16.dp))
            LoginGoogle(onNavigateSignUp, onNavigateHome)
            Spacer(Modifier.width(16.dp))
            LoginFacebook(onNavigateSignUp, onNavigateHome)
        }
    }
}

@Composable
private fun LoginKaKao(
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Image(
        painter = painterResource(R.drawable.ic_kakao_login),
        contentDescription = stringResource(R.string.login_kakao),
        modifier = Modifier
            .size(48.dp)
            .clickable {
                coroutineScope.launch {
                    when (loginKaKao(context)) {
                        is AuthResult.Success -> {
                            // TODO 카카오 AccessToken 서버 전달
                            onNavigateHome()
                        }
                        is AuthResult.Failure -> {
                            // TODO 카카오 로그인 에러 메세지 정의
                        }
                    }
                }
            }
    )
}

@Composable
private fun LoginGoogle(
    onNavigateSignUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val registerGoogleLogin = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // TODO 구글 AcceessToken 서버 전달
            result.data?.let { intent ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                task.result.idToken?.let { token ->
                    onNavigateHome()
                }
            }
        } else {
            // TODO 구글 로그인 에러 메세지 정의
        }
    }

    Image(
        painter = painterResource(R.drawable.ic_google_login),
        contentDescription = stringResource(R.string.login_google),
        modifier = Modifier
            .size(48.dp)
            .clickable {
                val signInClient = getGoogleSignInClient(context)
                registerGoogleLogin.launch(signInClient.signInIntent)
            }
    )
}

@Composable
private fun LoginFacebook(
    onNavigateSignUp: () -> Unit,
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
        contentDescription = stringResource(R.string.login_facebook),
        modifier = Modifier
            .size(48.dp)
            .clickable {
                coroutineScope.launch {
                    when (loginFacebook(loginManager, callbackManager, registerFacebookLogin)) {
                        is AuthResult.Success -> {
                            // TODO 페이스북 AccessToken 전달
                            onNavigateHome()
                        }
                        is AuthResult.Failure -> {
                            // TODO 페이스북 로그인 에러 메세지 정의
                        }
                    }
                }
            }
    )
}

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

private fun getGoogleSignInClient(context: Context): GoogleSignInClient =
    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(com.cmc.curtaincall.feature.auth.R.string.google_web_client_id))
        .build()
        .let { GoogleSignIn.getClient(context, it) }

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
