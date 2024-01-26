package com.cmc.curtaincall.feature.auth.login.naver

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.feature.auth.login.LoginViewModel
import com.navercorp.nid.NaverIdLoginSDK
import timber.log.Timber

private const val LOGIN_NAVER_BUTTON = "LOGIN_NAVER_BUTTON"

@Composable
fun LoginNaverButton(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateSignUpTerms: () -> Unit = {},
    onNavigateHome: () -> Unit = {}
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                Timber.d("Naver Login Success Token: ${NaverIdLoginSDK.getAccessToken()}")
            }

            RESULT_CANCELED -> {
                Timber.e("Naver Login Error: ${NaverIdLoginSDK.getLastErrorDescription()}")
            }
        }
    }

    Image(
        painter = painterResource(R.drawable.ic_naver_login),
        contentDescription = LOGIN_NAVER_BUTTON,
        modifier = modifier
            .size(50.dp)
            .clickable {
                NaverIdLoginSDK.authenticate(context, launcher)
            }
    )
}
