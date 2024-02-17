package com.cmc.curtaincall.feature.show.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.appbars.CurtainCallCenterTopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.custom.show.ShowReviewItemContent
import com.cmc.curtaincall.common.designsystem.custom.show.ShowReviewListEmptyContent
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey9
import com.cmc.curtaincall.common.navigation.destination.DEFAULT_REVIEW_ID
import com.cmc.curtaincall.domain.type.ReportType
import com.cmc.curtaincall.domain.type.ReviewSortType

@Composable
internal fun ShowReviewScreen(
    showReviewViewModel: ShowReviewViewModel = hiltViewModel(),
    showId: String?,
    reviewCount: Int?,
    onNavigateToReviewCreate: (Int) -> Unit = {},
    onNavigateReport: (Int, ReportType) -> Unit = { _, _ -> },
    onBack: () -> Unit = {}
) {
    requireNotNull(showId)
    requireNotNull(reviewCount)

    LaunchedEffect(Unit) {
        showReviewViewModel.fetchShowReviewList(showId)
    }

    SystemUiStatusBar(Grey9)
    Scaffold(
        topBar = {
            CurtainCallCenterTopAppBarWithBack(
                title = stringResource(R.string.show_review_title),
                containerColor = Grey9,
                contentColor = Black,
                onBack = onBack
            )
        },
        floatingActionButton = {
            CurtainCallFilledButton(
                text = stringResource(R.string.write_review),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                textStyle = CurtainCallTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                onClick = { onNavigateToReviewCreate(DEFAULT_REVIEW_ID) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        ShowReviewContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Grey9),
            reviewCount = reviewCount
        )
    }
}

@Composable
private fun ShowReviewContent(
    modifier: Modifier = Modifier,
    showReviewViewModel: ShowReviewViewModel = hiltViewModel(),
    reviewCount: Int
) {
    val showReviewModels = showReviewViewModel.showReviewModel.collectAsLazyPagingItems()
    val memberId by showReviewViewModel.memberId.collectAsStateWithLifecycle()
    var showMenu by remember { mutableStateOf(false) }
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (reviewCount == 0) {
                    stringResource(R.string.show_review_empty_list)
                } else {
                    String.format(stringResource(R.string.show_review_count_format), reviewCount)
                },
                modifier = Modifier.weight(1f),
                style = CurtainCallTheme.typography.subTitle4.copy(
                    color = CurtainCallTheme.colors.primary
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = ReviewSortType.RECENTLY.label,
                    style = CurtainCallTheme.typography.body3
                )
                Icon(
                    painter = painterResource(R.drawable.ic_down),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(12.dp),
                    tint = Color.Unspecified
                )
            }
        }
        if (reviewCount == 0) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .background(
                        color = CurtainCallTheme.colors.background,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(179.dp))
                ShowReviewListEmptyContent()
                Spacer(Modifier.height(319.dp))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(showReviewModels.itemCount) { index ->
                    showReviewModels[index]?.let { showReviewModel ->
                        ShowReviewItemContent(
                            modifier = Modifier.fillMaxWidth(),
                            showReviewModel = showReviewModel,
                            isMyReview = true,
                            showMenu = showMenu,
                            isFavorite = showReviewModel.isFavorite,
                            onMoreClick = { showMenu = !showMenu },
                            onLikeClick = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowReviewContent(
    modifier: Modifier = Modifier,
    showReviewViewModel: ShowReviewViewModel = hiltViewModel(),
    showId: String,
    onNavigateReport: (Int, ReportType) -> Unit,
    onNavigateReviewCreate: (Int) -> Unit
) {
//    val reviewItems = showReviewViewModel.reviewItems.collectAsLazyPagingItems()
//    var isShowRemoveDialog by remember { mutableStateOf(false) }
//    var removeReviewId by remember { mutableIntStateOf(0) }
//    var clickIndex by remember { mutableIntStateOf(-1) }
//
//    if (isShowRemoveDialog) {
//        CurtainCallBasicDialog(
//            title = stringResource(R.string.dialog_performance_review_remove_title),
//            description = stringResource(R.string.dialog_performance_review_remove_description),
//            dismissText = stringResource(R.string.dialog_performance_review_remove_dismiss),
//            positiveText = stringResource(R.string.dialog_performance_review_remove_positive),
//            onDismiss = { isShowRemoveDialog = false },
//            onPositive = {
//                showReviewViewModel.deleteShowReview(removeReviewId)
//                showReviewViewModel.requestShowReviewList(showId)
//                isShowRemoveDialog = false
//            }
//        )
//    }
//    if (reviewItems.itemCount == 0) {
//        EmptyContent(
//            modifier = Modifier.fillMaxSize(),
//            text = stringResource(R.string.performance_review_detail_empty)
//        )
//    } else {
//        LazyColumn(
//            modifier = modifier
//                .padding(top = 13.dp)
//                .padding(horizontal = 20.dp)
//        ) {
//            itemsIndexed(reviewItems) { index, reviewItem ->
//                reviewItem?.let { reviewItem ->
//                    ReviewDetailItem(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 20.dp),
//                        painter = painterResource(R.drawable.ic_default_profile),
//                        rating = reviewItem.grade,
//                        name = reviewItem.creatorNickname,
//                        date = reviewItem.createdAt.toChangeFullDate(),
//                        comment = reviewItem.content,
//                        numberOfLike = reviewItem.likeCount,
//                        isFavorite = reviewItem.isFavorite,
//                        isClickMoreVert = clickIndex == index,
//                        onClickMoreVert = { check -> clickIndex = if (check) index else -1 },
//                        onFavoriteChange = { check ->
//                            if (check) {
//                                showReviewViewModel.requestLikeReview(reviewItem.id)
//                            } else {
//                                showReviewViewModel.requestDislikeReview(reviewItem.id)
//                            }
//                        },
//                        isMyWriting = showReviewViewModel.memberId.value == reviewItem.creatorId,
//                        onChangeWriting = { onNavigateReviewCreate(reviewItem.id) },
//                        onRemoveWriting = {
//                            removeReviewId = reviewItem.id
//                            isShowRemoveDialog = true
//                        },
//                        onReport = { onNavigateReport(reviewItem.id, ReportType.SHOW_REVIEW) }
//                    )
//                    if (index < reviewItems.itemCount - 1) {
//                        Spacer(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(1.dp)
//                                .background(Bright_Gray)
//                        )
//                    }
//                }
//            }
//        }
//    }
}
