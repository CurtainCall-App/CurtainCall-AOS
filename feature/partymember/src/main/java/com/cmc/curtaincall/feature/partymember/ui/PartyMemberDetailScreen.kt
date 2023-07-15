package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cmc.curtaincall.common.design.component.TopAppBarWithAction
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PartyMemberDetailScreen(
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            TopAppBarWithAction(
                title = "식사/카페",
                actionRes = R.drawable.ic_report,
                onBack = onBack,
                onClickAction = { /* TODO */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(paddingValues)
        ) {
        }
    }
}
