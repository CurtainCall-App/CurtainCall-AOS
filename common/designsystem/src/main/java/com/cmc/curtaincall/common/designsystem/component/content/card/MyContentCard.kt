package com.cmc.curtaincall.common.designsystem.component.content.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
fun MyContentCard(
    modifier: Modifier = Modifier,
    category: String,
    title: String,
    numberOfPartyMember: Int,
    numberOfTotalMember: Int,
    description: String,
    date: String? = null,
    imageUrl: String? = null,
    showName: String?,
    time: String?
) {
    Card(
        modifier = modifier.padding(bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            if (category == PartyType.ETC.category) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 18.dp),
                        color = Nero,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$numberOfPartyMember/$numberOfTotalMember",
                        color = Silver_Sand,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                }
                Text(
                    text = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(Modifier.padding(top = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .background(Cultured, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = PartyType.ETC.value,
                            color = Black_Coral,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(Cultured, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp),
                            tint = Black_Coral
                        )
                        Text(
                            text = date ?: "날짜 미정",
                            modifier = Modifier.padding(start = 4.dp),
                            color = Black_Coral,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            } else {
                Row(Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        error = painterResource(R.drawable.ic_error_poster),
                        modifier = Modifier
                            .size(56.dp, 76.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(Modifier.padding(start = 12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .widthIn(max = 170.dp)
                                    .wrapContentWidth()
                                    .background(Me_Pink, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 8.dp, vertical = 3.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = showName ?: "제목없음",
                                    color = White,
                                    fontSize = 14.dp.toSp(),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = spoqahansanseeo,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )
                            }
                            Text(
                                text = "$numberOfPartyMember/$numberOfTotalMember",
                                modifier = Modifier.weight(1f),
                                color = Silver_Sand,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo,
                                textAlign = TextAlign.End
                            )
                        }
                        Text(
                            text = title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            color = Nero,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            color = Black_Coral,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
                Row(Modifier.padding(top = 14.dp)) {
                    Box(
                        modifier = Modifier
                            .background(Cultured, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (category) {
                                PartyType.PERFORMANCE.category -> PartyType.PERFORMANCE.value
                                else -> PartyType.MEAL.value
                            },
                            color = Black_Coral,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(Cultured, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp),
                            tint = Black_Coral
                        )
                        Text(
                            text = date ?: "날짜 미정",
                            modifier = Modifier.padding(start = 4.dp),
                            color = Black_Coral,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .background(Cultured, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clock),
                            contentDescription = null,
                            modifier = Modifier.size(13.dp),
                            tint = Black_Coral
                        )
                        Text(
                            text = time ?: "00:00",
                            modifier = Modifier.padding(start = 4.dp),
                            color = Black_Coral,
                            fontSize = 12.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            }
        }
    }
}
