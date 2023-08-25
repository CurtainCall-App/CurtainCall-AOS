package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    onNavigateColor: () -> Unit,
    onNavigateIcons: () -> Unit,
    onNavigateTypography: () -> Unit,
    onNavigateAlert: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateText: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateCalendar: () -> Unit,
    onNavigateRatingBar: () -> Unit,
    onNavigateTimePicker: () -> Unit,
    onNavigateDialog: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "CurtainCall Design System",
                        color = Color.White,
                        fontSize = 17.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(Cetacean_Blue)
                    .padding(top = 20.dp, bottom = 10.dp),
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Cetacean_Blue)
            )
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue),
            onNavigateColor = { /*TODO*/ },
            onNavigateIcons = { /*TODO*/ },
            onNavigateTypography = { /*TODO*/ },
            onNavigateAlert = { /*TODO*/ },
            onNavigateButton = { /*TODO*/ },
            onNavigateText = { /*TODO*/ },
            onNavigateTextField = { /*TODO*/ },
            onNavigateAppbar = { /*TODO*/ },
            onNavigateCard = { /*TODO*/ },
            onNavigateBottomSheet = { /*TODO*/ },
            onNavigateCalendar = { /*TODO*/ },
            onNavigateRatingBar = { /*TODO*/ },
            onNavigateTimePicker = { /*TODO*/ },
            onNavigateDialog = {}
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onNavigateColor: () -> Unit,
    onNavigateIcons: () -> Unit,
    onNavigateTypography: () -> Unit,
    onNavigateAlert: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateText: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateCalendar: () -> Unit,
    onNavigateRatingBar: () -> Unit,
    onNavigateTimePicker: () -> Unit,
    onNavigateDialog: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Column(modifier) {
    }
}
