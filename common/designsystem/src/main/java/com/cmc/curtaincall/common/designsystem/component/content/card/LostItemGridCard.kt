package com.cmc.curtaincall.common.designsystem.component.content.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Pearl
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

enum class LostItemType(
    val label: String,
    @DrawableRes val drawableRes: Int
) {
    BAG("가방", R.drawable.ic_bag), WALLET("지갑", R.drawable.ic_wallet), MONEY("현금", R.drawable.ic_money),
    CARD("카드", R.drawable.ic_card), JEWELRY("귀금속", R.drawable.ic_jewelry), ELECTRONICS("전자기기", R.drawable.ic_electronics),
    BOOKS("도서", R.drawable.ic_books), CLOTHES("의류", R.drawable.ic_clothes), ETC("기타", R.drawable.ic_etc)
}

@Composable
fun LostItemGridCard(
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
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.BAG) },
                    name = LostItemType.BAG.label,
                    painter = painterResource(LostItemType.BAG.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.WALLET) },
                    name = LostItemType.WALLET.label,
                    painter = painterResource(LostItemType.WALLET.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.MONEY) },
                    name = LostItemType.MONEY.label,
                    painter = painterResource(LostItemType.MONEY.drawableRes)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.CARD) },
                    name = LostItemType.CARD.label,
                    painter = painterResource(LostItemType.CARD.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.JEWELRY) },
                    name = LostItemType.JEWELRY.label,
                    painter = painterResource(LostItemType.JEWELRY.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.ELECTRONICS) },
                    name = LostItemType.ELECTRONICS.label,
                    painter = painterResource(LostItemType.ELECTRONICS.drawableRes)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.BOOKS) },
                    name = LostItemType.BOOKS.label,
                    painter = painterResource(LostItemType.BOOKS.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.CLOTHES) },
                    name = LostItemType.CLOTHES.label,
                    painter = painterResource(LostItemType.CLOTHES.drawableRes)
                )
                LostItemGridItem(
                    modifier = itemModifier.clickable { onTypeChange(LostItemType.ETC) },
                    name = LostItemType.ETC.label,
                    painter = painterResource(LostItemType.ETC.drawableRes)
                )
            }
        }
    }
}

@Composable
fun LostItemGridItem(
    modifier: Modifier = Modifier,
    name: String,
    painter: Painter
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Cultured, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
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
