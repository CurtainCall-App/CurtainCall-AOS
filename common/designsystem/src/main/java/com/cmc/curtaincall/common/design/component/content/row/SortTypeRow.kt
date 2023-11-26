package com.cmc.curtaincall.common.design.component.content.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.custom.SortType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun SortTypeRow(
    modifier: Modifier = Modifier,
    sortType: SortType,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sortType.value,
            color = Nero,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Icon(
            painter = painterResource(R.drawable.ic_sort),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp)
                .size(14.dp, 11.dp),
            tint = Color.Unspecified
        )
    }
}
