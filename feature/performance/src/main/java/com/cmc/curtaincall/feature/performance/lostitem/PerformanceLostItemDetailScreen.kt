package com.cmc.curtaincall.feature.performance.lostitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithReportAction
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemDetailScreen(
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cultured)

    Scaffold(
        topBar = {
            TopAppBarWithReportAction(
                title = stringResource(R.string.performance_find_lost_item),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = Cultured,
                onBack = onBack
            )
        }
    ) { paddingValues ->
        PerformanceLostItemDetailContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
private fun PerformanceLostItemDetailContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Cultured)
                .padding(top = 36.dp, bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.img_poster),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        LostItemDetail(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        )
        Spacer(Modifier.weight(1f))
        LostItemPlaceInfo(Modifier.fillMaxWidth())
    }
}

@Composable
private fun LostItemPlaceInfo(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Cultured)
        )
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.performance_find_lost_item_detail_management_number),
                modifier = Modifier.width(64.dp),
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "000-0000-0000",
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.performance_find_lost_item_detail_storage),
                modifier = Modifier.width(64.dp),
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "LG아트센터 서울",
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 12.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.performance_find_lost_item_detail_phone_number),
                modifier = Modifier.width(64.dp),
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "02-1234-5678",
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@Composable
private fun LostItemDetail(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .background(Gunmetal, RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "록시땅 핸드크림",
                color = White,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        LostItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_classification),
            content = "기타",
            icon = painterResource(R.drawable.ic_lost_item)
        )
        LostItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_place_of_acquisition),
            content = "LG아트센터 서울",
            icon = painterResource(R.drawable.ic_location_searching)
        )
        LostItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_place),
            content = "LG아트센터 A동 13B 2열 8석",
            icon = painterResource(R.drawable.ic_my_location)
        )
        LostItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_acquistion_date),
            content = "습득일자",
            icon = painterResource(R.drawable.ic_event_available)
        )
        LostItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_significant),
            content = "없음",
            icon = painterResource(R.drawable.ic_stars)
        )
    }
}

@Composable
private fun LostItemInfo(
    modifier: Modifier = Modifier,
    type: String,
    content: String,
    icon: Painter
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Black_Coral
        )
        Text(
            text = type,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(79.dp),
            color = Black_Coral,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = content,
            color = Nero,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
