package com.cmc.curtaincall.feature.performance.lostitem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Anti_Flash_White
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

enum class LostItemType(
    val label: String,
    @DrawableRes val drawableRes: Int
) {
    BAG("가방", R.drawable.ic_bag), WALLET("지갑", R.drawable.ic_wallet), MONEY("현금", R.drawable.ic_money),
    CARD("카드", R.drawable.ic_card), JEWELRY("귀금속", R.drawable.ic_jewelry), ELECTRONICS("전자기기", R.drawable.ic_electronics),
    BOOKS("도서", R.drawable.ic_books), CLOTHES("의류", R.drawable.ic_clothes), ETC("기타", R.drawable.ic_etc)
}

@Composable
fun LostItemTypeGrid(
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier,
    onTypeChange: (LostItemType) -> Unit = {}
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
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.BAG) },
                    name = LostItemType.BAG.label,
                    icon = painterResource(LostItemType.BAG.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.WALLET) },
                    name = LostItemType.WALLET.label,
                    icon = painterResource(LostItemType.WALLET.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.MONEY) },
                    name = LostItemType.MONEY.label,
                    icon = painterResource(LostItemType.MONEY.drawableRes)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.CARD) },
                    name = LostItemType.CARD.label,
                    icon = painterResource(LostItemType.CARD.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.JEWELRY) },
                    name = LostItemType.JEWELRY.label,
                    icon = painterResource(LostItemType.JEWELRY.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.ELECTRONICS) },
                    name = LostItemType.ELECTRONICS.label,
                    icon = painterResource(LostItemType.ELECTRONICS.drawableRes)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.BOOKS) },
                    name = LostItemType.BOOKS.label,
                    icon = painterResource(LostItemType.BOOKS.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.CLOTHES) },
                    name = LostItemType.CLOTHES.label,
                    icon = painterResource(LostItemType.CLOTHES.drawableRes)
                )
                LostItemTypeCard(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.ETC) },
                    name = LostItemType.ETC.label,
                    icon = painterResource(LostItemType.ETC.drawableRes)
                )
            }
        }
    }
}

@Composable
fun LostItemTypeCard(
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
