package com.cmc.curtaincall.common.design.component.show.lostproperty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.enum.LostPropertyType
import com.cmc.curtaincall.common.design.theme.White

private val LostPropertyTypeGridContentPadding = 23.dp
private val LostPropertyTypeGridBottomPadding = 10.dp
private val LostPropertyTypeGridRadius = 10.dp
private val LostPropertyTypeGridElevation = 15.dp
private const val LostPropertyTypeGridCellCount = 3

@Composable
fun LostPropertyTypeGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(LostPropertyTypeGridContentPadding),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable (LostPropertyType) -> Unit
) {
    Card(
        modifier = modifier.padding(bottom = LostPropertyTypeGridBottomPadding),
        shape = RoundedCornerShape(LostPropertyTypeGridRadius),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = LostPropertyTypeGridElevation)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(LostPropertyTypeGridCellCount),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = verticalArrangement,
            horizontalArrangement = horizontalArrangement,
            contentPadding = contentPadding
        ) {
            items(LostPropertyType.values().dropLast(1)) {
                content(it)
            }
        }
    }
}

@Preview
@Composable
private fun LostPropertyTypeGridPreview() {
    LostPropertyTypeGrid(
        modifier = Modifier.size(342.dp, 482.dp),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 23.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalArrangement = Arrangement.spacedBy(49.dp)
    ) {
        LostPropertyTypeItem(
            modifier = Modifier.size(60.dp, 88.dp),
            lostPropertyType = it,
            onTypeChange = {}
        )
    }
}
