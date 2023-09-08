package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.DottedLine
import com.cmc.curtaincall.common.design.component.content.card.PartyMemberCard
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PartyMemberScreen(
    partyMemberViewModel: PartyMemberViewModel = hiltViewModel(),
    onNavigateList: (PartyType) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cultured)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cultured)
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.partymember_recruitment),
            modifier = Modifier.padding(top = 60.dp),
            color = Black,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp, bottom = 41.dp)
        ) {
            PartyMemberCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = stringResource(R.string.partymember_performance_title),
                description = stringResource(R.string.partymember_performance_description),
                onClick = { onNavigateList(PartyType.PERFORMANCE) }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_partymember_performance),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 11.dp, bottom = 21.dp)
                        .size(100.dp)
                )
            }
            DottedLine(
                modifier = Modifier.fillMaxWidth(),
                strokeWidth = 10.dp.value,
                strokeColor = Cultured,
                intervals = floatArrayOf(10.dp.value, 15.dp.value),
                phase = 15.dp.value
            )
            PartyMemberCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = stringResource(R.string.partymember_restaurant_title),
                description = stringResource(R.string.partymember_restaurant_description),
                onClick = { onNavigateList(PartyType.MEAL) }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_partymember_food),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 33.dp, bottom = 23.dp)
                        .size(55.dp, 98.dp)
                )
            }
            DottedLine(
                modifier = Modifier.fillMaxWidth(),
                strokeWidth = 10.dp.value,
                strokeColor = Cultured,
                intervals = floatArrayOf(10.dp.value, 15.dp.value),
                phase = 15.dp.value
            )
            PartyMemberCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = stringResource(R.string.partymember_etc_title),
                description = stringResource(R.string.partymember_etc_description),
                onClick = { onNavigateList(PartyType.ETC) }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_partymember_etc),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 28.dp, bottom = (29.13).dp)
                        .size(65.dp, (88.87).dp)
                )
            }
        }
    }
}
