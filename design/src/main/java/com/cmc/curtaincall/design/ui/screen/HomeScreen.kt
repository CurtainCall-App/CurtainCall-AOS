package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
    onNavigateIllustration: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCalendar: () -> Unit,
    onNavigateTimePicker: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateDialog: () -> Unit
) {
    Scaffold() { paddingValues ->
        HomeContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue),
            onNavigateColor = onNavigateColor,
            onNavigateIcons = onNavigateIcons,
            onNavigateTypography = onNavigateTypography,
            onNavigateIllustration = onNavigateIllustration,
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
    onNavigateIllustration: () -> Unit,
    onNavigateButton: () -> Unit,
    onNavigateCard: () -> Unit,
    onNavigateTextField: () -> Unit,
    onNavigateAppbar: () -> Unit,
    onNavigateCalendar: () -> Unit,
    onNavigateTimePicker: () -> Unit,
    onNavigateBottomSheet: () -> Unit,
    onNavigateDialog: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cetacean_Blue)

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "CURTAINCALL\n" + "DESIGN SYSTEM",
            modifier = Modifier.padding(top = 36.dp),
            color = Me_Pink,
            fontSize = 22.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = "Foundation",
            modifier = Modifier.padding(top = 38.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateColor() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Colors",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable { onNavigateIcons() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Icons",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateTypography() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Typography",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
                    .clickable { onNavigateIllustration() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Illustrations",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Text(
            text = "Controls",
            modifier = Modifier.padding(top = 40.dp),
            color = White,
            fontSize = 18.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateButton() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Button",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable { onNavigateCard() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Card",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateTextField() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Text Field",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable { onNavigateAppbar() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "App Bar",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateCalendar() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Calendar",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable { onNavigateTimePicker() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Time Picker",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateBottomSheet() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Bottom Sheet",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .clickable { onNavigateDialog() }
                    .background(White, RoundedCornerShape(6.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Dialog",
                    modifier = Modifier.fillMaxSize(),
                    color = Cetacean_Blue,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}
