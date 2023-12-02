package com.cmc.curtaincall.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.auth.login.kakao.LoginKaKaoButton
import kotlinx.coroutines.flow.collectLatest

private const val CURTAINCALL_LOGO = "CURTAINCALL_LOGO"
private const val CURTAINCALL_START_LOGIN_DESCRIPTION = "CURTAINCALL_START_LOGIN_DESCRIPTION"

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateSignUpTerms: () -> Unit,
    onNavigateHome: () -> Unit
) {
    SystemUiStatusBar(color = Cetacean_Blue)
    LaunchedEffect(loginViewModel) {
        loginViewModel.effects.collectLatest { sideEffect ->
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
        Spacer(Modifier.weight(209.35f))
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = CURTAINCALL_LOGO,
            modifier = Modifier.size(60.dp, (67.79).dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.weight(50.85f))
        Image(
            painter = painterResource(R.drawable.ic_social_login),
            contentDescription = CURTAINCALL_START_LOGIN_DESCRIPTION,
            modifier = Modifier.size(180.dp, 47.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.weight(16f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginKaKaoButton(
                loginViewModel = loginViewModel,
                onNavigateSignUpTerms = onNavigateSignUpTerms,
                onNavigateHome = onNavigateHome
            )
        }
        Spacer(Modifier.weight(193f))
        Text(
            text = stringResource(R.string.login_start_without_login),
            color = Me_Pink,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            lineHeight = 22.dp.toSp()
        )
        Spacer(Modifier.weight(72f))
    }
}
