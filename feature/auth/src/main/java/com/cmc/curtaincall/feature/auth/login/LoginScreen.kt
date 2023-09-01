package com.cmc.curtaincall.feature.auth.login

import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

private sealed interface AuthResult {
    data class Success(val idToken: String) : AuthResult
    data class Failure(val errorMsg: String?) : AuthResult
}

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateSignUpTerms: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    LaunchedEffect(loginViewModel) {
        loginViewModel.effects.collectLatest { sideEffect ->
            Timber.d("LoginScreen $sideEffect")
            when (sideEffect) {
                LoginSideEffect.SuccessLogin -> {
                    onNavigateSignUpTerms()
                }

                LoginSideEffect.ExistMember -> {
                    onNavigateHome()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cetacean_Blue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(244f))
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.size(60.dp, (67.79).dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.weight(224f))
        Image(
            painter = painterResource(R.drawable.ic_social_login),
            contentDescription = null,
            modifier = Modifier.size(180.dp, 47.dp),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginKaKao(
                loginViewModel = loginViewModel,
                onNavigateSignUpTerms = onNavigateSignUpTerms,
                onNavigateHome = onNavigateHome
            )
            Spacer(Modifier.width(16.dp))
            LoginGoogle(onNavigateSignUpTerms, onNavigateHome)
            Spacer(Modifier.width(16.dp))
            LoginFacebook(onNavigateSignUpTerms, onNavigateHome)
        }
        Spacer(Modifier.weight(65f))
        Text(
            text = stringResource(R.string.login_inquire),
            color = White.copy(0.7f),
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(78f))
    }
}

@Composable
private fun LoginKaKao(
    loginViewModel: LoginViewModel,
    onNavigateSignUpTerms: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Image(
        painter = painterResource(R.drawable.ic_kakao_login),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clickable {
                coroutineScope.launch {
                    val result = loginKaKao(context)
                    when (result) {
                        is AuthResult.Success -> {
                            // TODO 카카오  AccessToken 서버 전달
                            loginViewModel.fetchLogin("kakao", result.idToken)
                            // onNavigateSignUpTerms()
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
    onNavigateSignUpTerms: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val context = LocalContext.current
    val registerGoogleLogin = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // TODO 구글 AcceessToken 서버 전달
            result.data?.let { intent ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                task.result.idToken?.let { token ->
                    onNavigateSignUpTerms()
                }
            }
        } else {
            // TODO 구글 로그인 에러 메세지 정의
        }
    }

    Image(
        painter = painterResource(R.drawable.ic_google_login),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clickable {
                val signInClient = getGoogleSignInClient(context)
                registerGoogleLogin.launch(signInClient.signInIntent)
            }
    )
}

@Composable
private fun LoginFacebook(
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
                    when (loginFacebook(loginManager, callbackManager, registerFacebookLogin)) {
                        is AuthResult.Success -> {
                            // TODO 페이스북 AccessToken 전달
                            onNavigateSignUpTerms()
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
                Timber.e("loginKaKao error ${error.message}")
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                loginWithKaKaoAccount(context, continuation)
            } else if (token != null) {
                continuation.resume(AuthResult.Success(token.accessToken))
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
            continuation.resume(AuthResult.Success(token.accessToken))
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
