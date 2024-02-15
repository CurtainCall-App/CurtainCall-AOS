package com.cmc.curtaincall.common.designsystem.custom.show

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.custom.CurtainCallRatingBar
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey6
import com.cmc.curtaincall.common.utility.extensions.convertUIDate
import com.cmc.curtaincall.domain.model.review.ShowReviewModel

@Composable
fun ShowReviewEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_review_empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.empty_show_review),
            modifier = Modifier.padding(top = 12.dp),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold,
                color = CurtainCallTheme.colors.primary
            )
        )
    }
}

@Composable
fun ShowReviewListEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_review_empty),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.show_review_empty_list_content),
            modifier = Modifier.padding(top = 12.dp),
            style = CurtainCallTheme.typography.body3.copy(
                fontWeight = FontWeight.SemiBold,
                color = CurtainCallTheme.colors.primary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ShowReviewContent(
    modifier: Modifier = Modifier,
    showReviewModel: ShowReviewModel
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = CurtainCallTheme.colors.background
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp, bottom = 10.dp)
        ) {
            CurtainCallRatingBar(
                modifier = Modifier.size(84.dp, 16.dp),
                rating = showReviewModel.grade
            )
            Text(
                text = showReviewModel.content,
                modifier = Modifier.padding(top = 10.dp),
                style = CurtainCallTheme.typography.body4,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = String.format(
                    "%s | %s",
                    showReviewModel.creatorNickname,
                    showReviewModel.createdAt.convertUIDate()
                ),
                modifier = Modifier.padding(top = 10.dp),
                style = CurtainCallTheme.typography.body4.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Grey6
                )
            )
        }
    }
}

@Preview
@Composable
private fun ShowReviewEmptyContentPreview() {
    CurtainCallTheme {
        ShowReviewEmptyContent()
    }
}

@Preview
@Composable
private fun ShowReviewContentPreview() {
    CurtainCallTheme {
        ShowReviewContent(
            modifier = Modifier.fillMaxWidth(),
            showReviewModel = ShowReviewModel(
                content = "리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰리뷰",
                grade = 3,
                creatorNickname = "ows3090",
                createdAt = "2023-08-31T03:28:00"
            )
        )
    }
}
