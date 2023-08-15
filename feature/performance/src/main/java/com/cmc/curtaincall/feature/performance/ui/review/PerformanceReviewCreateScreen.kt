package com.cmc.curtaincall.feature.performance.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
internal fun PerformanceReviewCreateScreen(
    fromMyPage: Boolean = false,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Dark_Slate_Grey)

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = if (fromMyPage) {
                    stringResource(R.string.mypage_writing_review_edit)
                } else {
                    stringResource(R.string.performance_review)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = Dark_Slate_Grey,
                contentColor = White,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        PerformanceReviewCreateContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}

@Composable
private fun PerformanceReviewCreateContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ReviewCreateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .background(Dark_Slate_Grey)
        )
        ReviewCreateBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(White)
        )
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 19.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Me_Pink)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.performance_review_create_write_complete),
                    color = White,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@Composable
private fun ReviewCreateHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.img_poster),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 33.dp)
                .size(80.dp, 106.dp)
        )
        Box(
            modifier = Modifier
                .padding(top = 14.dp)
                .size(54.dp, 23.dp)
                .background(Me_Pink, RoundedCornerShape(20.dp)),
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
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
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
                modifier = Modifier.size(42.dp),
                rating = ratingState,
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
        BasicTextField(
            value = reviewText,
            onValueChange = {
                if (reviewText.length < 30) {
                    reviewText = it
                }
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .heightIn(min = 62.dp)
                .background(Cultured, RoundedCornerShape(10.dp)),
            textStyle = TextStyle(
                color = Nero,
                fontSize = 15.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp()
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 12.dp)
                ) {
                    if (reviewText.isEmpty()) {
                        Text(
                            text = stringResource(R.string.performance_review_create_request_placeholder),
                            color = Roman_Silver,
                            fontSize = 15.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    innerTextField()
                }
            }
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
