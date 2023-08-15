package com.cmc.curtaincall.feature.performance.review

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.custom.RatingBar
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceReviewScreen(
    onNavigateReviewCreate: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Whisper)
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.performance_review),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateReviewCreate() },
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(58.dp),
                shape = CircleShape,
                containerColor = Cetacean_Blue
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pen),
                    contentDescription = null,
                    modifier = Modifier.size(29.dp),
                    tint = Color.Unspecified
                )
            }
        }
    ) { paddingValues ->
        PerformanceReviewContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun PerformanceReviewContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 33.dp)
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(List(10) {}) { index, item ->
            PerformanceReviewItem()
            if (index < 9) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .height(1.dp)
                        .background(Bright_Gray)
                )
            }
        }
    }
}

@Composable
private fun PerformanceReviewItem(
    modifier: Modifier = Modifier
) {
    var isClickThumbsUp by remember { mutableStateOf(false) }
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = null,
                modifier = Modifier.size(42.dp)
            )
            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    RatingBar(
                        modifier = Modifier.size(14.dp),
                        rating = 4
                    )
                    Text(
                        text = "이디야커피맛없서 | 2023.6.24",
                        modifier = Modifier.padding(top = 4.dp),
                        color = Nero,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(40.dp, 24.dp)
                        .border(BorderStroke(1.dp, Silver_Sand), RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.performance_review_repot),
                        color = Silver_Sand,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }

        Text(
            text = "고전연극은 처음인데 엄청 재미있게 봤어요!",
            modifier = Modifier.padding(top = 20.dp),
            color = Nero,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp()
        )

        Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .background(
                    if (isClickThumbsUp) Me_Pink else Ghost_White,
                    RoundedCornerShape(6.dp)
                )
                .clickable { isClickThumbsUp = isClickThumbsUp.not() }
                .padding(vertical = 4.dp, horizontal = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(
                    painter = painterResource(
                        if (isClickThumbsUp) R.drawable.ic_thumb_up_sel else R.drawable.ic_thumb_up
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "153",
                    modifier = Modifier.padding(start = 4.dp),
                    color = if (isClickThumbsUp) White else Cadet_Grey,
                    fontSize = 11.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}
