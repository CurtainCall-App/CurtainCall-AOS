package com.cmc.curtaincall.common.designsystem.component.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey6
import com.cmc.curtaincall.common.designsystem.theme.Grey9

private val BasicChipRadius = 30.dp
private val BasicChipHorizontalPadding = 11.dp
private val BasicChipVerticalPadding = 4.dp

@Composable
fun CurtainCallBasicChip(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle.Default,
    isSelect: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = if (isSelect) CurtainCallTheme.colors.primary else Grey9,
                shape = RoundedCornerShape(BasicChipRadius)
            ).padding(horizontal = BasicChipHorizontalPadding)
            .padding(vertical = BasicChipVerticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle.copy(
                color = if (isSelect) CurtainCallTheme.colors.onPrimary else Grey6,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
private fun CurtainCallBasicChipPreview() {
    CurtainCallTheme {
        CurtainCallBasicChip(
            text = "Label",
            isSelect = true
        )
    }
}
