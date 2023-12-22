package com.cmc.curtaincall.feature.show.lostproperty.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallSnackbar
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallSnackbarHost
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithReportAction
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Granite_Gray
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import kotlinx.coroutines.launch

@Composable
internal fun ShowLostPropertyDetailScreen(
    showLostPropertyDetailViewModel: ShowLostPropertyDetailViewModel = hiltViewModel(),
    lostPropertyId: Int?,
    fromCreate: Boolean?,
    onNavigateReport: (Int, String) -> Unit = { _, _ -> },
    onBack: () -> Unit = {}
) {
    requireNotNull(lostPropertyId)
    requireNotNull(fromCreate)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        showLostPropertyDetailViewModel.requestLostDetailProperty(lostPropertyId)
        if (fromCreate) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    context.getString(R.string.snackbar_upload_lostitem_complete)
                )
            }
        }
    }

    SystemUiStatusBar(Cultured)
    Scaffold(
        snackbarHost = {
            CurtainCallSnackbarHost(snackbarHostState = snackbarHostState) { snackbarData ->
                CurtainCallSnackbar(
                    modifier = Modifier.fillMaxWidth(),
                    snackbarData = snackbarData
                )
            }
        },
        topBar = {
            TopAppBarWithReportAction(
                title = stringResource(R.string.performance_find_lost_item),
                containerColor = Cultured,
                contentColor = Nero,
                onBack = onBack,
                onAction = { onNavigateReport(lostPropertyId, "LOST_ITEM") }
            )
        }
    ) { paddingValues ->
        ShowLostPropertyDetailContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
private fun ShowLostPropertyDetailContent(
    showLostPropertyDetailViewModel: ShowLostPropertyDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val showLostPropertyDetailUiState by showLostPropertyDetailViewModel.lostPropertyDetailModel.collectAsStateWithLifecycle()
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Cultured),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = showLostPropertyDetailUiState.imageUrl,
                error = painterResource(R.drawable.img_poster),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 40.dp)
                    .size(200.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        ShowLostPropertyDetailBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp),
            title = showLostPropertyDetailUiState.title,
            type = showLostPropertyDetailUiState.type,
            facilityName = showLostPropertyDetailUiState.facilityName,
            foundPlaceDetail = showLostPropertyDetailUiState.foundPlaceDetail,
            foundDate = showLostPropertyDetailUiState.foundDate.toChangeFullDate(),
            foundTime = showLostPropertyDetailUiState.foundTime,
            particulars = showLostPropertyDetailUiState.particulars
        )
        Spacer(Modifier.weight(1f))
        ShowLostPropertyDetailFooter(
            modifier = Modifier.fillMaxWidth(),
            facilityName = showLostPropertyDetailUiState.facilityName,
            facilityPhone = showLostPropertyDetailUiState.facilityPhone
        )
    }
}

@Composable
private fun ShowLostPropertyDetailFooter(
    modifier: Modifier = Modifier,
    facilityName: String,
    facilityPhone: String
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
                .padding(top = 40.dp)
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
                text = facilityName,
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 47.dp)
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
                text = facilityPhone,
                color = Granite_Gray,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@Composable
private fun ShowLostPropertyDetailBody(
    modifier: Modifier = Modifier,
    title: String,
    type: String,
    facilityName: String,
    foundPlaceDetail: String,
    foundDate: String,
    foundTime: String?,
    particulars: String
) {
    Column(modifier) {
        LostItemDetailInfo(
            modifier = Modifier.fillMaxWidth(),
            type = stringResource(R.string.performance_find_lost_item_detail_subtitle),
            content = title,
            icon = painterResource(R.drawable.ic_subtitles)
        )
        LostItemDetailInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_classification),
            content = when (type) {
                LostPropertyType.BAG.code -> LostPropertyType.BAG.label
                LostPropertyType.WALLET.code -> LostPropertyType.WALLET.label
                LostPropertyType.MONEY.code -> LostPropertyType.MONEY.label
                LostPropertyType.CARD.code -> LostPropertyType.CARD.label
                LostPropertyType.JEWELRY.code -> LostPropertyType.JEWELRY.label
                LostPropertyType.ELECTRONICS.code -> LostPropertyType.ELECTRONICS.label
                LostPropertyType.BOOKS.code -> LostPropertyType.BOOKS.label
                LostPropertyType.CLOTHES.code -> LostPropertyType.CLOTHES.label
                else -> LostPropertyType.ETC.label
            },
            icon = painterResource(R.drawable.ic_lost_item)
        )
        LostItemDetailInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_place_of_acquisition),
            content = facilityName,
            icon = painterResource(R.drawable.ic_location_searching)
        )
        if (foundPlaceDetail.isNotEmpty()) {
            LostItemDetailInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                type = stringResource(R.string.performance_find_lost_item_detail_place),
                content = foundPlaceDetail,
                icon = painterResource(R.drawable.ic_my_location)
            )
        }
        LostItemDetailInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_acquistion_date),
            content = foundDate,
            icon = painterResource(R.drawable.ic_event_available)
        )
        foundTime?.let { foundTime ->
            LostItemDetailInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                type = stringResource(R.string.performance_find_lost_item_create_acquistion_time),
                content = foundTime,
                icon = painterResource(R.drawable.ic_alarm_on)
            )
        }
        LostItemDetailInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            type = stringResource(R.string.performance_find_lost_item_detail_significant),
            content = particulars,
            icon = painterResource(R.drawable.ic_stars),
            isSingleLine = false
        )
    }
}

@Composable
private fun LostItemDetailInfo(
    modifier: Modifier = Modifier,
    type: String,
    content: String,
    icon: Painter,
    isSingleLine: Boolean = true
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
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 22.dp.toSp()
        )
    }
}
