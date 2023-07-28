package com.cmc.curtaincall.feature.performance.ui.review

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.RatingBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*

@Composable
internal fun PerformanceReviewTabScreen(
    modifier: Modifier = Modifier,
    onNavigateReview: () -> Unit
) {
    Column(modifier.padding(horizontal = 20.dp)) {
        Row {
            Text(
                text = "총 324개의 한 줄 리뷰",
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(71.dp, 28.dp)
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(14.dp))
                    .clickable { onNavigateReview() },
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
        Column(Modifier.padding(top = 14.dp, bottom = 20.dp)) {
            PerformanceReviewSimpleItem(Modifier.fillMaxWidth())
            PerformanceReviewSimpleItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            PerformanceReviewSimpleItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}

@Composable
private fun PerformanceReviewSimpleItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(BorderStroke(1.dp, Bright_Gray), RoundedCornerShape(10.dp))
            .padding(start = 12.dp, top = 14.dp, bottom = 16.dp)
    ) {
        RatingBar(rating = 4)
        Text(
            text = "zkvpzkvpzk | 2023.6.28",
            modifier = Modifier.padding(top = 6.dp),
            color = Black_Coral,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = "최고령 리어왕'으로 기네스북에 오를 예정이라시는~!",
            modifier = Modifier.padding(top = 12.dp),
            color = Nero,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 16.dp.toSp()
        )
    }
}
