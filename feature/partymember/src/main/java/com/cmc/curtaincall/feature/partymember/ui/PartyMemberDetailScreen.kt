package com.cmc.curtaincall.feature.partymember.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithAction
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.partymember.PartyType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberDetailScreen(
    partyType: PartyType,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            TopAppBarWithAction(
                title = stringResource(
                    when (partyType) {
                        PartyType.PERFORMANCE -> R.string.partymember_performance_title
                        PartyType.MEAL -> R.string.partymember_restaurant_title
                        PartyType.ETC -> R.string.partymember_etc_title
                    }
                ),
                actionRes = R.drawable.ic_report,
                onBack = onBack,
                onClickAction = { /* TODO */ }
            )
        }
    ) { paddingValues ->
        PartyMemberDetailContent(
            partyType = partyType,
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(paddingValues),
            profile = "",
            name = "고라파덕",
            createdAt = "2023.6.7. 12:37",
            title = "리어왕 lg센터 끝나고 밥 드실 분",
            description = "등촌칼국수 드실 분 있나여?! 다들 참여 기기"
        )
    }
}

@Composable
private fun PartyMemberDetailContent(
    partyType: PartyType,
    modifier: Modifier = Modifier,
    profile: String,
    name: String,
    createdAt: String,
    title: String,
    description: String
) {
    Column(modifier = modifier) {
        PartyMemberDetailUser(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 29.dp)
                .padding(horizontal = 22.dp),
            profile = "",
            name = name,
            createdAt = createdAt,
            title = title,
            description = description
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .height(12.dp)
                .background(Black.copy(alpha = 0.36f))
        )

        PartyMemberDetailPerformance(
            partyType = partyType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 22.dp),
        )
    }
}

@Composable
private fun PartyMemberDetailUser(
    modifier: Modifier = Modifier,
    profile: String,
    name: String,
    createdAt: String,
    title: String,
    description: String
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    color = Eerie_Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )

                Text(
                    text = createdAt,
                    modifier = Modifier.padding(top = 1.dp),
                    color = Silver_Sand,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        Text(
            text = title,
            modifier = Modifier.padding(top = 28.dp),
            color = Eerie_Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )

        Text(
            text = description,
            modifier = Modifier.padding(top = 15.dp),
            color = Black_Coral,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun PartyMemberDetailPerformance(
    partyType: PartyType,
    modifier: Modifier = Modifier
) {
    var joinState by remember { mutableStateOf(false) }

    Column(modifier) {
        Text(
            text = stringResource(R.string.partymember_detail),
            color = Gunmetal,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )

        if (partyType == PartyType.ETC) {
            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 22.dp),
                iconRes = R.drawable.ic_calendar,
                category = stringResource(R.string.partymember_detail_date),
                info = "2023.6.24"
            )

            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp)
            )

            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 11.dp),
                iconRes = R.drawable.ic_party_state,
                category = stringResource(R.string.partymember_detail_join_state),
                info = "2/5"
            )
        } else {
            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 22.dp),
                iconRes = R.drawable.ic_dns,
                category = stringResource(R.string.partymember_detail_performance_title),
                info = "리어왕"
            )

            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp)
            )

            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 11.dp),
                iconRes = R.drawable.ic_party_state,
                category = stringResource(R.string.partymember_detail_join_state),
                info = "2/5"
            )

            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp)
            )

            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 11.dp),
                iconRes = R.drawable.ic_calendar,
                category = stringResource(R.string.partymember_detail_performance_date),
                info = "2023.6.24"
            )

            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp)
            )

            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 11.dp),
                iconRes = R.drawable.ic_clock,
                category = stringResource(R.string.partymember_detail_performance_time),
                info = "19:3019:3019:3019:3019:3019:3019:3019:3019:3019:3019:30"
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp)
            )

            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 11.dp),
                iconRes = R.drawable.ic_location,
                category = stringResource(R.string.partymember_detail_performance_location),
                info = "LG아트센터 서울"
            )
        }

        Spacer(Modifier.weight(1f))
        Button(
            onClick = { joinState = joinState.not() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .height(52.dp),
            enabled = joinState.not(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (joinState) Bright_Gray else Me_Pink,
                disabledContainerColor = Bright_Gray
            )
        ) {
            Text(
                text = stringResource(
                    if (joinState) {
                        R.string.partymember_detail_joined
                    } else {
                        R.string.partymember_detail_join
                    }
                ),
                color = if (joinState) Silver_Sand else White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun PartyMemberDetailInfo(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    category: String,
    info: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(Bright_Gray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = category,
            modifier = Modifier
                .padding(start = 9.dp)
                .weight(1f),
            color = Black_Coral,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = info,
            modifier = Modifier.weight(3f),
            color = Eerie_Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun DottedLine(
    modifier: Modifier = Modifier
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 10f), 15f)
    Canvas(modifier) {
        drawLine(
            color = Bright_Gray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect,
            strokeWidth = 2.dp.value
        )
    }
}
