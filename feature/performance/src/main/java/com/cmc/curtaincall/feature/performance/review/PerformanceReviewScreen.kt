package com.cmc.curtaincall.feature.performance.review

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.dialog.CurtainCallBasicDialog
import com.cmc.curtaincall.common.design.component.items.ReviewDetailItem
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceReviewScreen(
    onNavigateReviewCreate: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

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
    var isShowRemoveDialog by remember { mutableStateOf(false) }

    if (isShowRemoveDialog) {
        CurtainCallBasicDialog(
            title = stringResource(R.string.dialog_performance_review_remove_title),
            description = stringResource(R.string.dialog_performance_review_remove_description),
            dismissText = stringResource(R.string.dialog_performance_review_remove_dismiss),
            positiveText = stringResource(R.string.dialog_performance_review_remove_positive),
            onDismiss = { isShowRemoveDialog = false }
        )
    }

    LazyColumn(
        modifier = modifier
            .padding(top = 13.dp)
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(List(10) {}) { index, item ->
            ReviewDetailItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                painter = painterResource(R.drawable.img_profile),
                rating = 4,
                name = "이디야커피맛없서",
                date = "2023.6.24",
                comment = "고전연극은 처음인데 엄청 재미있게 봤어요!",
                numberOfLike = 37,
                isMyWriting = true,
                onRemoveWriting = { isShowRemoveDialog = true }
            )
            if (index < 9) {
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
