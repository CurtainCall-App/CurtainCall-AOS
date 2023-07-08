package com.cmc.curtaincall.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.French_Rose
import com.cmc.curtaincall.common.design.theme.Gunmetal
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnBoardingScreen(
    onNavigateLoginUp: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val pagerItems = listOf(
        stringResource(R.string.onboarding_performance_navigation) to stringResource(R.string.onboarding_performance_navigation_description),
        stringResource(R.string.onboarding_partymember_gathering) to stringResource(R.string.onboarding_partymember_gathering_description),
        stringResource(R.string.onboarding_livetalk) to stringResource(R.string.onboarding_livetalk_description)
    )

    val pagerState = rememberPagerState { pagerItems.size }
    val coroutineScopre = rememberCoroutineScope()
    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(Gunmetal)
        ) { position ->
            val isSkip = position != pagerItems.lastIndex
            OnBoardingPagerItem(
                title = pagerItems[position].first,
                description = pagerItems[position].second,
                isSkipButton = isSkip
            ) {
                if (isSkip) {
                    coroutineScopre.launch {
                        pagerState.scrollToPage(pagerItems.lastIndex)
                    }
                } else {
                    onNavigateLoginUp()
                }
            }
        }
    }
}

@Composable
private fun OnBoardingPagerItem(
    title: String,
    description: String,
    isSkipButton: Boolean,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            modifier = Modifier.padding(top = 68.dp, start = 22.dp),
            color = White,
            fontSize = 26.sp,
            fontFamily = spoqahansanseeo,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 47.dp, start = 22.dp),
            color = White,
            fontSize = 18.sp,
            fontFamily = spoqahansanseeo,
            fontWeight = FontWeight.Medium,
            lineHeight = 28.sp
        )
        Spacer(Modifier.weight(1f))
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 22.dp)
                .padding(bottom = 17.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = French_Rose)
        ) {
            Text(
                text = stringResource(if (isSkipButton) R.string.onboarding_skip else R.string.onboarding_login),
                color = White,
                fontSize = 18.sp,
                fontFamily = spoqahansanseeo,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
