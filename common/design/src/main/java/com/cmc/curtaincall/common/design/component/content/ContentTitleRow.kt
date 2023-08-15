package com.cmc.curtaincall.common.design.component.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun ContentTitleRow(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 6.dp),
            color = Chinese_Black,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentTitleRowPreview() {
    ContentTitleRow(
        painter = painterResource(R.drawable.ic_gather),
        title = stringResource(R.string.home_my_gathering_tab)
    )
}
