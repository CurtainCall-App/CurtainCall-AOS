package com.cmc.curtaincall.feature.home.tab

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.LiveTalkCard
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
internal fun HomeLiveTalkTab(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Cultured)
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_chatting),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = Color.Unspecified
            )
            Text(
                text = stringResource(R.string.home_coming_livetalk),
                modifier = Modifier.padding(start = 6.dp),
                color = Chinese_Black,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            itemsIndexed(List(10) {}) { index, item ->
                if (index == 0) Spacer(Modifier.size(20.dp))
                Row {
                    LiveTalkCard(
                        name = "비스타",
                        image = painterResource(R.drawable.img_poster),
                        time = "19:30~"
                    )
                    Spacer(Modifier.size(6.dp))
                }
            }
        }
    }
}
