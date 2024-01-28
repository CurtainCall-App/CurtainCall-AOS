package com.cmc.curtaincall.common.designsystem.custom.poster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey1
import com.cmc.curtaincall.domain.type.ShowGenreType

// 120 x 221
@Composable
fun CurtainCallPopularPoster(
    modifier: Modifier = Modifier,
    model: Any?,
    text: String,
    rank: Int = 1,
    genreType: ShowGenreType = ShowGenreType.PLAY
) {
    Column(modifier.size(120.dp, 221.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(120 / 160f)
        ) {
            AsyncImage(
                model = model,
                contentDescription = text,
                error = painterResource(R.drawable.ic_error_poster),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .background(Grey1.copy(alpha = 0.6f), RoundedCornerShape(topStart = 10.dp, bottomEnd = 5.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rank.toString(),
                    style = CurtainCallTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = CurtainCallTheme.colors.onPrimary
                    )
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(CurtainCallTheme.colors.primary, RoundedCornerShape(20.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = genreType.value,
                style = CurtainCallTheme.typography.caption.copy(
                    color = CurtainCallTheme.colors.onPrimary
                )
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(top = Paddings.medium),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
