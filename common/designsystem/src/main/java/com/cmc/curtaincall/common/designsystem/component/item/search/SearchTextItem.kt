package com.cmc.curtaincall.common.designsystem.component.item.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Arsenic
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
fun SearchTextItem(
    modifier: Modifier = Modifier,
    text: String,
    onDelete: () -> Unit = {},
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() },
            color = Arsenic,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Icon(
            painter = painterResource(R.drawable.ic_search_close),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .clickable { onDelete() },
            tint = Silver_Sand
        )
    }
}

@Preview
@Composable
private fun SearchTextItemPreview() {
    CurtainCallTheme {
        SearchTextItem(
            modifier = Modifier.fillMaxWidth(),
            text = "커튼콜",
            onClick = {}
        )
    }
}
