package com.cmc.curtaincall.common.design.component.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.custom.RatingBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Nero
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
