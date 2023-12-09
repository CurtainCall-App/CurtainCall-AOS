package com.cmc.curtaincall.feature.performance.review

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.dialog.CurtainCallBasicDialog
import com.cmc.curtaincall.common.designsystem.component.items.EmptyItem
import com.cmc.curtaincall.common.designsystem.component.items.ReviewDetailItem
import com.cmc.curtaincall.common.designsystem.theme.*
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.feature.performance.detail.PerformanceDetailViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceReviewScreen(
    performanceDetailViewModel: PerformanceDetailViewModel = hiltViewModel(),
    showId: String,
    onNavigateReviewCreate: (String?, String, String, Boolean, Int) -> Unit,
    onNavigateReport: (Int, String) -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    LaunchedEffect(Unit) {
        performanceDetailViewModel.requestShowReviewList(showId)
    }

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.performance_review),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateReviewCreate(
                        performanceDetailViewModel.uiState.value.showDetailModel.poster,
                        performanceDetailViewModel.uiState.value.showDetailModel.genre,
                        performanceDetailViewModel.uiState.value.showDetailModel.name,
                        false,
                        Int.MIN_VALUE
                    )
                },
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
            performanceDetailViewModel = performanceDetailViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            showId = showId,
            onNavigateReviewCreate = onNavigateReviewCreate,
            onNavigateReport = onNavigateReport
        )
    }
}

@Composable
private fun PerformanceReviewContent(
    performanceReviewViewModel: PerformanceReviewViewModel = hiltViewModel(),
    performanceDetailViewModel: PerformanceDetailViewModel,
    modifier: Modifier = Modifier,
    showId: String,
    onNavigateReviewCreate: (String?, String, String, Boolean, Int) -> Unit,
    onNavigateReport: (Int, String) -> Unit
) {
    val reviewItems = performanceDetailViewModel.reviewItems.collectAsLazyPagingItems()
    var isShowRemoveDialog by remember { mutableStateOf(false) }
    var removeReviewId by remember { mutableIntStateOf(0) }
    var clickIndex by remember { mutableIntStateOf(-1) }
    if (isShowRemoveDialog) {
        CurtainCallBasicDialog(
            title = stringResource(R.string.dialog_performance_review_remove_title),
            description = stringResource(R.string.dialog_performance_review_remove_description),
            dismissText = stringResource(R.string.dialog_performance_review_remove_dismiss),
            positiveText = stringResource(R.string.dialog_performance_review_remove_positive),
            onDismiss = { isShowRemoveDialog = false },
            onPositive = {
                performanceReviewViewModel.deleteShowReview(removeReviewId)
                performanceDetailViewModel.requestShowReviewList(showId)
                isShowRemoveDialog = false
            }
        )
    }
    if (reviewItems.itemCount == 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            contentAlignment = Alignment.Center
        ) {
            EmptyItem(alert = stringResource(R.string.performance_review_detail_empty))
        }
    } else {
        LazyColumn(
            modifier = modifier
                .padding(top = 13.dp)
                .padding(horizontal = 20.dp)
        ) {
            itemsIndexed(reviewItems) { index, reviewItem ->
                reviewItem?.let { reviewItem ->
                    ReviewDetailItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        painter = painterResource(R.drawable.ic_default_profile),
                        rating = reviewItem.grade,
                        name = reviewItem.creatorNickname,
                        date = reviewItem.createdAt.toChangeFullDate(),
                        comment = reviewItem.content,
                        numberOfLike = reviewItem.likeCount,
                        isFavorite = reviewItem.isFavortie,
                        isClickMoreVert = clickIndex == index,
                        onClickMoreVert = { check ->
                            clickIndex = if (check) index else -1
                        },
                        onFavoriteChange = { check ->
                            if (check) {
                                performanceReviewViewModel.requestLikeReview(reviewItem.id)
                            } else {
                                performanceReviewViewModel.requestDislikeReview(reviewItem.id)
                            }
                        },
                        isMyWriting = performanceDetailViewModel.uiState.value.memberId == reviewItem.creatorId,
                        onChangeWriting = {
                            onNavigateReviewCreate(
                                performanceDetailViewModel.uiState.value.showDetailModel.poster,
                                performanceDetailViewModel.uiState.value.showDetailModel.genre,
                                performanceDetailViewModel.uiState.value.showDetailModel.name,
                                true,
                                reviewItem.id
                            )
                        },
                        onRemoveWriting = {
                            removeReviewId = reviewItem.id
                            isShowRemoveDialog = true
                        },
                        onReport = {
                            onNavigateReport(
                                reviewItem.id,
                                "SHOW_REVIEW"
                            )
                        }
                    )
                    if (index < reviewItems.itemCount - 1) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Bright_Gray)
                        )
                    }
                }
            }
        }
    }
}
