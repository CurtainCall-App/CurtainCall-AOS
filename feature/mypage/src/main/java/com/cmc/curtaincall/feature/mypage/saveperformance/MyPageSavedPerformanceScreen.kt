package com.cmc.curtaincall.feature.mypage.saveperformance

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.mypage.MyPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageSavedPerformanceScreen(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_saved_performance_list),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageSavedPerformanceContent(
            myPageViewModel = myPageViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageSavedPerformanceContent(
    myPageViewModel: MyPageViewModel,
    modifier: Modifier = Modifier
) {
    val myFavoriteItems = myPageViewModel.favoriteShowItems.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier
            .padding(top = 2.dp)
            .padding(horizontal = 20.dp)
    ) {
        items(myFavoriteItems) { favoriteShowModel ->
            favoriteShowModel?.let { favoriteShowModel ->
//                PerformanceDetailCard(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 28.dp),
//                    imageUrl = favoriteShowModel.poster,
//                    painter = painterResource(R.drawable.ic_error_poster),
//                    title = favoriteShowModel.name,
//                    rate = favoriteShowModel.reviewGradeSum
//                )
            }
        }
        itemsIndexed(List(10) {}) { index, item ->
            SavedPerformanceItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .height(148.dp),
                title = "드림하이",
                rate = 4.8f,
                numberOfRate = 324,
                description = "지도를 가져오라. 짐은 이 왕국을 셋으로 나누었다” 브리튼의 늙은 왕 리어는 세 딸의 효심 크기에 따라 왕국을 나눠 주기로 한다. 영토를 많이 받기 위해 첫째 고너릴과 둘째 리건은 과장된 말로"
            )
            if (index < 9) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun SavedPerformanceItem(
    modifier: Modifier = Modifier,
    title: String,
    rate: Float,
    numberOfRate: Int,
    description: String
) {
    Row(modifier) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(111 / 148f),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        ) {
            Row {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = Chinese_Black,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified
                        )
                        Text(
                            text = rate.toString(),
                            modifier = Modifier.padding(start = 2.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                        Text(
                            text = String.format("(%d)", numberOfRate),
                            modifier = Modifier.padding(start = 2.dp),
                            color = Roman_Silver,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(26.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Cetacean_Blue
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_bookmark_sel),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                color = Black_Coral,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo,
                lineHeight = 18.dp.toSp(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
