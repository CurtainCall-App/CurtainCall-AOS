package com.cmc.curtaincall.feature.auth.signup.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewNavigator
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import timber.log.Timber

private const val SERVICE_TERMS_URL = "https://ssarivibebe.github.io/curtaincall/%EC%84%9C%EB%B9%84%EC%8A%A4%20%EC%9D%B4%EC%9A%A9%EC%95%BD%EA%B4%8001.html"
private const val PRIVACY_INFORMATION_TERMS_URL = "https://ssarivibebe.github.io/curtaincall/%EA%B0%9C%EC%9D%B8%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EB%B0%A9%EC%B9%A801.html"

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
        if (isBold.not()) {
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
