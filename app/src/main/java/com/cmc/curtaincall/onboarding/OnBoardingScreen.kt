package com.cmc.curtaincall.onboarding

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
import androidx.compose.ui.layout.ContentScale
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

private val OnBoardingButtonHeight = 52.dp
private val OnBoardingButtonHorizontalPadding = 20.dp
private val OnBoardingButtonBottomPadding = 19.dp

private data class OnBoardingBanner(
    val title: String,
    val description: String,
    val image: Painter
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnBoardingScreen(
    onNavigateLoginUp: () -> Unit
) {
    val banners = listOf(
        OnBoardingBanner(
            title = stringResource(R.string.performance_search),
            description = stringResource(R.string.onboarding_performance_search_banner_description),
            image = painterResource(R.drawable.ic_onboarding_performance_search)
        ),
        OnBoardingBanner(
            title = stringResource(R.string.partymember_recruit),
            description = stringResource(R.string.onboarding_partymember_recruit_banner_description),
            image = painterResource(R.drawable.ic_onboarding_partymember_recruit)
        ),
        OnBoardingBanner(
            title = stringResource(R.string.livetalk),
            description = stringResource(R.string.onboarding_livetalk_banner_description),
            image = painterResource(R.drawable.ic_onboarding_livetalk)
        )
    )

    val pagerState = rememberPagerState { banners.size }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cetacean_Blue)
    ) {
        DynamicHorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = banners.size,
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
        HorizontalPager(state = pagerState) { position ->
            Column(modifier = Modifier.fillMaxSize()) {
                OnBoardingPagerItem(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    banner = banners[position]
                )
                CurtainCallRoundedTextButton(
                    onClick = {
                        coroutineScope.launch {
                            if (position == banners.lastIndex) {
                                onNavigateLoginUp()
                            } else {
                                pagerState.scrollToPage(banners.lastIndex)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = OnBoardingButtonHorizontalPadding)
                        .padding(bottom = OnBoardingButtonBottomPadding)
                        .height(OnBoardingButtonHeight),
                    title = stringResource(if (position == banners.lastIndex) R.string.onboarding_login_button else R.string.onboarding_skip_button),
                    fontSize = 16.dp.toSp(),
                    containerColor = Me_Pink,
                    contentColor = Cetacean_Blue
                )
            }
        }
    }
}

@Composable
private fun OnBoardingPagerItem(
    modifier: Modifier = Modifier,
    banner: OnBoardingBanner
) {
    Column(modifier) {
        Text(
            text = banner.title,
            modifier = Modifier.padding(start = 20.dp),
            color = White,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = banner.description,
            modifier = Modifier.padding(top = 30.dp, start = 20.dp),
            color = White,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 23.dp.toSp()
        )
        Column(Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(42f))
            Image(
                painter = banner.image,
                contentDescription = banner.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.weight(128f))
        }
    }
}
