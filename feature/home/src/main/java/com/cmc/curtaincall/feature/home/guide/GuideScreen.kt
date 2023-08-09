package com.cmc.curtaincall.feature.home.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class GuideType {
    DICTIONARY, TICKETING, DISCOUNT
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GuideScreen(
    guideType: GuideType,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cultured)

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
        GuideContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cultured),
            guideType = guideType
        )
    }
}

@Composable
private fun GuideContent(
    modifier: Modifier = Modifier,
    guideType: GuideType
) {
    Column(modifier) {
        GuideHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 27.dp, start = 19.dp),
            icon = painterResource(R.drawable.ic_dictionary_guide),
            title = stringResource(R.string.home_banner_word_dictionary_title)
        )
        GuideMenuTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            menus = listOf(
                stringResource(R.string.home_guide_total_menu) to { GuideTotalExpressionContent() },
                stringResource(R.string.home_guide_ticketing_menu) to { GuideTicketingExpressionContent() },
                stringResource(R.string.home_guide_performance_menu) to { GuidePerformanceExpressionContent() },
                stringResource(R.string.home_guide_theater_menu) to { GuideTheaterExpressionContent() },
                stringResource(R.string.home_guide_audience_menu) to { GuideAudienceExpressionContent() },
                stringResource(R.string.home_guide_etc_menu) to { GuideEtcExpressionContent() }
            )
        )
    }
}

@Composable
private fun GuideHeader(
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
