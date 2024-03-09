package com.cmc.curtaincall.common.designsystem.custom.show

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey3
import com.cmc.curtaincall.common.designsystem.theme.Grey5
import com.cmc.curtaincall.common.utility.extensions.convertDefaultDate
import com.cmc.curtaincall.domain.model.lostproperty.LostPropertyModel

@Composable
fun LostPropertyEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_lost_property_empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.empty_lost_property),
            modifier = Modifier.padding(top = 12.dp),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold,
                color = CurtainCallTheme.colors.primary
            )
        )
    }
}

@Composable
fun LostPropertyContent(
    modifier: Modifier = Modifier,
    lostPropertyModel: LostPropertyModel = LostPropertyModel(),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CurtainCallTheme.colors.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = lostPropertyModel.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(120 / 88f)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = lostPropertyModel.title,
                style = CurtainCallTheme.typography.body4.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = lostPropertyModel.facilityName,
                style = CurtainCallTheme.typography.body4.copy(
                    color = Grey3
                ),
                modifier = Modifier.padding(top = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = lostPropertyModel.foundDate.convertDefaultDate(),
                style = CurtainCallTheme.typography.body5.copy(
                    color = Grey5
                ),
                modifier = Modifier.padding(top = 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun LostPropertyEmptyContentPreview() {
    CurtainCallTheme {
        LostPropertyEmptyContent()
    }
}

@Preview
@Composable
private fun LostPropertyContentPreview() {
    CurtainCallTheme {
        LostPropertyContent(
            modifier = Modifier.size(136.dp, 180.dp),
            lostPropertyModel = LostPropertyModel(
                facilityName = "A홀 13B 2열 8석",
                foundDate = "2023-03-04",
                title = "분실물"
            )
        )
    }
}
