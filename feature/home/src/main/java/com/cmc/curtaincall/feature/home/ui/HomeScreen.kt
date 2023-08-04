package com.cmc.curtaincall.feature.home.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigatePerformance: () -> Unit,
    onNavigateLiveTalk: () -> Unit,
    onNavigatePartyMember: () -> Unit,
    onNavigateMyPage: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Scaffold(
        topBar = {
            HomeSearchAppbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                onSearchClick = {
                }
            )
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        HomeBanner(
            modifier = Modifier
                .background(Cetacean_Blue)
                .height(284.dp)
        )
    }
}

data class BannerItem(
    val title: String,
    val description: String,
    val color: Color,
    val content: @Composable BoxScope.() -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeBanner(
    modifier: Modifier = Modifier
) {
    val bannerItems = listOf(
        BannerItem(
            stringResource(R.string.home_banner_word_dictionary_title),
            stringResource(R.string.home_banner_word_dictionary_description),
            Navajo_White
        ) {
            Image(
                painter = painterResource(R.drawable.ic_dictionary),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 18.dp, bottom = (14.63).dp)
            )
        },
        BannerItem(
            stringResource(R.string.home_banner_ticketing_guide_title),
            stringResource(R.string.home_banner_ticketing_guide_description),
            Pale_Lavendar
        ) {
            Image(
                painter = painterResource(R.drawable.ic_ticket),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = (18.5).dp, bottom = (28.37).dp)
            )
        },
        BannerItem(
            stringResource(R.string.home_banner_gift_title),
            stringResource(R.string.home_banner_gift_description),
            Water
        ) {
            Image(
                painter = painterResource(R.drawable.ic_gift),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = (15.35).dp)
            )
        }
    )

    val pagerState = rememberPagerState { bannerItems.size }
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "안녕하세요. 고라파덕님:)",
            modifier = Modifier.padding(top = 22.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Box(Modifier.padding(top = 14.dp)) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .height(180.dp)
            ) { position ->
                HomeBannerPagerItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bannerItems[position].color),
                    title = bannerItems[position].title,
                    description = bannerItems[position].description,
                    content = bannerItems[position].content
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = bannerItems.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp),
            activeColor = White,
            inactiveColor = White.copy(alpha = 0.2f),
            indicatorWidth = 7.dp,
            indicatorHeight = 7.dp,
            spacing = 5.dp
        )
    }
}

@Composable
private fun HomeBannerPagerItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(modifier) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = title,
                color = Cetacean_Blue,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = description,
                color = Cetacean_Blue,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(50.dp, 30.dp)
                    .background(Cetacean_Blue, RoundedCornerShape(16.dp))
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.home_banner_showing),
                    color = White,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        content()
    }
}

@Composable
private fun HomeSearchAppbar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = modifier.background(Cetacean_Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.home_appbar_title),
            modifier = Modifier.padding(start = 20.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = avenirnext
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = { onSearchClick() },
            modifier = Modifier
                .padding(end = 20.dp)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = White
            )
        }
    }
}
