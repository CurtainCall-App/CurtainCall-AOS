package com.cmc.curtaincall.feature.performance.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.custom.RatingBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceReviewCreateScreen(
    fromMyPage: Boolean = false,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Black)

    Column(Modifier.fillMaxSize()) {
        PerformanceReviewCreateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(331.dp)
                .background(Cetacean_Blue.copy(0.8f)),
            fromMyPage = fromMyPage,
            onBack = onBack
        )
        PerformanceReviewCreateBody(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun PerformanceReviewCreateHeader(
    modifier: Modifier = Modifier,
    fromMyPage: Boolean,
    onBack: () -> Unit = {}
) {
    Box(modifier) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(50.dp)
                .alpha(0.3f),
            contentScale = ContentScale.FillBounds
        )
        TopAppBarWithBack(
            title = if (fromMyPage) {
                stringResource(R.string.mypage_writing_review_edit)
            } else {
                stringResource(R.string.performance_review)
            },
            containerColor = Color.Transparent,
            contentColor = White,
            onClick = onBack
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 78.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.img_poster),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(120 / 159f),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(Me_Pink, RoundedCornerShape(20.dp))
                    .padding(vertical = 4.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "뮤지컬",
                    color = White,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Text(
                text = "비스티",
                modifier = Modifier.padding(top = 8.dp),
                color = White,
                fontSize = 18.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@Composable
private fun PerformanceReviewCreateBody(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ReviewCreateBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .weight(1f)
        )
        CurtainCallRoundedTextButton(
            onClick = {},
            modifier = Modifier
                .padding(bottom = 19.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            title = stringResource(R.string.performance_review_create_write_complete),
            fontSize = 16.dp.toSp(),
            containerColor = Me_Pink,
            contentColor = White
        )
    }
}

@Composable
private fun ReviewCreateBody(
    modifier: Modifier = Modifier
) {
    var reviewText by remember { mutableStateOf("") }
    var ratingState by remember { mutableStateOf(0) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.performance_review_create_satisfaction_question),
            modifier = Modifier.padding(top = 40.dp),
            color = Chinese_Black,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(67.dp)
                .background(Cultured, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            RatingBar(
                modifier = Modifier.size(40.dp),
                rating = ratingState,
                tint = Me_Pink,
                canChange = true,
                onChangeRate = { ratingState = it }
            )
        }
        Text(
            text = stringResource(R.string.performance_review_create_request),
            modifier = Modifier.padding(top = 40.dp),
            color = Chinese_Black,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        CurtainCallMultiLineTextField(
            value = reviewText,
            onValueChange = {
                if (reviewText.length < 30) {
                    reviewText = it
                }
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(62.dp),
            fontSize = 15.dp.toSp(),
            containerColor = Cultured,
            shape = RoundedCornerShape(10.dp),
            contentColor = Nero,
            contentModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            placeholder = stringResource(R.string.performance_review_create_request_placeholder),
            placeholderColor = Silver_Sand
        )
        Text(
            text = String.format(
                stringResource(R.string.performance_review_create_write_number_of_text),
                reviewText.length
            ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 10.dp),
            color = Roman_Silver,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
