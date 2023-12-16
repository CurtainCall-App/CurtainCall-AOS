package com.cmc.curtaincall.common.designsystem.component.content.empty

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

private val EmptyIconDescription = "Empty"

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconRes: Int = R.drawable.ic_empty_item
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(70f))
        Icon(
            painter = painterResource(iconRes),
            contentDescription = EmptyIconDescription,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = text,
            modifier = Modifier.padding(top = 12.dp),
            color = Silver_Sand,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(90f))
    }
}

@Preview
@Composable
private fun EmptyContentPreview() {
    CurtainCallTheme {
        EmptyContent(
            modifier = Modifier.fillMaxSize(),
            text = "최근 검색 내역이 없습니다"
        )
    }
}
