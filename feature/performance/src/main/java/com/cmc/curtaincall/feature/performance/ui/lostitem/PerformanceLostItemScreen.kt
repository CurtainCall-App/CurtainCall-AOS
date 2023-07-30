package com.cmc.curtaincall.feature.performance.ui.lostitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.SelectedDateCalender
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemScreen(
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.performance_find_lost_item),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(58.dp),
                shape = CircleShape,
                containerColor = Cetacean_Blue
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pen),
                    contentDescription = null,
                    modifier = Modifier.size(29.dp),
                    tint = Color.Unspecified
                )
            }
        }
    ) { paddingValues ->
        PerformanceLostItemContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
                .padding(top = 23.dp)
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
private fun PerformanceLostItemContent(
    modifier: Modifier = Modifier
) {
    var isClickedDate by remember { mutableStateOf(false) }
    var isClickedType by remember { mutableStateOf(false) }
    Box(modifier) {
        DropDownLostItem(
            modifier = Modifier.fillMaxWidth(),
            isClickedDate = isClickedDate,
            isClickedType = isClickedType,
            onClickDate = { isClickedDate = it },
            onClickType = { isClickedType = it },
            content = {
                if (isClickedDate) {
                    SelectedDateCalender(
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                if (isClickedType) {
                    LostItemTypeGrid(
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 30.dp)
                            .fillMaxWidth()
                            .height(336.dp),
                        itemModifier = Modifier.size(60.dp, 90.dp)
                    )
                }
            }
        )
    }
}

@Composable
private fun DropDownLostItem(
    modifier: Modifier = Modifier,
    isClickedDate: Boolean = false,
    isClickedType: Boolean = false,
    onClickDate: (Boolean) -> Unit = {},
    onClickType: (Boolean) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(modifier) {
        Row(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onClickDate(isClickedDate.not())
                        if (isClickedDate.not()) onClickType(false)
                    }
                    .background(Cultured, RoundedCornerShape(6.dp))
                    .border(BorderStroke(1.dp, if (isClickedDate) Roman_Silver else Color.Transparent), RoundedCornerShape(6.dp))
                    .padding(vertical = 9.dp)
                    .padding(start = 12.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_date),
                    color = Silver_Sand,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.ic_dropdown),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable {
                        onClickType(isClickedType.not())
                        if (isClickedType.not()) onClickDate(false)
                    }
                    .background(Cultured, RoundedCornerShape(6.dp))
                    .border(BorderStroke(1.dp, if (isClickedType) Roman_Silver else Color.Transparent), RoundedCornerShape(6.dp))
                    .padding(vertical = 9.dp)
                    .padding(start = 12.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_type),
                    color = Silver_Sand,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.ic_dropdown),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
        if (isClickedDate or isClickedType) {
            content()
        }
    }
}
