package com.cmc.curtaincall.feature.mypage.write

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.component.dialog.CurtainCallBasicDialog
import com.cmc.curtaincall.common.designsystem.component.item.MyLostItem
import com.cmc.curtaincall.common.designsystem.component.item.MyReviewWriteItem
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.common.utility.extensions.toTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageWriteScreen(
    myPageWriteModel: MyPageWriteViewModel = hiltViewModel(),
    onNavigateReviewEdit: (String, Int) -> Unit,
    onNavigateLostItemEdit: (Int, String, String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_my_writing),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageWriteContent(
            myPageWriteModel = myPageWriteModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onNavigateReviewEdit = onNavigateReviewEdit,
            onNavigateLostItemEdit = onNavigateLostItemEdit
        )
    }
}

@Composable
private fun MyPageWriteContent(
    myPageWriteModel: MyPageWriteViewModel,
    modifier: Modifier = Modifier,
    onNavigateReviewEdit: (String, Int) -> Unit,
    onNavigateLostItemEdit: (Int, String, String) -> Unit
) {
    LaunchedEffect(myPageWriteModel) {
        myPageWriteModel.loadLostItems()
        myPageWriteModel.loadReviewItems()
    }

    val writeTypeState by myPageWriteModel.writeTypeState.collectAsStateWithLifecycle()
    val myReviewItems = myPageWriteModel.reviewItems.collectAsLazyPagingItems()
    val lostItems = myPageWriteModel.lostItems.collectAsLazyPagingItems()

    var removeIndex by remember { mutableIntStateOf(-1) }
    var clickMoreVertIndex by remember { mutableIntStateOf(-1) }
    var isShowRemoveDialog by remember { mutableStateOf(false) }

    if (isShowRemoveDialog) {
        CurtainCallBasicDialog(
            title = stringResource(R.string.dialog_performance_review_remove_title),
            dismissText = stringResource(R.string.dialog_performance_review_remove_dismiss),
            positiveText = stringResource(R.string.dialog_performance_review_remove_positive),
            onDismiss = { isShowRemoveDialog = false },
            onPositive = {
                if (writeTypeState == WriteType.REVIEW) {
                    myPageWriteModel.deleteShowReview(removeIndex)
                    myPageWriteModel.loadReviewItems()
                } else {
                    myPageWriteModel.deleteLostItem(removeIndex)
                    myPageWriteModel.loadLostItems()
                }
                isShowRemoveDialog = false
            }
        )
    }

    Column(modifier) {
        Column(Modifier.padding(horizontal = 20.dp)) {
            MyPageWriteMenuTab(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                writeType = writeTypeState,
                onChangeWriteType = { myPageWriteModel.changeWriteType(it) }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (writeTypeState == WriteType.REVIEW) {
                itemsIndexed(myReviewItems) { index, memberReviewModel ->
                    memberReviewModel?.let { reviewModel ->
                        MyReviewWriteItem(
                            modifier = Modifier.fillMaxWidth(),
                            title = reviewModel.showName,
                            rating = reviewModel.grade,
                            description = reviewModel.content,
                            createdAtDate = reviewModel.createdAt.toChangeFullDate(),
                            createAtTime = reviewModel.createdAt.toTime(),
                            isClickMoreVert = clickMoreVertIndex == index,
                            onClickMoreVert = { check -> clickMoreVertIndex = if (check) index else -1 },
                            onChangeWriting = {
                                onNavigateReviewEdit(
                                    reviewModel.showId,
                                    reviewModel.id
                                )
                            },
                            onRemoveWriting = {
                                removeIndex = reviewModel.id
                                isShowRemoveDialog = true
                            }
                        )
                    }
                }
            } else {
                itemsIndexed(lostItems) { index, lostItemModel ->
                    lostItemModel?.let { lostItem ->
                        MyLostItem(
                            modifier = Modifier.fillMaxWidth(),
                            title = lostItem.title,
                            itemImageUrl = lostItem.imageUrl,
                            findLocation = lostItem.facilityName,
                            findDate = lostItem.foundDate,
                            createdAtDate = lostItem.createdAt.toChangeFullDate(),
                            createdAtTime = lostItem.createdAt.toTime(),
                            isClickMoreVert = clickMoreVertIndex == index,
                            onClickMoreVert = { check -> clickMoreVertIndex = if (check) index else -1 },
                            onChangeWriting = {
                                onNavigateLostItemEdit(
                                    lostItem.id,
                                    lostItem.facilityId,
                                    lostItem.facilityName
                                )
                            },
                            onRemoveWriting = {
                                removeIndex = lostItem.id
                                isShowRemoveDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MyPageWriteMenuTab(
    modifier: Modifier,
    writeType: WriteType,
    onChangeWriteType: (WriteType) -> Unit
) {
    Row(modifier) {
        Box(
            modifier = Modifier
                .background(
                    if (writeType == WriteType.REVIEW) Cetacean_Blue else Cultured,
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable { onChangeWriteType(WriteType.REVIEW) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = WriteType.REVIEW.value,
                color = if (writeType == WriteType.REVIEW) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .background(
                    if (writeType == WriteType.LOST_ITEM) Cetacean_Blue else Cultured,
                    RoundedCornerShape(20.dp)
                )
                .padding(vertical = 8.dp, horizontal = 14.dp)
                .clickable { onChangeWriteType(WriteType.LOST_ITEM) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = WriteType.LOST_ITEM.value,
                color = if (writeType == WriteType.LOST_ITEM) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
