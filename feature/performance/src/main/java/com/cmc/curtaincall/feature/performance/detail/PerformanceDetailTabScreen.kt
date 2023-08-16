package com.cmc.curtaincall.feature.performance.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallBorderTextButton
import com.cmc.curtaincall.common.design.component.content.card.PerformanceCard
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.*

@Composable
internal fun PerformanceDetailTabScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        // TODO PerformanceNotice
        PerformanceNotice(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp)
        )
        PerformanceTimeRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ghost_White)
                .height(7.dp)
        )
        PerformancePlace(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(vertical = 30.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
        PerformanceSimilarContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 103.dp)
        )
    }
}

@Composable
private fun PerformanceSimilarContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_similar_content),
            modifier = Modifier.padding(start = 20.dp),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        ) {
            itemsIndexed(List(10) {}) { index, item ->
                if (index == 0) Spacer(Modifier.size(20.dp))
                Row {
                    PerformanceCard(
                        modifier = Modifier.width(120.dp),
                        title = "데스노트",
                        painter = painterResource(R.drawable.dummy_poster),
                        rate = 4.89f,
                        numberOfTotal = 1012
                    )
                    Spacer(Modifier.size(12.dp))
                }
            }
        }
    }
}

@Composable
private fun PerformanceNotice(
    modifier: Modifier = Modifier
) {
    var isClickMore by remember { mutableStateOf(false) }
    Column(modifier) {
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (isClickMore.not()) {
                        Modifier.aspectRatio(360 / 300f)
                    } else {
                        Modifier
                    }
                ),
            error = painterResource(R.drawable.dummy_performance_poster),
            contentScale = ContentScale.FillWidth
        )
        CurtainCallBorderTextButton(
            onClick = { isClickMore = isClickMore.not() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.performance_detail_more_view),
            fontSize = 16.dp.toSp(),
            containerColor = White,
            contentColor = Me_Pink,
            borderColor = Me_Pink,
            radiusSize = 12.dp
        )
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun PerformancePlace(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_place_information),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(Modifier.padding(top = 14.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_place_name),
                modifier = Modifier.width(72.dp),
                color = Black_Coral,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "LG아트센터 서울",
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 6.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_address),
                modifier = Modifier.width(72.dp),
                color = Black_Coral,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "서울 강서구 마곡중앙로 136",
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 6.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_phone_number),
                modifier = Modifier.width(72.dp),
                color = Black_Coral,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "1661-0017",
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 6.dp)) {
            Text(
                text = stringResource(R.string.performance_detail_website),
                modifier = Modifier.width(72.dp),
                color = Black_Coral,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = "https://www.lgart.com/",
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        NaverMap(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(142.dp)
                .clip(RoundedCornerShape(10.dp)),
            cameraPositionState = CameraPositionState(
                CameraPosition(LatLng(37.56480446250912, 126.82722338487427), 12.0)
            ),
            uiSettings = MapUiSettings(
                isScrollGesturesEnabled = false,
                isTiltGesturesEnabled = false,
                isRotateGesturesEnabled = false,
                isStopGesturesEnabled = false,
                isZoomGesturesEnabled = false,
                isZoomControlEnabled = false,
                isLogoClickEnabled = false
            )
        ) {
            Marker(MarkerState(LatLng(37.56480446250912, 126.82722338487427)))
        }
    }
}

@Composable
private fun PerformanceTimeRow(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_time),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(
            Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .size(6.dp)
                    .background(Nero, CircleShape)
            )
            Text(
                text = "예매 가능 시간 : 관람 2시간 전까지",
                modifier = Modifier.padding(start = 10.dp),
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 4.dp)) {
            Spacer(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(6.dp)
                    .background(Nero, CircleShape)
            )
            Text(
                text = "화, 수, 목, 금 | 19:30\n주말 | 15:00\n공휴일 | 15:00",
                modifier = Modifier.padding(start = 10.dp),
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            )
        }
    }
}