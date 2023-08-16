package com.cmc.curtaincall.common.design.component.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.custom.RatingBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cadet_Grey
import com.cmc.curtaincall.common.design.theme.Eerie_Black
import com.cmc.curtaincall.common.design.theme.Ghost_White
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun ReviewItem(
    modifier: Modifier = Modifier,
    rating: Int,
    name: String,
    date: String,
    comment: String
) {
    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, Bright_Gray), RoundedCornerShape(10.dp))
            .padding(start = 12.dp, top = 14.dp, bottom = 16.dp)
    ) {
        RatingBar(
            modifier = Modifier.size(14.dp),
            rating = 4
        )
        Text(
            text = String.format("%s | %s", name, date),
            modifier = Modifier.padding(top = 6.dp),
            color = Black_Coral,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = comment,
            modifier = Modifier.padding(top = 12.dp),
            color = Nero,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 16.dp.toSp(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ReviewDetailItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    rating: Int,
    name: String,
    date: String,
    comment: String,
    numberOfLike: Int,
    isMyWriting: Boolean = false,
    onClickLike: (Boolean) -> Unit = {},
    onChangeWriting: () -> Unit = {},
    onRemoveWriting: () -> Unit = {}
) {
    var isCheckLike by remember { mutableStateOf(false) }
    var isClickMoreVert by remember { mutableStateOf(false) }
    Box(modifier) {
        Row(
            modifier = if (isMyWriting) Modifier.padding(end = 24.dp) else Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(42.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(
                Modifier.padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    RatingBar(
                        modifier = Modifier.size(14.dp),
                        rating = rating
                    )
                    Text(
                        text = String.format("%s | %s", name, date),
                        modifier = Modifier.padding(top = 4.dp),
                        color = Nero,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                if (isMyWriting.not()) {
                    Box(
                        modifier = Modifier
                            .border(BorderStroke(1.dp, Silver_Sand), RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.performance_review_repot),
                            color = Silver_Sand,
                            fontSize = 13.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .zIndex(if (isClickMoreVert) 1f else 0f),
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vert),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isClickMoreVert = isClickMoreVert.not() },
                tint = Eerie_Black
            )
            if (isClickMoreVert) {
                Card(
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .size(73.dp, 86.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .clickable { onChangeWriting() },
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .clickable { onRemoveWriting() },
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
        Text(
            text = comment,
            modifier = Modifier.padding(top = 62.dp),
            color = Nero,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            modifier = Modifier
                .padding(top = 95.dp)
                .background(
                    if (isCheckLike) Me_Pink else Ghost_White,
                    RoundedCornerShape(6.dp)
                )
                .clickable {
                    isCheckLike = isCheckLike.not()
                    onClickLike(isCheckLike)
                }
                .padding(vertical = 4.dp, horizontal = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(
                    painter = painterResource(
                        if (isCheckLike) R.drawable.ic_thumb_up_sel else R.drawable.ic_thumb_up
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = numberOfLike.toString(),
                    modifier = Modifier.padding(start = 4.dp),
                    color = if (isCheckLike) White else Cadet_Grey,
                    fontSize = 11.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}
