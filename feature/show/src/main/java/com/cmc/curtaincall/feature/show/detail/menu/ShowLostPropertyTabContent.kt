package com.cmc.curtaincall.feature.show.detail.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.custom.show.LostPropertyContent
import com.cmc.curtaincall.common.designsystem.custom.show.LostPropertyEmptyContent
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel

@Composable
fun ShowLostPropertyTabContent(
    modifier: Modifier = Modifier,
    lostProperties: List<LostPropertyModel> = listOf()
) {
    Column(modifier.heightIn(min = 285.dp)) {
        if (lostProperties.isEmpty()) {
            Spacer(Modifier.weight(60f))
            LostPropertyEmptyContent(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.weight(60f))
            CurtainCallFilledButton(
                text = stringResource(R.string.lost_property_all_view),
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                onClick = {}
            )
        } else {
            Text(
                text = stringResource(R.string.lost_property_title),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 22.dp),
                style = CurtainCallTheme.typography.subTitle4
            )
            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(lostProperties) { lostPropertyModel ->
                    LostPropertyContent(
                        modifier = Modifier.size(135.dp, 180.dp),
                        lostPropertyModel = lostPropertyModel
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            CurtainCallFilledButton(
                text = stringResource(R.string.lost_property_all_view),
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 30.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                onClick = {}
            )
        }
    }
}
