package com.cmc.curtaincall.feature.auth.signup.terms

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
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Black
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.domain.model.Urls.DEFAULT_URL
import com.cmc.curtaincall.domain.model.Urls.PRIVACY_INFORMATION_TERMS_URL
import com.cmc.curtaincall.domain.model.Urls.SERVICE_TERMS_URL
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
internal fun SignUpTermsScreen(
    onNavigateSignUpInput: () -> Unit = {},
    onBack: () -> Unit
) {
    var webViewUrl by remember { mutableStateOf(DEFAULT_URL) }
    SystemUiStatusBar(White)
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.signup_terms),
                containerColor = White,
                contentColor = Nero,
                onClick = {
                    if (webViewUrl.isNotEmpty()) {
                        webViewUrl = DEFAULT_URL
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
            onNavigateSignUpInput = onNavigateSignUpInput,
            onNavigateSignUpTermsWebView = {
                webViewUrl = it
            }
        )
    }

    BackHandler {
        if (webViewUrl.isNotEmpty()) {
            webViewUrl = DEFAULT_URL
        } else {
            onBack()
        }
    }
}

@Composable
private fun SignUpTermsContent(
    modifier: Modifier = Modifier,
    webViewUrl: String = DEFAULT_URL,
    onNavigateSignUpInput: () -> Unit = {},
    onNavigateSignUpTermsWebView: (String) -> Unit = {}
) {
    val webViewState = rememberWebViewState(url = webViewUrl)
    val signUpTermsState by remember { mutableStateOf(SignUpTermsState()) }
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
            SignUpTermsCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                description = stringResource(R.string.signup_terms_all_agree),
                isChecked = signUpTermsState.isAllChecked(),
                isBold = true,
                onCheckedChange = {
                    signUpTermsState.clickedAllTerms()
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(1.dp)
                    .background(Cultured)
            )
            SignUpTermsCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_service),
                isChecked = signUpTermsState.serviceTerms.value,
                hasMore = true,
                onCheckedChange = {
                    signUpTermsState.clickedServiceTerms()
                },
                onNavigateTermsDetail = {
                    onNavigateSignUpTermsWebView(SERVICE_TERMS_URL)
                }
            )
            SignUpTermsCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_privacy_information_agree),
                isChecked = signUpTermsState.privacyInfoTerms.value,
                hasMore = true,
                onCheckedChange = {
                    signUpTermsState.clickedPrivacyInfoTerms()
                },
                onNavigateTermsDetail = {
                    onNavigateSignUpTermsWebView(PRIVACY_INFORMATION_TERMS_URL)
                }
            )
            SignUpTermsCheckBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                description = stringResource(R.string.signup_terms_age_agree),
                isChecked = signUpTermsState.ageTerms.value,
                onCheckedChange = {
                    signUpTermsState.clickedAgeTerms()
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
                enabled = signUpTermsState.isAllChecked(),
                containerColor = if (signUpTermsState.isAllChecked()) Me_Pink else Bright_Gray,
                contentColor = if (signUpTermsState.isAllChecked()) White else Silver_Sand
            )
        }
        if (webViewUrl != DEFAULT_URL) {
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
private fun SignUpTermsCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    description: String,
    isBold: Boolean = false,
    hasMore: Boolean = false,
    onCheckedChange: () -> Unit = {},
    onNavigateTermsDetail: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCheckedChange,
            modifier = Modifier
                .background(if (isChecked) Me_Pink else Bright_Gray, CircleShape)
                .size(20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = description,
                modifier = Modifier.size(14.dp),
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
                onClick = onNavigateTermsDetail,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_right_pink),
                    contentDescription = description,
                    modifier = Modifier.size(12.dp),
                    tint = Arsenic
                )
            }
        }
    }
}
