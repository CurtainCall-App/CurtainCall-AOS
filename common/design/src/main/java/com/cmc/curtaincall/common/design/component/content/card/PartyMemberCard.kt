package com.cmc.curtaincall.common.design.component.content.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun PartyMemberCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier.background(Cetacean_Blue, RoundedCornerShape(10.dp))
    ) {
        Column(Modifier.padding(top = 22.dp, start = 20.dp)) {
            Text(
                text = title,
                color = White,
                fontSize = 20.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = description,
                modifier = Modifier.padding(top = 6.dp),
                color = White,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 17.dp.toSp()
            )
        }
        content()
    }
}
