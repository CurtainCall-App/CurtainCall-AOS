package com.cmc.curtaincall.common.designsystem.component.menus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.divider.HorizontalDivider
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey1
import com.cmc.curtaincall.common.designsystem.theme.Grey8

@Composable
fun CurtainCallMenu(
    modifier: Modifier = Modifier,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    Card(
        modifier = modifier.size(73.dp, 88.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CurtainCallTheme.colors.background
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onEdit() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.edit),
                style = CurtainCallTheme.typography.body3.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Grey1
                )
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            background = Grey8
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { onDelete() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.delete),
                style = CurtainCallTheme.typography.body3.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Grey1
                )
            )
        }
    }
}

@Preview
@Composable
private fun CurtainCallMenuPreview() {
    CurtainCallTheme {
        CurtainCallMenu()
    }
}
