package com.cmc.curtaincall.feature.show.lostproperty

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.content.empty.EmptyContent
import com.cmc.curtaincall.common.designsystem.component.item.LostItem
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel

@Composable
internal fun ShowLostPropertyMenuScreen(
    modifier: Modifier = Modifier,
    facilityId: String,
    facilityName: String,
    lostItems: List<LostPropertyModel> = listOf(),
    onNavigateLostProperty: (String, String) -> Unit = { _, _ -> }
) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.performance_find_lost_item),
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(14.dp))
                    .padding(vertical = 6.dp, horizontal = 10.dp)
                    .clickable { onNavigateLostProperty(facilityId, facilityName) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.performance_detail_show_all_review),
                    color = Me_Pink,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 14.dp)
                .heightIn(min = 317.dp)
        ) {
            if (lostItems.isEmpty()) {
                EmptyContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(317.dp),
                    text = stringResource(R.string.performance_lostitem_empty)
                )
            } else {
                lostItems.forEach { lostItem ->
                    LostItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        imageUrl = lostItem.imageUrl,
                        title = lostItem.title,
                        date = lostItem.foundDate,
                        getLocation = lostItem.facilityName,
                        storeLocation = lostItem.facilityName
                    )
                }
            }
        }
    }
}
