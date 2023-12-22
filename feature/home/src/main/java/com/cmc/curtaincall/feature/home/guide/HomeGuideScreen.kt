package com.cmc.curtaincall.feature.home.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.domain.type.HomeGuideMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGuideScreen(
    homeGuideMenu: HomeGuideMenu?,
    onBack: () -> Unit
) {
    requireNotNull(homeGuideMenu)
    SystemUiStatusBar(Cultured)
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.home_guide_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = Cultured,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        HomeGuideContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            homeGuideMenu = homeGuideMenu
        )
    }
}

@Composable
private fun HomeGuideContent(
    modifier: Modifier = Modifier,
    homeGuideMenu: HomeGuideMenu
) {
    val verticalScrollState = rememberScrollState()
    Column(modifier.verticalScroll(verticalScrollState)) {
        HomeGuideHeader(
            modifier = Modifier
                .background(Cultured)
                .fillMaxWidth()
                .padding(top = 27.dp, start = 19.dp),
            icon = painterResource(getGuideIcon(homeGuideMenu)),
            title = stringResource(getGuideTitle(homeGuideMenu))
        )
        when (homeGuideMenu) {
            HomeGuideMenu.DICTIONARY -> {
                HomeGuideMenuTab(
                    modifier = Modifier
                        .background(Cultured)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    menus = listOf(
                        stringResource(R.string.home_guide_total_menu) to { HomeGuideTotalExpressionContent() },
                        stringResource(R.string.home_guide_ticketing_menu) to { HomeGuideTicketingExpressionContent() },
                        stringResource(R.string.home_guide_performance_menu) to { HomeGuidePerformanceExpressionContent() },
                        stringResource(R.string.home_guide_theater_menu) to { HomeGuideTheaterExpressionContent() },
                        stringResource(R.string.home_guide_audience_menu) to { HomeGuideAudienceExpressionContent() },
                        stringResource(R.string.home_guide_etc_menu) to { HomeGuideEtcExpressionContent() }
                    )
                )
            }

            HomeGuideMenu.TICKETING -> {
                HomeGuideMenuTab(
                    modifier = Modifier
                        .background(Cultured)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    menus = listOf(
                        stringResource(R.string.home_guide_before_ticketing) to { HomeGuideBeforeTicketingContent() },
                        stringResource(R.string.home_guide_after_tickketing) to { HomeGuideAfterTicketingContent() }
                    )
                )
            }

            HomeGuideMenu.DISCOUNT -> {
                HomeGuideDiscountContent(
                    modifier = Modifier
                        .background(Cultured)
                        .padding(top = 10.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun HomeGuideHeader(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(28.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 6.dp),
            color = Chinese_Black,
            fontSize = 20.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
    }
}

private fun getGuideTitle(homeGuideMenu: HomeGuideMenu) = when (homeGuideMenu) {
    HomeGuideMenu.DICTIONARY -> R.string.home_banner_word_dictionary_title
    HomeGuideMenu.TICKETING -> R.string.home_banner_ticketing_guide_title
    HomeGuideMenu.DISCOUNT -> R.string.home_banner_gift_title
}

private fun getGuideIcon(homeGuideMenu: HomeGuideMenu) = when (homeGuideMenu) {
    HomeGuideMenu.DICTIONARY -> R.drawable.ic_dictionary_guide
    HomeGuideMenu.TICKETING -> R.drawable.ic_ticketing_guide
    HomeGuideMenu.DISCOUNT -> R.drawable.ic_gift_guide
}
