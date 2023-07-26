package com.cmc.curtaincall.feature.performance.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
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
        PerformanceStoryRow(Modifier.padding(horizontal = 20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
        PerformanceTimeRow(Modifier.padding(start = 20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
        PerformanceCastingRow(Modifier.padding(start = 20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
        PerformanceImageRow(Modifier.padding(start = 20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 30.dp)
                .background(Ghost_White)
                .height(7.dp)
        )
        PerformanceAnnouncement(Modifier.padding(horizontal = 20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
        PerformancePlace(Modifier.padding(horizontal = 20.dp))
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
        Row(Modifier.padding(top = 10.dp)) {
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
                .padding(top = 20.dp, bottom = 30.dp)
                .fillMaxWidth()
                .height(142.dp)
                .clip(RoundedCornerShape(10.dp)),
            cameraPositionState = CameraPositionState(
                CameraPosition(LatLng(37.56480446250912, 126.82722338487427), 12.0)
            ),
            uiSettings = MapUiSettings(
                isScrollGesturesEnabled = false,
                isZoomGesturesEnabled = false,
                isTiltGesturesEnabled = false,
                isRotateGesturesEnabled = false,
                isZoomControlEnabled = false,
                isLogoClickEnabled = false
            )
        ) {
            Marker(MarkerState(LatLng(37.56480446250912, 126.82722338487427)))
        }
    }
}

@Composable
private fun PerformanceAnnouncement(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_announcement),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = "※ 본 공연은 LG아트센터 서울 연동 공연으로, 예매대기 서비스 및 취소 후 재예매 서비스가 제공되지 않습니다.\n\n※ LG아트센터가 역삼에서 마곡으로 이전하였습니다.\n" +
                "방문에 혼선이 없으시기 바랍니다.\n\n※ LG아트센터 서울, 강서구 마곡중앙로 136\n주차장이 협소하오니 대중교통을 이용하여 주시기 바랍니다.\n" +
                "지하철 9호선 및 공항철도 '마곡나루역' 3-4번 출구를 통하시면 공연장 로비와 바로 연결됩니다.",
            modifier = Modifier.padding(top = 10.dp),
            color = Nero,
            fontWeight = FontWeight.Normal,
            fontFamily = spoqahansanseeo,
            lineHeight = 20.dp.toSp()
        )
    }
}

@Composable
private fun PerformanceImageRow(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_related_image_or_video),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        LazyRow(Modifier.padding(top = 16.dp)) {
            items(10) {
                Image(
                    painter = painterResource(R.drawable.img_example_detail),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}

@Composable
private fun PerformanceCastingRow(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_casting),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        LazyRow(Modifier.padding(top = 16.dp)) {
            items(10) {
                Column(
                    modifier = Modifier.padding(end = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "김종구",
                        modifier = Modifier.padding(top = 6.dp),
                        color = Nero,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Text(
                        text = "이재현",
                        color = Roman_Silver,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
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
                text = "화, 수, 목, 금 | 19:30\n주말 | 15:00\n* 단, 6/6화 | 15:00",
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

@Composable
private fun PerformanceStoryRow(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_story_introduction),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = "시끄러운 도시의 소음, 서울의 밤거리, 클랙슨 소리가 사방에 퍼진 적들처럼 쏟아지면, 개츠비의 간판이 켜진다.",
            modifier = Modifier.padding(top = 16.dp),
            color = Nero,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp()
        )
    }
}
