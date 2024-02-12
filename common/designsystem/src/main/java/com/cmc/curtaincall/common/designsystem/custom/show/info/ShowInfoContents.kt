package com.cmc.curtaincall.common.designsystem.custom.show.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey2
import com.cmc.curtaincall.common.designsystem.theme.Grey9

@Composable
fun ShowInfoContent(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Row(
        modifier = modifier
            .background(Grey9, RoundedCornerShape(6.dp))
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Top)
                .background(CurtainCallTheme.colors.primary, RoundedCornerShape(4.dp))
                .widthIn(min = 61.dp)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = CurtainCallTheme.typography.body4.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = CurtainCallTheme.colors.onPrimary
                )
            )
        }
        Text(
            text = content,
            modifier = Modifier.padding(start = 12.dp),
            style = CurtainCallTheme.typography.body4.copy(
                color = Grey2
            ),
            lineHeight = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowInfoContentPreview() {
    CurtainCallTheme {
        Column(
            Modifier.background(Black).height(100.dp)
        ) {
            ShowInfoContent(
                modifier = Modifier.fillMaxWidth(),
                title = "공연기간",
                content = "월 | 13:00, 15:00, 20:00\n화, 수, 목, 금 | 15:00, 20:00\n토 | 14:00, 19:00"
            )
        }
    }
}
