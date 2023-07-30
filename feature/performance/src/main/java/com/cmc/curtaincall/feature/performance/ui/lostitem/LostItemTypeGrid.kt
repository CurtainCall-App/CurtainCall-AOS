package com.cmc.curtaincall.feature.performance.ui.lostitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Anti_Flash_White
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun LostItemTypeGrid(
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_bag),
                    icon = painterResource(R.drawable.ic_bag)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_wallet),
                    icon = painterResource(R.drawable.ic_wallet)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_money),
                    icon = painterResource(R.drawable.ic_money)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_card),
                    icon = painterResource(R.drawable.ic_card)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_jewelry),
                    icon = painterResource(R.drawable.ic_jewelry)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_electronics),
                    icon = painterResource(R.drawable.ic_electronics)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_books),
                    icon = painterResource(R.drawable.ic_books)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_clothes),
                    icon = painterResource(R.drawable.ic_clothes)
                )
                LostItemType(
                    modifier = itemModifier,
                    name = stringResource(R.string.performance_find_lost_item_etc),
                    icon = painterResource(R.drawable.ic_etc)
                )
            }
        }
    }
}

@Composable
fun LostItemType(
    modifier: Modifier = Modifier,
    name: String,
    icon: Painter
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Anti_Flash_White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = name,
            color = Black_Pearl,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
