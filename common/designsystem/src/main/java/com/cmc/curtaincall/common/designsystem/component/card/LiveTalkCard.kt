package com.cmc.curtaincall.common.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Red_Salsa
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
fun LiveTalkCard(
    modifier: Modifier = Modifier,
    startTime: String,
    endTime: String,
    posterUrl: String,
    name: String,
    facilityName: String,
    onClick: () -> Unit = {}
) {
    Column(modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(152 / 203f)
                .clip(RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                error = painterResource(R.drawable.ic_error_poster),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(152 / 203f)
                    .clip(RoundedCornerShape(15.dp))
                    .clickable { onClick() },
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .background(Red_Salsa, RoundedCornerShape(topStart = 15.dp, bottomEnd = 15.dp))
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$startTime~$endTime",
                    color = White,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Text(
            text = name,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            color = White,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = facilityName,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            color = White,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
