package com.cmc.curtaincall.feature.partymember.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PartyMemberScreen(onNavigateDetail: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Black.copy(alpha = 0.36f))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black.copy(alpha = 0.36f))
            .padding(bottom = 30.dp)
    ) {
        Text(
            text = stringResource(R.string.partymember_recruitment),
            modifier = Modifier.padding(top = 44.dp, start = 22.dp),
            color = Gunmetal,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )

        PartyMemberRecruitmentCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 22.dp, vertical = 10.dp),
            title = stringResource(R.string.partymember_performance_title),
            description = stringResource(R.string.partymember_performance_description),
            fontColor = White,
            backgroundColor = Me_Pink
        )

        PartyMemberRecruitmentCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 22.dp, vertical = 10.dp),
            title = stringResource(R.string.partymember_restaurant_title),
            description = stringResource(R.string.partymember_restaurant_description),
            fontColor = Cetacean_Blue,
            backgroundColor = Corn
        )

        PartyMemberRecruitmentCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 22.dp, vertical = 10.dp),
            title = stringResource(R.string.partymember_etc_title),
            description = stringResource(R.string.partymember_etc_description),
            fontColor = White,
            backgroundColor = Cetacean_Blue
        )
    }
}

@Composable
private fun ColumnScope.PartyMemberRecruitmentCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    fontColor: Color,
    backgroundColor: Color,
    @DrawableRes backgroundRes: Int? = null
) {
    Box(modifier = modifier.background(backgroundColor, RoundedCornerShape(10.dp))) {
        Column {
            Text(
                text = title,
                modifier = Modifier.padding(start = 22.dp, top = 25.dp),
                color = fontColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )

            Text(
                text = description,
                modifier = Modifier.padding(start = 22.dp, top = 8.dp),
                color = fontColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 18.sp
            )
        }

        if (backgroundRes != null) {
            // TODO
            Image(
                painter = painterResource(backgroundRes),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}
