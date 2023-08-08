package com.cmc.curtaincall.feature.home.ui.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
internal fun HomeMyTab(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = Color.Unspecified
            )
            Text(
                text = title,
                modifier = Modifier.padding(start = 6.dp),
                color = Chinese_Black,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        content()
    }
}

@Composable
internal fun HomeMyTabItem(
    modifier: Modifier = Modifier,
    image: Painter,
    description: String,
    numberOfMember: Int,
    numberOfTotal: Int,
    date: String,
    time: String
) {
    Row(
        modifier = modifier
            .background(Cultured, RoundedCornerShape(10.dp))
            .height(94.dp)
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(56.dp, 74.dp)
        )
        Column(Modifier.padding(start = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = description,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = String.format(
                        stringResource(R.string.home_my_number_of_member_format),
                        numberOfMember,
                        numberOfTotal
                    ),
                    color = Roman_Silver,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(Modifier.weight(1f))
            Row(Modifier.padding(bottom = 2.dp)) {
                Row(
                    modifier = Modifier
                        .background(Me_Pink, RoundedCornerShape(11.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                    Text(
                        text = date,
                        modifier = Modifier.padding(start = 6.dp),
                        color = White,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .background(Me_Pink, RoundedCornerShape(11.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                    Text(
                        text = time,
                        modifier = Modifier.padding(start = 6.dp),
                        color = White,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    }
}
