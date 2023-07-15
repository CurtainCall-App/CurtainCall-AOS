package com.cmc.curtaincall.feature.partymember.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.partymember.PartyType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberListScreen(
    partyType: PartyType,
    onNavigateDetail: () -> Unit,
    onBack: () -> Unit
) {
    var queryString by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBarWithSearch(
                title = stringResource(
                    when (partyType) {
                        PartyType.PERFORMANCE -> R.string.partymember_performance_title
                        PartyType.MEAL -> R.string.partymember_restaurant_title
                        PartyType.ETC -> R.string.partymember_etc_title
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                query = queryString,
                onChangeText = { queryString = it },
                onBack = onBack
            )
        }
    ) { paddingValues ->
        PartyMemberList(
            partyType = partyType,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun PartyMemberList(
    partyType: PartyType,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.background(Black.copy(alpha = 0.36f))) {
        item {
            Text(
                text = stringResource(R.string.partymember_list_description),
                modifier = Modifier.padding(start = 22.dp, top = 28.dp, bottom = 8.dp),
                color = Slate_Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }

        item {
            PartyMemberItemCard(
                partyType = partyType,
                cardName = "비스티",
                profile = "",
                name = "고라파덕",
                createdAt = "2023.6.7. 11:51",
                numberOfMember = 1,
                maxOfMember = 5,
                description = "비스티 이번주 토욜 저녁 공연 같이 봐요~",
                poster = "",
                date = "2023.6.24(토)",
                time = "19:30",
                location = "링크아트센터",
            )
        }

        item {
            PartyMemberItemCard(
                partyType = partyType,
                cardName = "비스티",
                profile = "",
                name = "고라파덕",
                createdAt = "2023.6.7. 11:51",
                numberOfMember = 1,
                maxOfMember = 5,
                description = "비스티 이번주 토욜 저녁 공연 같이 봐요~~~~~~~~~~",
                poster = "",
                date = "2023.6.24(토)",
                time = "19:30",
                location = "링크아트센터",
            )
        }

        item {
            PartyMemberItemCard(
                partyType = partyType,
                cardName = "비스티",
                profile = "",
                name = "고라파덕",
                createdAt = "2023.6.7. 11:51",
                numberOfMember = 1,
                maxOfMember = 5,
                description = "비스티 이번주 토욜 저녁 공연 같이 봐요~~~~~~~~~~~~~~~~~~~~~",
                poster = "",
                date = "2023.6.24(토)",
                time = "19:30",
                location = "링크아트센터",
            )
        }
    }
}

@Composable
private fun PartyMemberItemCard(
    partyType: PartyType,
    cardName: String,
    profile: String,
    name: String,
    createdAt: String,
    numberOfMember: Int,
    maxOfMember: Int,
    description: String,
    poster: String,
    date: String,
    time: String,
    location: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 10.dp)
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 13.dp)
                .background(White, RoundedCornerShape(15.dp))
                .padding(top = 28.dp)
                .padding(horizontal = 18.dp)
        ) {
            PartyMemberItemCardBody(
                modifier = Modifier.fillMaxWidth(),
                profile = profile,
                name = name,
                createdAt = createdAt,
                numberOfMember = numberOfMember,
                maxOfMember = maxOfMember
            )

            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                color = Eerie_Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                    .height(1.dp)
                    .background(Anti_Flash_White)
            )

            if (partyType == PartyType.ETC) {
                PartyMemberItemCardBottomText(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 18.dp)
                        .background(Bright_Gray, RoundedCornerShape(4.dp)),
                    iconRes = R.drawable.ic_calendar,
                    text = date
                )
            } else {
                PartyMemberItemCardBottom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 18.dp),
                    poster = poster,
                    date = date,
                    time = time,
                    location = location
                )
            }
        }

        Text(
            text = cardName,
            modifier = Modifier
                .background(Me_Pink, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomEnd = 4.dp))
                .padding(vertical = 3.dp, horizontal = 11.dp),
            color = White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun PartyMemberItemCardBody(
    modifier: Modifier = Modifier,
    profile: String,
    name: String,
    createdAt: String,
    numberOfMember: Int,
    maxOfMember: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_profile),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = name,
                color = Eerie_Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )

            Text(
                text = createdAt,
                modifier = Modifier.padding(top = 2.dp),
                color = Silver_Sand,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }

        Text(
            text = "$numberOfMember/$maxOfMember",
            color = Eerie_Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun PartyMemberItemCardBottom(
    modifier: Modifier = Modifier,
    poster: String,
    date: String,
    time: String,
    location: String
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier.size(59.dp, 81.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 15.dp)
                .height(81.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PartyMemberItemCardBottomText(
                modifier = Modifier.background(Bright_Gray, RoundedCornerShape(4.dp)),
                iconRes = R.drawable.ic_calendar,
                text = date
            )

            PartyMemberItemCardBottomText(
                modifier = Modifier.background(Bright_Gray, RoundedCornerShape(4.dp)),
                iconRes = R.drawable.ic_clock,
                text = time
            )

            PartyMemberItemCardBottomText(
                modifier = Modifier.background(Bright_Gray, RoundedCornerShape(4.dp)),
                iconRes = R.drawable.ic_location,
                text = location
            )
        }
    }
}

@Composable
private fun PartyMemberItemCardBottomText(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    text: String
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp)
                .size(15.dp)
        )

        Box(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Eerie_Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarWithSearch(
    title: String,
    modifier: Modifier = Modifier,
    query: String,
    onChangeText: (String) -> Unit,
    onBack: () -> Unit
) {
    var isActiveSearchBar by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            if (isActiveSearchBar) {
                BasicTextField(
                    value = query,
                    onValueChange = onChangeText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .background(White, RoundedCornerShape(8.dp)),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 12.dp)
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (query.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.partymember_list_top_appbar_placeholder),
                                    color = Silver_Chalice,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = spoqahansanseeo,
                                )
                            } else {
                                innerTextField()
                            }
                        }
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = Chinese_Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Eerie_Black
                )
            }
        },
        actions = {
            if (isActiveSearchBar.not()) {
                IconButton(onClick = { isActiveSearchBar = isActiveSearchBar.not() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = Eerie_Black
                    )
                }
            } else {
                IconButton(onClick = { isActiveSearchBar = isActiveSearchBar.not() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Black.copy(0.36f))
    )
}
