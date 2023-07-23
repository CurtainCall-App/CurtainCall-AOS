package com.cmc.curtaincall.feature.performance.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(
    onNavigateDetail: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Whisper)

    var queryString by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            SearchAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                query = queryString,
                onChangeText = { queryString = it }
            )
        }
    ) { paddingValues ->
        PerformanceContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            onNavigateDetail = onNavigateDetail
        )
    }
}

@Composable
private fun PerformanceContent(
    modifier: Modifier = Modifier,
    onNavigateDetail: () -> Unit
) {
    LazyColumn(modifier) {
        item {
            Column {
                Text(
                    text = stringResource(R.string.performance_search),
                    color = Black,
                    fontSize = 24.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = stringResource(R.string.performance_search_description),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                SelectTypeButton(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                )
                SelectSortButton(modifier = Modifier.padding(top = 26.dp))
            }
        }

        itemsIndexed(Array(10) {}) { index, item ->
            PerformanceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateDetail() }
            )
            if (index != 9) {
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun PerformanceCard(
    modifier: Modifier = Modifier
) {
    var selectBookmark by remember { mutableStateOf(false) }
    Row(modifier = modifier.padding(vertical = 12.dp)) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier.size(114.dp, 153.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp)
        ) {
            Row {
                Column {
                    Text(
                        text = "드림하이",
                        color = Chinese_Black,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )

                    Row(
                        modifier = Modifier.padding(top = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified
                        )
                        Text(
                            text = "4.8",
                            modifier = Modifier.padding(start = 2.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.sp
                        )
                        Text(
                            text = "(324)",
                            modifier = Modifier.padding(start = 2.dp),
                            color = Roman_Silver,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.sp
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { selectBookmark = selectBookmark.not() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(26.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = if (selectBookmark) Cetacean_Blue else Bright_Gray
                    )
                ) {
                    Icon(
                        painter = painterResource(if (selectBookmark) R.drawable.ic_bookmark_sel else R.drawable.ic_bookmark),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "기간",
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
                Text(
                    text = "2023.6.1 - 2023.6.18",
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
            }

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "시간",
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
                Text(
                    text = "200분",
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
            }

            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = "일정",
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
                Text(
                    text = "화-금 19:00\n토, 일 14:00, 19:00",
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
            }

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "장소",
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
                Text(
                    text = "LG아트센터 서울",
                    modifier = Modifier.padding(start = 13.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.sp
                )
            }
        }
    }
}

@Composable
private fun SelectSortButton(
    modifier: Modifier = Modifier
) {
    var sortType by remember { mutableStateOf(true) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(4.dp)
                .background(
                    if (sortType) Fiery_Rose else Color.Transparent,
                    CircleShape
                ).clickable { sortType = true }
        )
        Text(
            text = stringResource(R.string.performance_search_popular_sort),
            modifier = Modifier.padding(start = 4.dp),
            color = if (sortType) Nero else Silver_Sand,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )

        Spacer(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(4.dp)
                .background(
                    if (sortType.not()) Fiery_Rose else Color.Transparent,
                    CircleShape
                ).clickable { sortType = false }
        )
        Text(
            text = stringResource(R.string.performance_search_korean_sort),
            modifier = Modifier.padding(start = 4.dp),
            color = if (sortType.not()) Nero else Silver_Sand,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun SelectTypeButton(
    modifier: Modifier = Modifier
) {
    var selectType by remember { mutableStateOf(true) }
    Row(
        modifier = modifier.background(Cultured, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 3.dp)
                .height(39.dp)
                .background(
                    if (selectType) Me_Pink else Cultured,
                    RoundedCornerShape(8.dp)
                ).clickable { selectType = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.partymember_create_classification_theater),
                color = if (selectType) White else Silver_Sand,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 3.dp)
                .height(39.dp)
                .background(
                    if (selectType.not()) Me_Pink else Cultured,
                    RoundedCornerShape(8.dp)
                ).clickable { selectType = false },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.partymember_create_classification_musical),
                color = if (selectType.not()) White else Silver_Sand,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchAppBar(
    modifier: Modifier = Modifier,
    query: String,
    onChangeText: (String) -> Unit
) {
    var isActiveSearchBar by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {
            if (isActiveSearchBar) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(24.dp),
                        onClick = { isActiveSearchBar = false }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    BasicTextField(
                        value = query,
                        onValueChange = onChangeText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 20.dp)
                            .height(36.dp)
                            .background(Cultured, RoundedCornerShape(8.dp))
                            .padding(start = 10.dp, end = 12.dp),
                        textStyle = TextStyle(
                            color = Nero,
                            fontSize = 16.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        ),
                        singleLine = true,
                        maxLines = 1,
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    innerTextField()
                                    if (query.isEmpty()) {
                                        Text(
                                            text = stringResource(R.string.partymember_list_top_appbar_placeholder),
                                            modifier = Modifier.padding(start = 4.dp),
                                            color = Silver_Sand,
                                            fontSize = 16.dp.toSp(),
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = spoqahansanseeo
                                        )
                                    }
                                }
                                IconButton(
                                    modifier = Modifier.size(22.dp),
                                    onClick = { onChangeText("") }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_close),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        }
                    )
                }
            }
        },
        modifier = modifier,
        actions = {
            if (isActiveSearchBar.not()) {
                IconButton(onClick = { isActiveSearchBar = true }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        modifier = Modifier.size(24.dp),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}
