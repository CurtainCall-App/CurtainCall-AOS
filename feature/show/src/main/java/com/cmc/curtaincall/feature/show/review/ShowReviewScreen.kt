package com.cmc.curtaincall.feature.show.review

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.cmc.curtaincall.common.designsystem.component.dialogs.ConfirmDialog
import com.cmc.curtaincall.common.designsystem.component.divider.HorizontalDivider
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
        showReviewViewModel.checkMyReview(showId)
    }

    val showReviewUiState by showReviewViewModel.uiState.collectAsStateWithLifecycle()
    var existedReviewPopup by remember { mutableStateOf(false) }

    if (existedReviewPopup) {
        ConfirmDialog(
            title = "이미 공연 리뷰를 등록했어요!",
            actionText = "확인",
            onAction = { existedReviewPopup = false },
            onDismiss = { existedReviewPopup = false }
        )
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
                onClick = {
                    if (showReviewUiState.hasMyReview) {
                        existedReviewPopup = true
                    } else {
                        onNavigateToReviewCreate(DEFAULT_REVIEW_ID)
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        ShowReviewContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Grey9),
            showId = showId,
            reviewCount = reviewCount,
            onNavigateToReviewCreate = onNavigateToReviewCreate
        )
    }
}

@Composable
private fun ShowReviewContent(
    modifier: Modifier = Modifier,
    showReviewViewModel: ShowReviewViewModel = hiltViewModel(),
    showId: String,
    reviewCount: Int,
    onNavigateToReviewCreate: (Int) -> Unit = {},
) {
    val showReviewUiState by showReviewViewModel.uiState.collectAsStateWithLifecycle()
    val showReviewModels = showReviewUiState.showReviewModels.collectAsLazyPagingItems()
    val memberId = showReviewUiState.memberId
    var showMenu by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(showReviewViewModel) {
        showReviewViewModel.effects.collect { effects ->
            when (effects) {
                ShowReviewSideEffect.RefreshShowReview -> {
                    showReviewModels.refresh()
                }

                ShowReviewSideEffect.CreateMyReview -> {
                    lazyListState.animateScrollToItem(0)
                }

                ShowReviewSideEffect.DeleteMyReview -> {
                    showReviewViewModel.fetchShowReviewList(showId)
                }
            }
        }
    }

    Column(modifier.clickable { showMenu = false }) {
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
        if (showReviewModels.itemCount == 0) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .background(color = CurtainCallTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(179.dp))
                ShowReviewListEmptyContent()
                Spacer(Modifier.height(319.dp))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .background(color = CurtainCallTheme.colors.background),
                state = lazyListState
            ) {
                items(showReviewModels.itemCount) { index ->
                    showReviewModels[index]?.let { showReviewModel ->
                        ShowReviewItemContent(
                            modifier = Modifier.fillMaxWidth(),
                            showReviewModel = showReviewModel,
                            isMyReview = memberId == showReviewModel.creatorId,
                            showMenu = memberId == showReviewModel.creatorId && showMenu,
                            isFavorite = showReviewModel.isFavorite,
                            onMoreClick = { showMenu = !showMenu },
                            onLikeClick = {
                                showReviewViewModel.selectLikeReview(
                                    reviewId = showReviewModel.id,
                                    isFavorite = !showReviewModel.isFavorite
                                )
                            },
                            onEditClick = {
                                onNavigateToReviewCreate(showReviewModel.id)
                            },
                            onDeleteClick = {
                                showReviewViewModel.deleteShowReview(
                                    showId = showId,
                                    reviewId = showReviewModel.id
                                )
                            }
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        height = if (index + 1 < showReviewModels.itemCount) 10.dp else 101.dp,
                        background = if (index + 1 < showReviewModels.itemCount) Grey9 else CurtainCallTheme.colors.background
                    )
                }
            }
        }
    }
}
