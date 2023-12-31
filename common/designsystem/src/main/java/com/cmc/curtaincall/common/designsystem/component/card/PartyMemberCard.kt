package com.cmc.curtaincall.common.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallIconText
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.DottedLine
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

enum class PartyType(
    val value: String,
    val category: String
) {
    PERFORMANCE(
        value = "공연 관람",
        category = "WATCHING"
    ),
    MEAL(
        value = "식사/카페",
        category = "FOOD_CAFE"
    ),
    ETC(
        value = "기타",
        category = "ETC"
    )
}

@Composable
fun PartyMemberCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(Cetacean_Blue, RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        Column(Modifier.padding(top = 22.dp, start = 20.dp)) {
            Text(
                text = title,
                color = White,
                fontSize = 20.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = description,
                modifier = Modifier.padding(top = 6.dp),
                color = White,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 17.dp.toSp()
            )
        }
        content()
    }
}

@Composable
fun PartyMemberEtcItemCard(
    modifier: Modifier = Modifier,
    profileImageUrl: String? = null,
    nickname: String,
    createAtDate: String,
    createAtTime: String,
    description: String,
    date: String?,
    numberOfMember: Int,
    numberOfTotal: Int,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp)
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(top = 15.dp, bottom = 20.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = profileImageUrl,
                        error = painterResource(R.drawable.ic_default_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(Modifier.padding(start = 10.dp)) {
                        Text(
                            text = nickname,
                            color = Nero,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Bold,
                            fontFamily = spoqahansanseeo
                        )
                        Text(
                            text = "$createAtDate $createAtTime",
                            color = Silver_Sand,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
                Text(
                    text = description,
                    modifier = Modifier.padding(top = 18.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CurtainCallIconText(
                        modifier = Modifier.wrapContentHeight(),
                        painter = painterResource(R.drawable.ic_calendar),
                        text = date ?: "날짜 미정",
                        containerColor = Cultured,
                        contentColor = Nero,
                        fontSize = 12.dp.toSp(),
                        iconModifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "$numberOfMember/$numberOfTotal",
                        modifier = Modifier.fillMaxWidth(),
                        color = Nero,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

@Composable
fun PartyMemberItemCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    profileImageUrl: String? = null,
    nickname: String,
    createAtDate: String,
    createAtTime: String,
    description: String,
    posterUrl: String? = null,
    date: String?,
    time: String?,
    location: String?,
    numberOfMember: Int,
    numberOfTotal: Int,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(White, RoundedCornerShape(12.dp)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (title != null) {
                        Text(
                            text = title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp, vertical = 16.dp),
                            color = Chinese_Black,
                            fontSize = 16.dp.toSp(),
                            fontWeight = FontWeight.Bold,
                            fontFamily = spoqahansanseeo,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                DottedLine(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 11.dp),
                    strokeWidth = 10.dp.value,
                    strokeColor = Bright_Gray,
                    intervals = floatArrayOf(10.dp.value, 15.dp.value),
                    phase = 15.dp.value
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(219.dp)
                        .background(White, RoundedCornerShape(12.dp))
                        .padding(top = 15.dp, start = 18.dp, end = 20.dp, bottom = 20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = profileImageUrl,
                            error = painterResource(R.drawable.ic_default_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(Modifier.padding(start = 10.dp)) {
                            Text(
                                text = nickname,
                                color = Nero,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                            Text(
                                text = "$createAtDate $createAtTime",
                                color = Silver_Sand,
                                fontSize = 12.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Text(
                        text = description,
                        modifier = Modifier.padding(top = 18.dp),
                        color = Nero,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        AsyncImage(
                            model = posterUrl,
                            error = painterResource(R.drawable.ic_error_poster),
                            contentDescription = null,
                            modifier = Modifier
                                .size(66.dp, 87.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (date != null) {
                                CurtainCallIconText(
                                    modifier = Modifier.wrapContentHeight(),
                                    painter = painterResource(R.drawable.ic_calendar),
                                    text = date,
                                    containerColor = Cultured,
                                    contentColor = Nero,
                                    fontSize = 12.dp.toSp(),
                                    iconModifier = Modifier.size(16.dp)
                                )
                            }
                            if (time != null) {
                                CurtainCallIconText(
                                    modifier = Modifier.wrapContentHeight(),
                                    painter = painterResource(R.drawable.ic_clock),
                                    text = time,
                                    containerColor = Cultured,
                                    contentColor = Nero,
                                    fontSize = 12.dp.toSp(),
                                    iconModifier = Modifier.size(16.dp)
                                )
                            }
                            if (location != null) {
                                CurtainCallIconText(
                                    modifier = Modifier.wrapContentHeight(),
                                    painter = painterResource(R.drawable.ic_location),
                                    text = location,
                                    containerColor = Cultured,
                                    contentColor = Nero,
                                    fontSize = 12.dp.toSp(),
                                    iconModifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Column {
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "$numberOfMember/$numberOfTotal",
                                color = Nero,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PartyMemberContentCard(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    title: String,
    painter: Painter = painterResource(R.drawable.ic_default_profile),
    nickname: String,
    createAtDate: String,
    createAtTime: String,
    numberOfMember: Int,
    numberOfTotal: Int,
    description: String,
    posterUrl: String? = null,
    date: String,
    time: String,
    location: String,
    hasLiveTalk: Boolean = false,
    onClick: () -> Unit = {},
    onAction: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(bottom = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(top = 14.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp),
            colors = CardDefaults.cardColors(
                containerColor = White,
                contentColor = White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .padding(top = if (partyType == PartyType.ETC) 20.dp else 30.dp, bottom = 20.dp)
            ) {
                PartyMemberContentCardHeader(
                    modifier = Modifier.fillMaxWidth(),
                    nickname = nickname,
                    createAtDate = createAtDate,
                    createAtTime = createAtTime,
                    numberOfMember = numberOfMember,
                    numberOfTotal = numberOfTotal
                )
                Text(
                    text = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = Nero,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 13.dp, bottom = 16.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
                PartyMemberContentCardBody(
                    partyType = partyType,
                    posterUrl = posterUrl,
                    date = date,
                    time = time,
                    location = location
                )
                if (hasLiveTalk) {
                    CurtainCallRoundedTextButton(
                        onClick = onAction,
                        modifier = Modifier
                            .padding(top = 22.dp)
                            .fillMaxWidth()
                            .height(40.dp),
                        title = stringResource(R.string.mypage_livetalk_entrance),
                        fontSize = 14.dp.toSp(),
                        containerColor = Me_Pink,
                        contentColor = White,
                        radiusSize = 8.dp
                    )
                }
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .background(Me_Pink, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomEnd = 4.dp))
                .padding(vertical = 4.dp, horizontal = 12.dp),
            color = White,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun PartyMemberContentCardBody(
    partyType: PartyType,
    posterUrl: String? = null,
    date: String,
    time: String,
    location: String
) {
    if (partyType == PartyType.ETC) {
        CurtainCallIconText(
            modifier = Modifier.wrapContentHeight(),
            painter = painterResource(R.drawable.ic_calendar),
            text = date,
            containerColor = Cultured,
            contentColor = Nero,
            fontSize = 12.dp.toSp(),
            iconModifier = Modifier.size(16.dp)
        )
    } else {
        Row(
            modifier = Modifier.wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp, 85.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .height(85.dp)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CurtainCallIconText(
                    modifier = Modifier.wrapContentHeight(),
                    painter = painterResource(R.drawable.ic_calendar),
                    text = date,
                    containerColor = Cultured,
                    contentColor = Nero,
                    fontSize = 12.dp.toSp(),
                    iconModifier = Modifier.size(16.dp)
                )
                CurtainCallIconText(
                    modifier = Modifier.wrapContentHeight(),
                    painter = painterResource(R.drawable.ic_clock),
                    text = time,
                    containerColor = Cultured,
                    contentColor = Nero,
                    fontSize = 12.dp.toSp(),
                    iconModifier = Modifier.size(16.dp)
                )
                CurtainCallIconText(
                    modifier = Modifier.wrapContentHeight(),
                    painter = painterResource(R.drawable.ic_location),
                    text = location,
                    containerColor = Cultured,
                    contentColor = Nero,
                    fontSize = 12.dp.toSp(),
                    iconModifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun PartyMemberContentCardHeader(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.ic_default_profile),
    nickname: String,
    createAtDate: String,
    createAtTime: String,
    numberOfMember: Int,
    numberOfTotal: Int,
) {
    Row(modifier) {
        AsyncImage(
            model = null,
            contentDescription = null,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape),
            error = painter
        )
        Column(Modifier.padding(start = 9.dp)) {
            Row {
                Text(
                    text = nickname,
                    modifier = Modifier.weight(1f),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = String.format("%d/%d", numberOfMember, numberOfTotal),
                    modifier = Modifier.padding(start = 4.dp),
                    color = Nero,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Text(
                text = String.format("%s %s", createAtDate, createAtTime),
                color = Silver_Sand,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
