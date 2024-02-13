package com.cmc.curtaincall.common.designsystem.custom.show

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Composable
fun ShowReviewEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_review_empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.empty_show_review),
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
private fun ShowReviewEmptyContentPreview() {
    CurtainCallTheme {
        ShowReviewEmptyContent()
    }
}
