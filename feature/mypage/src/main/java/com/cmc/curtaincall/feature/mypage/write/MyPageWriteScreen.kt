package com.cmc.curtaincall.feature.mypage.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

enum class WriteType(val value: String) {
    TOTAL("전체"), REVIEW("한 줄 리뷰"), LOST_ITEM("분실물")
}

data class MyWriteModel(
    val writeType: WriteType,
    val title: String = "",
    val description: String = "",
    val findLocation: String = "",
    val findDate: String = "",
    val date: String = "",
    val imageUrl: String = ""
)

val writeModels = listOf(
    MyWriteModel(WriteType.LOST_ITEM, "파우치", description = "", "LG아트센터 서울", "2023-06-09", "2023.6.7 11:52"),
    MyWriteModel(WriteType.REVIEW, description = "진짜 리어왕 ㄹㅇㄹㅇㄹㅇㄹㅇㄹ 핵존잼~!", date = "2023.6.7 11:52"),
    MyWriteModel(WriteType.LOST_ITEM, "파우치", description = "", "LG아트센터 서울", "2023-06-09", "2023.6.7 11:52"),
    MyWriteModel(WriteType.REVIEW, description = "진짜 리어왕 ㄹㅇㄹㅇㄹㅇㄹㅇㄹ 핵존잼~!", date = "2023.6.7 11:52"),
    MyWriteModel(WriteType.LOST_ITEM, "파우치", description = "", "LG아트센터 서울", "2023-06-09", "2023.6.7 11:52"),
    MyWriteModel(WriteType.REVIEW, description = "진짜 리어왕 ㄹㅇㄹㅇㄹㅇㄹㅇㄹ 핵존잼~!", date = "2023.6.7 11:52"),
    MyWriteModel(WriteType.LOST_ITEM, "파우치", description = "", "LG아트센터 서울", "2023-06-09", "2023.6.7 11:52")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageWriteScreen(
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
                .background(White)
        )
    }
}

@Composable
private fun MyPageWriteContent(
    modifier: Modifier = Modifier
) {
    var writeTypeState by remember { mutableStateOf(WriteType.TOTAL) }
    Column(modifier) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            MyPageWriteMenuTab(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                writeType = writeTypeState,
                onChangeWriteType = { writeTypeState = it }
            )
            LazyColumn(Modifier.padding(top = 26.dp)) {
                items(
                    if (writeTypeState == WriteType.TOTAL) {
                        writeModels
                    } else {
                        writeModels.filter { it.writeType == writeTypeState }
                    }
                ) {
                    MyPageWriteItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        writeModel = it
                    )
                }
            }
        }
    }
}

@Composable
private fun MyPageWriteItem(
    modifier: Modifier = Modifier,
    writeModel: MyWriteModel
) {
    var isClickMoreTab by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .background(Cultured, RoundedCornerShape(15.dp))
            .padding(vertical = 12.dp, horizontal = 18.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Me_Pink, RoundedCornerShape(17.dp))
                    .padding(vertical = 6.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = writeModel.writeType.value,
                    color = White,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Text(
                text = writeModel.date,
                modifier = Modifier.padding(start = 8.dp),
                color = Roman_Silver,
                fontSize = 10.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
        Column(
            modifier = Modifier
                .zIndex(if (isClickMoreTab) 1f else 0f)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .size(24.dp)
                    .clickable { isClickMoreTab = isClickMoreTab.not() },
                tint = Color.Unspecified
            )
            if (isClickMoreTab) {
                Column(
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .background(White, RoundedCornerShape(8.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .background(Me_Pink.copy(0.15f), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.mypage_writing_edit),
                            color = Black_Pearl,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    Box(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.mypage_writing_remove),
                            color = Black_Pearl,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            }
        }

        when (writeModel.writeType) {
            WriteType.LOST_ITEM -> {
                Column(Modifier.padding(top = 44.dp)) {
                    Text(
                        text = writeModel.title,
                        color = Nero,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    Text(
                        text = String.format(
                            stringResource(R.string.mypage_writing_lost_item_find_location_format),
                            writeModel.findLocation
                        ),
                        modifier = Modifier.padding(top = 9.dp),
                        color = Black_Coral,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Text(
                        text = String.format(
                            stringResource(R.string.mypage_writing_lost_item_find_date_format),
                            writeModel.findDate
                        ),
                        modifier = Modifier.padding(top = 6.dp),
                        color = Black_Coral,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Image(
                    painter = painterResource(R.drawable.img_poster),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(67.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

            WriteType.REVIEW -> {
                Text(
                    text = writeModel.description,
                    modifier = Modifier.padding(top = 44.dp),
                    color = Nero,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }

            else -> Unit
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
                    if (writeType == WriteType.TOTAL) Cetacean_Blue else Cultured,
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable { onChangeWriteType(WriteType.TOTAL) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = WriteType.TOTAL.value,
                color = if (writeType == WriteType.TOTAL) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
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
