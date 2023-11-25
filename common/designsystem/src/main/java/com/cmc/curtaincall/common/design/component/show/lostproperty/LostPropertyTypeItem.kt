package com.cmc.curtaincall.common.design.component.show.lostproperty

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.enum.LostPropertyType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Anti_Flash_White
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun LostPropertyTypeItem(
    modifier: Modifier = Modifier,
    lostPropertyType: LostPropertyType,
    onTypeChange: (LostPropertyType) -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { onTypeChange(lostPropertyType) },
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(
            onClick = { onTypeChange(lostPropertyType) },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(containerColor = Anti_Flash_White)
        ) {
            Icon(
                painter = painterResource(lostPropertyType.drawableRes),
                contentDescription = lostPropertyType.label,
                tint = Color.Unspecified
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = lostPropertyType.label,
            modifier = Modifier.width(60.dp),
            color = Black_Pearl,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun LostPropertyTypeItemPreview() {
    LostPropertyTypeItem(
        modifier = Modifier.size(60.dp, 88.dp),
        lostPropertyType = LostPropertyType.BAG
    )
}
