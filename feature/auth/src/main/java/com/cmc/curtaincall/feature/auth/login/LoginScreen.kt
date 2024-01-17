package com.cmc.curtaincall.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Eerie_Black
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
    SystemUiStatusBar(Eerie_Black)
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
            .background(Eerie_Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(228f))
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = CURTAINCALL_LOGO,
            modifier = Modifier.size(56.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.weight(60f))
        Image(
            painter = painterResource(R.drawable.ic_social_login),
            contentDescription = CURTAINCALL_START_LOGIN_DESCRIPTION,
            modifier = Modifier.size(164.dp, (42.44).dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.weight(16.56f))
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
        Spacer(Modifier.weight(160f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.login_start_without_login),
                style = CurtainCallTheme.typography.body3.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = CurtainCallTheme.colors.onPrimary
                )
            )
            Divider(
                modifier = Modifier
                    .padding(top = Paddings.small)
                    .size(116.dp, 1.dp)
            )
        }
        Spacer(Modifier.weight(91f))
    }
}
