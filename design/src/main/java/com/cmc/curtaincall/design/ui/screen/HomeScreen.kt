package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.cmc.curtaincall.common.design.component.basic.CurtainCallBorderTextButton
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    onNavigateColor: () -> Unit,
    onNavigateIcons: () -> Unit,
    onNavigateTypography: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateCalendar: () -> Unit,
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
            onNavigateColor = onNavigateColor,
            onNavigateIcons = onNavigateIcons,
            onNavigateTypography = onNavigateTypography,
            onNavigateButton = onNavigateButton,
            onNavigateTextField = onNavigateTextField,
            onNavigateAppbar = onNavigateAppbar,
            onNavigateCard = onNavigateCard,
            onNavigateBottomSheet = onNavigateBottomSheet,
            onNavigateCalendar = onNavigateCalendar,
            onNavigateTimePicker = onNavigateTimePicker,
            onNavigateDialog = onNavigateDialog
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onNavigateColor: () -> Unit,
    onNavigateIcons: () -> Unit,
    onNavigateTypography: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateCalendar: () -> Unit,
    onNavigateTimePicker: () -> Unit,
    onNavigateDialog: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Foundation",
            modifier = Modifier.padding(top = 20.dp),
            color = White,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateColor() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "Colors",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            CurtainCallBorderTextButton(
                onClick = { onNavigateIcons() },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(42.dp),
                title = "Icons",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
        }

        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateTypography() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "Colors",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            Spacer(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
            )
        }

        Text(
            text = "Controls",
            modifier = Modifier.padding(top = 20.dp),
            color = White,
            fontSize = 17.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateButton() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "Button",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            CurtainCallBorderTextButton(
                onClick = { onNavigateTextField() },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(42.dp),
                title = "TextField",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
        }
        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateAppbar() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "Appbar",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            CurtainCallBorderTextButton(
                onClick = { onNavigateCard() },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(42.dp),
                title = "Card",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
        }
        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateBottomSheet() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "BottomSheet",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            CurtainCallBorderTextButton(
                onClick = { onNavigateCalendar() },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(42.dp),
                title = "Calendar",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
        }
        Row(Modifier.padding(top = 12.dp)) {
            CurtainCallBorderTextButton(
                onClick = { onNavigateTimePicker() },
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
                title = "TimePicker",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
            CurtainCallBorderTextButton(
                onClick = { onNavigateDialog() },
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
                    .height(42.dp),
                title = "Dialog",
                fontSize = 15.dp.toSp(),
                containerColor = Me_Pink,
                contentColor = White,
                borderColor = Color.Transparent,
                radiusSize = 8.dp
            )
        }
    }
}
