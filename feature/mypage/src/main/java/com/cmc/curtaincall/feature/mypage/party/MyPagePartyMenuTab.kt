package com.cmc.curtaincall.feature.mypage.party

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
internal fun MyPagePartyMenuTab(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    onChangePartType: (PartyType) -> Unit
) {
    Row(modifier) {
        Box(
            modifier = Modifier
                .clickable { onChangePartType(PartyType.PERFORMANCE) }
                .background(
                    if (partyType == PartyType.PERFORMANCE) Cetacean_Blue else White,
                    RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = PartyType.PERFORMANCE.value,
                color = if (partyType == PartyType.PERFORMANCE) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable { onChangePartType(PartyType.MEAL) }
                .background(
                    if (partyType == PartyType.MEAL) Cetacean_Blue else White,
                    RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = PartyType.MEAL.value,
                color = if (partyType == PartyType.MEAL) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable { onChangePartType(PartyType.ETC) }
                .background(
                    if (partyType == PartyType.ETC) Cetacean_Blue else White,
                    RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = PartyType.ETC.value,
                color = if (partyType == PartyType.ETC) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
