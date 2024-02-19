package com.cmc.curtaincall.feature.show.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.custom.RatingBar
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.common.navigation.destination.DEFAULT_REVIEW_ID
import com.cmc.curtaincall.feature.show.detail.ShowDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun ShowReviewCreateScreen(
    showDetailViewModel: ShowDetailViewModel,
    showReviewViewModel: ShowReviewViewModel = hiltViewModel(),
    showId: String?,
    reviewId: Int?,
    onBack: () -> Unit
) {
    requireNotNull(showId)
    requireNotNull(reviewId)

    val showDetailState by showDetailViewModel.uiState.collectAsStateWithLifecycle()

    SystemUiStatusBar(Black)
    Column(Modifier.fillMaxSize()) {
        ShowReviewCreateHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(331.dp)
                .background(Cetacean_Blue.copy(0.8f)),
            fromMyPage = reviewId == DEFAULT_REVIEW_ID,
            posterUrl = showDetailState.showDetailModel.poster,
            genre = showDetailState.showDetailModel.genre,
            title = showDetailState.showDetailModel.name,
            onBack = onBack
        )
        ShowReviewCreateBody(
            showReviewViewModel = showReviewViewModel,
            modifier = Modifier.fillMaxSize(),
            showId = showId,
            reviewId = reviewId,
            onBack = {
                onBack()
            }
        )
    }
}

@Composable
private fun ShowReviewCreateHeader(
    modifier: Modifier = Modifier,
    fromMyPage: Boolean = false,
    posterUrl: String? = null,
    genre: String = "",
    title: String = "",
    onBack: () -> Unit = {}
) {
    Box(modifier) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            error = painterResource(R.drawable.img_poster),
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
                stringResource(R.string.show_review_title)
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
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                error = painterResource(R.drawable.img_poster),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(120 / 159f)
                    .clip(RoundedCornerShape(12.dp)),
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
                    text = if (genre == "PLAY") "연극" else "뮤지컬",
                    color = White,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Text(
                text = title,
                modifier = Modifier
                    .width(228.dp)
                    .padding(top = 8.dp),
                color = White,
                fontSize = 18.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ShowReviewCreateBody(
    showReviewViewModel: ShowReviewViewModel,
    modifier: Modifier = Modifier,
    showId: String,
    reviewId: Int,
    onBack: () -> Unit,
) {
    LaunchedEffect(showReviewViewModel) {
        showReviewViewModel.effects.collectLatest {
            when (it) {
                ShowReviewSideEffect.CreateSuccess,
                ShowReviewSideEffect.UpdateSuccess,
                ShowReviewSideEffect.DeleteSuccess -> {
                    onBack()
                }
            }
        }
    }

    var rating by remember { mutableIntStateOf(0) }
    var review by remember { mutableStateOf("") }

    Column(modifier) {
        ShowReviewCreateBodyContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .weight(1f),
            rating = rating,
            review = review,
            onRatingChange = { rating = it },
            onReviewChange = { review = it }
        )
        CurtainCallRoundedTextButton(
            onClick = {
                if (reviewId == DEFAULT_REVIEW_ID) {
                    showReviewViewModel.createShowReview(showId, rating, review)
                } else {
                    showReviewViewModel.updateShowReview(reviewId, review, rating)
                }
            },
            modifier = Modifier
                .padding(bottom = 19.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            title = stringResource(R.string.performance_review_create_write_complete),
            fontSize = 16.dp.toSp(),
            enabled = review.isNotEmpty(),
            containerColor = Me_Pink,
            contentColor = White
        )
    }
}

@Composable
private fun ShowReviewCreateBodyContent(
    modifier: Modifier = Modifier,
    rating: Int,
    review: String,
    onRatingChange: (Int) -> Unit = {},
    onReviewChange: (String) -> Unit = {}
) {
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
                .height(67.dp),
            contentAlignment = Alignment.Center
        ) {
            RatingBar(
                modifier = Modifier.size(40.dp),
                rating = rating,
                tint = Me_Pink,
                canChange = true,
                onChangeRate = { onRatingChange(it) }
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
            value = review,
            onValueChange = { if (it.length <= 20) onReviewChange(it) },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(50.dp),
            fontSize = 15.dp.toSp(),
            containerColor = Cultured,
            shape = RoundedCornerShape(10.dp),
            contentColor = Nero,
            contentModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            placeholder = stringResource(R.string.performance_review_create_request_placeholder),
            placeholderColor = Silver_Sand
        )
        Text(
            text = String.format(
                stringResource(R.string.performance_review_create_write_number_of_text),
                review.length
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
