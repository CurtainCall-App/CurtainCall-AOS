package com.cmc.curtaincall.feature.mypage.write

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

enum class WriteType(val value: String) {
    REVIEW("한 줄 리뷰"), LOST_ITEM("분실물")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageWriteScreen(
    onNavigateLostItemEdit: () -> Unit,
    onNavigateReviewEdit: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_my_writing),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageWriteContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onNavigateLostItemEdit = onNavigateLostItemEdit,
            onNavigateReviewEdit = onNavigateReviewEdit
        )
    }
}

@Composable
private fun MyPageWriteContent(
    modifier: Modifier = Modifier,
    onNavigateLostItemEdit: () -> Unit,
    onNavigateReviewEdit: () -> Unit
) {
    var writeTypeState by remember { mutableStateOf(WriteType.REVIEW) }
    Column(modifier) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            MyPageWriteMenuTab(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                writeType = writeTypeState,
                onChangeWriteType = { writeTypeState = it }
            )
        }
    }
}

@Composable
private fun MyPageWriteMenuTab(
    modifier: Modifier,
    writeType: WriteType,
    onChangeWriteType: (WriteType) -> Unit
) {
    Row(modifier) {
        Box(
            modifier = Modifier
                .background(
                    if (writeType == WriteType.REVIEW) Cetacean_Blue else Cultured,
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable { onChangeWriteType(WriteType.REVIEW) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = WriteType.REVIEW.value,
                color = if (writeType == WriteType.REVIEW) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .background(
                    if (writeType == WriteType.LOST_ITEM) Cetacean_Blue else Cultured,
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable { onChangeWriteType(WriteType.LOST_ITEM) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = WriteType.LOST_ITEM.value,
                color = if (writeType == WriteType.LOST_ITEM) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
