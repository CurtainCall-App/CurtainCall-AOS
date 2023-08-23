package com.cmc.curtaincall.feature.performance.review

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.items.EmptyItem
import com.cmc.curtaincall.common.design.component.items.ReviewItem
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.domain.model.review.ShowReviewModel

@Composable
internal fun PerformanceReviewTabScreen(
    modifier: Modifier = Modifier,
    showId: String,
    reviewCount: Int = 0,
    showReviews: List<ShowReviewModel> = listOf(),
    onNavigateReview: (String) -> Unit
) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "총 ${reviewCount}개의 한 줄 리뷰",
                modifier = Modifier.weight(1f),
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(14.dp))
                    .padding(vertical = 6.dp, horizontal = 10.dp)
                    .clickable { onNavigateReview(showId) },
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
            if (showReviews.isEmpty()) {
                EmptyItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp),
                    alert = stringResource(R.string.performance_review_empty)
                )
            } else {
                showReviews.forEach { showReview ->
                    ReviewItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        rating = showReview.grade,
                        name = showReview.creatorNickname,
                        date = showReview.createdAt.toChangeDate(),
                        comment = showReview.content
                    )
                }
            }
        }
    }
}
