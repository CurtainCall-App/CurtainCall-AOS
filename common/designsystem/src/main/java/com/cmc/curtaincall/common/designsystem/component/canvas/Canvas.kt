package com.cmc.curtaincall.common.designsystem.component.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey1

@Composable
fun CurtainCallCoachMark(
    text: String,
    onClick: () -> Unit = {}
) {
    Column(Modifier.width(277.dp)) {
        Canvas(
            modifier = Modifier
                .padding(start = 240.dp)
                .size(10.dp, 5.dp)
        ) {
            drawPath(
                color = Grey1,
                path = Path().apply {
                    moveTo(5.dp.toPx(), 0.dp.toPx())
                    lineTo(0.dp.toPx(), 6.dp.toPx())
                    lineTo(10.dp.toPx(), 6.dp.toPx())
                }
            )
        }
        Box(
            modifier = Modifier
                .size(277.dp, 39.dp)
                .background(Grey1, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    style = CurtainCallTheme.typography.body5.copy(
                        color = CurtainCallTheme.colors.onPrimary
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.ic_white_close),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(14.dp)
                        .clickable { onClick() },
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 100)
@Composable
private fun CurtainCallCoachMarkPreview() {
    CurtainCallTheme {
        CurtainCallCoachMark(
            text = "인기순은 현재 상영 중인 작품 50개만 볼 수 있어요!"
        )
    }
}
