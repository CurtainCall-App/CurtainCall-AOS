package com.cmc.curtaincall.common.design.component.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun MyContentCard(
    modifier: Modifier = Modifier,
    containColor: Color,
    shape: Shape,
    painter: Painter,
    title: String,
    description: String,
    numberOfPartyMember: Int,
    numberOfTotalMember: Int,
    date: String,
    time: String
) {
    Row(
        modifier = modifier
            .background(containColor, shape)
            .height(97.dp)
            .padding(9.dp)
    ) {
        AsyncImage(
            model = null,
            error = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(58 / 77f)
        )
        Column(Modifier.padding(start = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = String.format("[%s]", title),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = String.format("%d/%d", numberOfPartyMember, numberOfTotalMember),
                    color = Roman_Silver,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Text(
                text = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(Modifier.weight(1f))
            Row {
                Row(
                    modifier = Modifier
                        .background(Me_Pink, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
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
                        .background(Me_Pink, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
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

@Preview
@Composable
private fun MyContentCardPreview() {
    MyContentCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        containColor = Cultured,
        shape = RoundedCornerShape(10.dp),
        painter = painterResource(R.drawable.img_poster),
        title = "시카고",
        description = "공연 끝나고 같이 근처에서 야식 먹공연 끝나고 같이 근처에서 야식 먹공연 끝나고 같이 근처에서 야식 먹",
        numberOfPartyMember = 2,
        numberOfTotalMember = 4,
        date = "2023.7.16(토)",
        time = "19:30"
    )
}
