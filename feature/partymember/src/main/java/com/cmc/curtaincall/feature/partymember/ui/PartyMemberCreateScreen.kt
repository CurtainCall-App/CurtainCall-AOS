package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.partymember.PartyType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberCreateScreen(
    partyType: PartyType,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.partymember_create_appbar),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                onClick = onBack
            )
        }
    ) { paddingValues ->
        PartyMemberCreateContent(
            partyType = partyType,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(White)
        )
    }
}

@Composable
private fun PartyMemberCreateContent(
    partyType: PartyType,
    modifier: Modifier = Modifier
) {
    var step by remember { mutableStateOf(1) }
    Column(modifier) {
        PartyMemberCreatePhrase(
            partyType = partyType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
                .padding(horizontal = 22.dp),
            currentStep = step
        ) {
        }
    }
}

@Composable
private fun PartyMemberCreatePhrase(
    partyType: PartyType,
    modifier: Modifier = Modifier,
    currentStep: Int,
    content: @Composable (Int) -> Unit
) {
    Column(modifier) {
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Step 1",
                    color = Cetacean_Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp)
                        .height(8.dp)
                        .background(Cetacean_Blue, RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp))
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Step 2",
                    color = if (currentStep >= 2) Cetacean_Blue else Bright_Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp)
                        .height(8.dp)
                        .background(
                            if (currentStep >= 2) Cetacean_Blue else Bright_Gray,
                            if (partyType == PartyType.ETC) RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp) else RectangleShape
                        )
                )
            }

            if (partyType != PartyType.ETC) {
                Column(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Step 3",
                        color = if (currentStep == 3) Cetacean_Blue else Bright_Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp)
                            .height(8.dp)
                            .background(
                                if (currentStep == 3) Cetacean_Blue else Bright_Gray,
                                RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
                            )
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 22.dp)
                .background(Cetacean_Blue, RoundedCornerShape(12.dp))
                .padding(horizontal = 11.dp, vertical = 3.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(
                    when (partyType) {
                        PartyType.PERFORMANCE -> R.string.partymember_performance_title
                        PartyType.MEAL -> R.string.partymember_restaurant_title
                        PartyType.ETC -> R.string.partymember_etc_title
                    }
                ),
                color = White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }

        content(currentStep)
    }
}
