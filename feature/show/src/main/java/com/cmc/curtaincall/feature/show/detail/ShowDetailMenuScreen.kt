package com.cmc.curtaincall.feature.show.detail

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallBorderTextButton
import com.cmc.curtaincall.common.designsystem.component.card.PerformanceCard
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.domain.enums.getShowTimes
import com.cmc.curtaincall.domain.model.show.FacilityDetailModel
import com.cmc.curtaincall.domain.model.show.ShowTimeModel
import com.cmc.curtaincall.domain.model.show.SimilarShowInfoModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.*

@Composable
internal fun ShowDetailMenuScreen(
    modifier: Modifier = Modifier,
    introductionImage: String? = null,
    showTimes: List<ShowTimeModel> = listOf(),
    similarShows: List<SimilarShowInfoModel> = listOf(),
    facilityDetailModel: FacilityDetailModel = FacilityDetailModel()
) {
    Column(modifier) {
        if (introductionImage != null) {
            ShowDetailNotice(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                introductionImage = introductionImage
            )
        }
        ShowDetailTime(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp),
            showTimes = showTimes
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ghost_White)
                .height(7.dp)
        )
        ShowDetailFacility(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(vertical = 30.dp),
            facilityDetailModel = facilityDetailModel
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(Bright_Gray)
                .height(1.dp)
        )
    }
}

@Composable
private fun ShowDetailSimilarRow(
    modifier: Modifier = Modifier,
    similarShows: List<SimilarShowInfoModel> = listOf(),
    onNavigateDetail: (String) -> Unit = {}
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
            itemsIndexed(similarShows) { index, similarShow ->
                if (index == 0) Spacer(Modifier.size(20.dp))
                Row {
                    PerformanceCard(
                        modifier = Modifier.width(120.dp),
                        title = similarShow.name,
                        painter = painterResource(R.drawable.ic_error_poster),
                        imageUrl = similarShow.poster,
                        rate = if (similarShow.reviewCount == 0) 0.0f else similarShow.reviewGradeSum / similarShow.reviewCount.toFloat(),
                        numberOfTotal = similarShow.reviewCount,
                        onClick = { onNavigateDetail(similarShow.id) }
                    )
                    Spacer(Modifier.size(12.dp))
                }
            }
        }
    }
}

@Composable
private fun ShowDetailNotice(
    modifier: Modifier = Modifier,
    introductionImage: String
) {
    var isClickMore by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    Column(modifier) {
        AsyncImage(
            model = introductionImage,
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
            error = painterResource(R.drawable.ic_error_introduction),
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
        if (isLoading.not() && isClickMore.not()) {
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
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun ShowDetailFacility(
    modifier: Modifier = Modifier,
    facilityDetailModel: FacilityDetailModel
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
                text = facilityDetailModel.name,
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
                text = facilityDetailModel.address,
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
                text = facilityDetailModel.phone,
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        if (facilityDetailModel.homepage.isNotEmpty()) {
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
                    text = facilityDetailModel.homepage,
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Normal,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        NaverMap(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(142.dp)
                .clip(RoundedCornerShape(10.dp)),
            cameraPositionState = CameraPositionState(
                CameraPosition(LatLng(facilityDetailModel.latitude, facilityDetailModel.longitude), 12.0)
            ),
            uiSettings = MapUiSettings(
                isScrollGesturesEnabled = false,
                isTiltGesturesEnabled = false,
                isRotateGesturesEnabled = false,
                isStopGesturesEnabled = false,
                scrollGesturesFriction = 1f,
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
private fun ShowDetailTime(
    modifier: Modifier = Modifier,
    showTimes: List<ShowTimeModel>
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.performance_detail_time),
            color = Chinese_Black,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(Modifier.padding(top = 4.dp)) {
            Spacer(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(6.dp)
                    .background(Nero, CircleShape)
            )
            Text(
                text = showTimes.getShowTimes(),
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
