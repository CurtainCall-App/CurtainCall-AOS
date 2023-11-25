package com.cmc.curtaincall.common.design.component.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun NoticeItem(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    isNew: Boolean = false,
    notRead: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(if (notRead) Cultured else White)
            .clickable { onClick() }
            .padding(horizontal = 20.dp)
            .padding(vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = Chinese_Black,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (isNew) {
                Icon(
                    painter = painterResource(R.drawable.ic_new),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .size(16.dp),
                    tint = Color.Unspecified
                )
            }
        }
        Text(
            text = date,
            modifier = Modifier.padding(top = 4.dp),
            color = Roman_Silver,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
