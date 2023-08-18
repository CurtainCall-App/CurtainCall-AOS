package com.cmc.curtaincall.common.design.component.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun GridLostItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    location: String,
    date: String
) {
    Column(modifier) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = title,
            modifier = Modifier.padding(top = 16.dp),
            color = Chinese_Black,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = String.format(
                stringResource(R.string.performance_find_lost_item_detail_place_of_acquisition_format),
                location
            ),
            modifier = Modifier.padding(top = 12.dp),
            color = Nero,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = String.format(
                stringResource(R.string.performance_find_lost_item_detail_acquistion_date_format),
                date
            ),
            modifier = Modifier.padding(top = 4.dp),
            color = Nero,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun LostItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    date: String,
    getLocation: String,
    storeLocation: String,
) {
    Row(
        modifier = modifier
            .border(BorderStroke(1.dp, Bright_Gray), RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(63.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(Modifier.padding(start = 12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    color = Chinese_Black,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = date,
                    color = Roman_Silver,
                    fontSize = 10.dp.toSp(),
                    fontWeight = FontWeight.Normal,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = "습득 장소 | $getLocation\n보관 장소 | $storeLocation",
                modifier = Modifier.padding(bottom = 3.dp),
                color = Black_Coral,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 16.dp.toSp()
            )
        }
    }
}
