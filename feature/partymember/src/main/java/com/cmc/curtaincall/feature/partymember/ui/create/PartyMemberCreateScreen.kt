package com.cmc.curtaincall.feature.partymember.ui.create

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.partymember.PartyType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private const val UNSELECTED_INDEX = -1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberCreateScreen(
    partyType: PartyType,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)
    var step by remember { mutableStateOf(1) }
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.partymember_create_appbar),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                onClick = {
                    if (step == 1) {
                        onBack()
                    } else {
                        step--
                    }
                }
            )
        }
    ) { paddingValues ->
        PartyMemberCreateContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(White),
            partyType = partyType,
            currentStep = step,
            onChangeStep = { step = it }
        )
    }
}

@Composable
private fun PartyMemberCreateContent(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    currentStep: Int,
    onChangeStep: (Int) -> Unit
) {
    var selectedPerformanceIndex by remember { mutableStateOf(UNSELECTED_INDEX) }
    var dateState by remember { mutableStateOf("") }
    var timeState by remember { mutableStateOf("") }

    Box(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(16.dp)
        ) {
            createStep(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp)
                    .padding(horizontal = 6.dp),
                partyType = partyType,
                currentStep = currentStep
            )

            when (currentStep) {
                1 -> showFirstStep(
                    modifier = Modifier.fillMaxWidth(),
                    partyType = partyType,
                    selectedIndex = selectedPerformanceIndex,
                    onChangeSelect = { selectedPerformanceIndex = it }
                )
                2 -> showSecondStep(
                    modifier = Modifier.fillMaxWidth(),
                    partyType = partyType,
                    selectedDate = dateState,
                    selectedTime = timeState,
                    onSelectDate = { dateState = it },
                    onSelectTime = { timeState = it }
                )
            }
        }

        when (currentStep) {
            1 -> {
                Button(
                    onClick = { onChangeStep(currentStep + 1) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 22.dp)
                        .padding(bottom = 15.dp),
                    enabled = selectedPerformanceIndex != UNSELECTED_INDEX,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedPerformanceIndex != UNSELECTED_INDEX) Me_Pink else Bright_Gray,
                        disabledContainerColor = Bright_Gray
                    )
                ) {
                    Text(
                        text = stringResource(R.string.partymember_create_next),
                        color = if (selectedPerformanceIndex != UNSELECTED_INDEX) White else Silver_Sand,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
            2 -> {
                Button(
                    onClick = { onChangeStep(currentStep + 1) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 22.dp)
                        .padding(bottom = 15.dp),
                    enabled = dateState.isNotEmpty() and timeState.isNotEmpty(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (dateState.isNotEmpty() and timeState.isNotEmpty()) Me_Pink else Bright_Gray,
                        disabledContainerColor = Bright_Gray
                    )
                ) {
                    Text(
                        text = stringResource(R.string.partymember_create_next),
                        color = if (dateState.isNotEmpty() and timeState.isNotEmpty()) White else Silver_Sand,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    }
}

private fun LazyGridScope.createStep(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    currentStep: Int,
) {
    item(span = { GridItemSpan(3) }) {
        Column(modifier) {
            Row {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.partymember_create_first_step),
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
                        text = stringResource(R.string.partymember_create_second_step),
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
                            text = stringResource(R.string.partymember_create_third_step),
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
        }
    }
}
