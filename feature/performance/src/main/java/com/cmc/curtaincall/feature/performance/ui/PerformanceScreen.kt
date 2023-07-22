package com.cmc.curtaincall.feature.performance.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(
    onNavigateDetail: () -> Unit
) {
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
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = stringResource(R.string.performance_search_description),
                    color = Black_Coral,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                SelectTypeButton(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                )
            }
        }
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
                fontSize = 16.sp,
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
                fontSize = 16.sp,
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
                            fontSize = 16.sp,
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
                                            fontSize = 16.sp,
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
