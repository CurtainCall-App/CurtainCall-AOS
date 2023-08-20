package com.cmc.curtaincall.common.design.component.content.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallIconText
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

enum class PartyType(val value: String) {
    PERFORMANCE("공연 관람"), MEAL("식사/카페"), ETC("기타")
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
    poster: Painter,
    date: String,
    time: String,
    location: String,
    hasLiveTalk: Boolean = false,
    onNavigateDetail: (PartyType) -> Unit = {},
    onNavigateLiveTalk: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onNavigateDetail(partyType) }
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
                    .padding(top = if (partyType == PartyType.ETC) 20.dp else 30.dp, bottom = 20.dp),
            ) {
                PartyMemberContentCardHeader(
                    modifier = Modifier.fillMaxWidth(),
                    nickname = nickname,
                    createAtDate = createAtDate,
                    createAtTime = createAtTime,
                    numberOfMember = numberOfMember,
                    numberOfTotal = numberOfTotal,
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
                    poster = poster,
                    date = date,
                    time = time,
                    location = location
                )
                if (hasLiveTalk) {
                    CurtainCallRoundedTextButton(
                        onClick = onNavigateLiveTalk,
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
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun PartyMemberContentCardBody(
    partyType: PartyType,
    poster: Painter,
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
            Image(
                painter = poster,
                contentDescription = null,
                modifier = Modifier.size(64.dp, 85.dp)
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
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = String.format("%d/%d", numberOfMember, numberOfTotal),
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
