package com.cmc.curtaincall.feature.home.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.CardType
import com.cmc.curtaincall.common.design.component.PerformanceCard
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.common.design.R

@Composable
internal fun HomeContentTab(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    cardType: CardType
) {
    Column(modifier) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            itemsIndexed(List(10) {}) { index, item ->
                if (index == 0) Spacer(Modifier.size(20.dp))
                Row {
                    PerformanceCard(
                        cardType = cardType,
                        name = "데스노트",
                        image = painterResource(R.drawable.dummy_poster),
                        rate = 4.89f,
                        numberOfRate = 1021,
                        meta = (index + 1).toString()
                    )
                    Spacer(Modifier.size(12.dp))
                }
            }
        }
    }
}
