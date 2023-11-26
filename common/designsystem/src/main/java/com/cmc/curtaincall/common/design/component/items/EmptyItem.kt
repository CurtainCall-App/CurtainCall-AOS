package com.cmc.curtaincall.common.design.component.items

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun EmptyItem(
    modifier: Modifier = Modifier,
    alert: String,
    painter: Painter = painterResource(R.drawable.ic_empty_item),
    iconColor: Color = Color.Unspecified,
    contentColor: Color = Silver_Sand
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = iconColor
        )
        Text(
            text = alert,
            modifier = Modifier.padding(top = 20.dp),
            color = contentColor,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmptyLiveTalk(
    modifier: Modifier,
    alert: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_fix),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            tint = Color.Unspecified
        )
        Text(
            text = alert,
            modifier = Modifier.padding(top = 20.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 26.dp.toSp(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmptyMyParty(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_dark_empty_item),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            modifier = Modifier.padding(top = 18.dp),
            color = Nero,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = subTitle,
            modifier = Modifier.padding(top = 10.dp),
            color = Black_Coral,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 23.dp.toSp(),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { onClick() }
                .border(1.dp, Black_Coral, RoundedCornerShape(30.dp))
                .padding(horizontal = 20.dp, vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_setting_empty),
                color = Black_Coral,
                fontSize = 15.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
