package com.cmc.curtaincall.feature.auth.signup.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Arsenic
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.domain.Urls.PRIVACY_INFORMATION_TERMS_URL
import com.cmc.curtaincall.domain.Urls.SERVICE_TERMS_URL
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState

@Composable
internal fun SignUpTermsScreen(
    onNavigateSignUpInput: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    var webViewUrl by remember { mutableStateOf("") }
    val webViewState = rememberWebViewState(url = webViewUrl)
    BackHandler {
        if (webViewUrl.isNotEmpty()) {
            webViewUrl = ""
        } else {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.signup_terms),
                containerColor = White,
                contentColor = Nero,
                onClick = {
                    if (webViewUrl.isNotEmpty()) {
                        webViewUrl = ""
                    } else {
                        onBack()
                    }
                }
            )
        }
    ) { paddingValues ->
        SignUpTermsContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            webViewUrl = webViewUrl,
            webViewState = webViewState,
            onNavigateSignUpInput = onNavigateSignUpInput,
            onNavigateSignUpTermsWebView = {
                webViewUrl = it
            }
        )
    }
}

@Composable
private fun SignUpTermsContent(
    modifier: Modifier = Modifier,
    webViewUrl: String,
    webViewState: WebViewState,
    onNavigateSignUpInput: () -> Unit = {},
    onNavigateSignUpTermsWebView: (String) -> Unit = {}
) {
    var allTermsState by remember { mutableStateOf(false) }
    var serviceTermsState by remember { mutableStateOf(false) }
    var privacyInfoTermsState by remember { mutableStateOf(false) }
    var ageState by remember { mutableStateOf(false) }

    Box(modifier) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.signup_terms_description),
                modifier = Modifier.padding(top = 40.dp),
                color = Black,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo,
                lineHeight = 32.dp.toSp()
            )
            Text(
                text = stringResource(R.string.signup_terms_sub_description),
                modifier = Modifier.padding(top = 14.dp),
                color = Roman_Silver,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            )
            SignUpTerms(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                description = stringResource(R.string.signup_terms_all_agree),
                checked = allTermsState,
                isBold = true,
                onCheckedChange = {
                    allTermsState = allTermsState.not()
                    serviceTermsState = allTermsState
                    privacyInfoTermsState = allTermsState
                    ageState = allTermsState
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(1.dp)
                    .background(Cultured)
            )
            SignUpTerms(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_service),
                checked = serviceTermsState,
                hasMore = true,
                onCheckedChange = {
                    serviceTermsState = serviceTermsState.not()
                    allTermsState = listOf(serviceTermsState, privacyInfoTermsState, ageState).all { it }
                },
                onClickDown = {
                    onNavigateSignUpTermsWebView(SERVICE_TERMS_URL)
                }
            )
            SignUpTerms(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_privacy_information_agree),
                checked = privacyInfoTermsState,
                hasMore = true,
                onCheckedChange = {
                    privacyInfoTermsState = privacyInfoTermsState.not()
                    allTermsState = listOf(serviceTermsState, privacyInfoTermsState, ageState).all { it }
                },
                onClickDown = {
                    onNavigateSignUpTermsWebView(PRIVACY_INFORMATION_TERMS_URL)
                }
            )
            SignUpTerms(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_age_agree),
                checked = ageState,
                onCheckedChange = {
                    ageState = ageState.not()
                    allTermsState = listOf(serviceTermsState, privacyInfoTermsState, ageState).all { it }
                }
            )
            Spacer(Modifier.weight(1f))
            CurtainCallRoundedTextButton(
                onClick = onNavigateSignUpInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 19.dp)
                    .height(52.dp),
                title = stringResource(R.string.signup_next),
                fontSize = 16.dp.toSp(),
                enabled = allTermsState,
                containerColor = if (serviceTermsState) Me_Pink else Bright_Gray,
                contentColor = if (allTermsState) White else Silver_Sand
            )
        }
        if (webViewUrl.isNotEmpty()) {
            WebView(
                state = webViewState,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun SignUpTerms(
    modifier: Modifier = Modifier,
    description: String,
    checked: Boolean = false,
    isBold: Boolean = false,
    hasMore: Boolean = false,
    onCheckedChange: () -> Unit = {},
    onClickDown: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCheckedChange,
            modifier = Modifier
                .background(if (checked) Me_Pink else Bright_Gray, CircleShape)
                .size(20.dp, 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.size(14.dp, 14.dp),
                tint = White
            )
        }
        Text(
            text = description,
            modifier = Modifier.padding(start = 10.dp),
            color = if (isBold) Nero else Arsenic,
            fontSize = 16.dp.toSp(),
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Spacer(Modifier.weight(1f))
        if (hasMore) {
            IconButton(
                onClick = onClickDown,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right_pink),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    tint = Arsenic
                )
            }
        }
    }
}
