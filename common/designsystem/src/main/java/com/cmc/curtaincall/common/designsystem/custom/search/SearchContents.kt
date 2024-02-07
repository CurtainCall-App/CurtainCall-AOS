package com.cmc.curtaincall.common.designsystem.custom.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey2
import com.cmc.curtaincall.common.designsystem.theme.Grey9

@Composable
fun SearchWordContent(
    modifier: Modifier = Modifier,
    text: String,
    onClose: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .background(Grey9, RoundedCornerShape(30.dp))
            .padding(horizontal = 12.dp)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f, false),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold,
                color = Grey2
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            painter = painterResource(R.drawable.ic_search_close),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(16.dp)
                .clickable { onClose(text) },
            tint = Color.Unspecified
        )
    }
}

@Composable
fun SearchWordEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_search_empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.empty_search_word),
            modifier = Modifier.padding(top = 12.dp),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold,
                color = CurtainCallTheme.colors.primary
            )
        )
    }
}

@Preview
@Composable
private fun SearchWordContentPreview() {
    CurtainCallTheme {
        SearchWordContent(text = "작품명작품명")
    }
}

@Preview
@Composable
private fun SearchWordEmptyContentPreview() {
    CurtainCallTheme {
        SearchWordEmptyContent()
    }
}
