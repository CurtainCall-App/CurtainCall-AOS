package com.cmc.curtaincall.feature.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Navajo_White
import com.cmc.curtaincall.common.design.theme.Pale_Lavendar
import com.cmc.curtaincall.common.design.theme.Water
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.home.guide.GuideType
import com.google.accompanist.pager.HorizontalPagerIndicator

data class BannerItem(
    val title: String,
    val description: String,
    val color: Color,
    val content: @Composable BoxScope.() -> Unit,
    val onClick: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeBanner(
    modifier: Modifier = Modifier,
    onNavigateGuide: (GuideType) -> Unit
) {
    val bannerItems = listOf(
        BannerItem(
            title = stringResource(R.string.home_banner_word_dictionary_title),
            description = stringResource(R.string.home_banner_word_dictionary_description),
            color = Navajo_White,
            content = {
                Image(
                    painter = painterResource(R.drawable.ic_dictionary),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 17.dp, bottom = (15.27).dp)
                )
            },
            onClick = { onNavigateGuide(GuideType.DICTIONARY) }
        ),
        BannerItem(
            title = stringResource(R.string.home_banner_ticketing_guide_title),
            description = stringResource(R.string.home_banner_ticketing_guide_description),
            color = Pale_Lavendar,
            content = {
                Image(
                    painter = painterResource(R.drawable.ic_ticket),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = (18.37).dp)
                )
            },
            onClick = { onNavigateGuide(GuideType.TICKETING) }
        ),
        BannerItem(
            title = stringResource(R.string.home_banner_gift_title),
            description = stringResource(R.string.home_banner_gift_description),
            color = Water,
            content = {
                Image(
                    painter = painterResource(R.drawable.ic_gift),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = (13.5).dp, bottom = (9.79).dp)
                )
            },
            onClick = { onNavigateGuide(GuideType.DISCOUNT) }
        )
    )

    val pagerState = rememberPagerState { bannerItems.size }
    Column(modifier) {
        Text(
            text = "안녕하세요. 고라파덕님:)",
            modifier = Modifier.padding(top = 22.dp, start = 20.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
                .height(170.dp)
        ) { position ->
            HomeBannerPagerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(bannerItems[position].color, RoundedCornerShape(12.dp)),
                title = bannerItems[position].title,
                description = bannerItems[position].description,
                content = bannerItems[position].content,
                onClick = bannerItems[position].onClick
            )
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
    content: @Composable BoxScope.() -> Unit = {},
    onClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier.clickable { onClick() }) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(top = 18.dp, bottom = 16.dp)
            ) {
                Text(
                    text = title,
                    color = Cetacean_Blue,
                    fontSize = 20.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = description,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Cetacean_Blue,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 17.dp.toSp()
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Cetacean_Blue, RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.home_banner_showing),
                        color = White,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
            content()
        }
    }
}
