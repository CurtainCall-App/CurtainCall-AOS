package com.cmc.curtaincall.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.lib.pager.DynamicHorizontalPagerIndicator
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Me_Pink
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
    val painterItems = listOf(
        painterResource(R.drawable.ic_onboarding_performance),
        painterResource(R.drawable.ic_onboarding_partymember),
        painterResource(R.drawable.ic_onboarding_livetalk)
    )

    val pagerState = rememberPagerState { pagerItems.size }
    val coroutineScopre = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cetacean_Blue)
    ) {
        DynamicHorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = pagerItems.size,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 50.dp, end = 20.dp),
            activeColor = White,
            activeIndicatorWidth = 16.dp,
            activeIndicatorHeight = 7.dp,
            inactiveColor = White.copy(0.2f),
            inactiveIndicatorWidth = 7.dp,
            spacing = 5.dp
        )
        HorizontalPager(
            state = pagerState
        ) { position ->
            val isSkip = position != pagerItems.lastIndex
            OnBoardingPagerItem(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 36.dp, bottom = 67.dp),
                title = pagerItems[position].first,
                description = pagerItems[position].second,
                painter = painterItems[position],
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
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    painter: Painter,
    isSkipButton: Boolean,
    onClick: () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            color = White,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 30.dp),
            color = White,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 185.dp)
                    .width(145.dp)
            )
        }
        CurtainCallRoundedTextButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            title = stringResource(if (isSkipButton) R.string.onboarding_skip else R.string.onboarding_login),
            fontSize = 16.dp.toSp(),
            containerColor = Me_Pink,
            contentColor = Cetacean_Blue
        )
    }
}
