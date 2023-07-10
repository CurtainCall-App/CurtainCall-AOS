package com.cmc.curtaincall.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onNavigateHome: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.signup_terms),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                onClick = onBack
            )
        }
    ) { paddingValues ->
        SignUpContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
private fun SignUpContent(modifier: Modifier = Modifier) {
    var allTermsState by remember { mutableStateOf(false) }
    var serviceTermsState by remember { mutableStateOf(false) }
    var locationTermsState by remember { mutableStateOf(false) }
    var notificationTermsState by remember { mutableStateOf(false) }
    var marketingTermsState by remember { mutableStateOf(false) }

    Column(modifier.background(White)) {
        Text(
            text = stringResource(R.string.signup_terms_description),
            modifier = Modifier.padding(top = 30.dp, start = 22.dp),
            color = Chinese_Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            lineHeight = 34.sp
        )

        Text(
            text = stringResource(R.string.signup_terms_sub_description),
            modifier = Modifier.padding(top = 11.dp, start = 22.dp),
            color = Slate_Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )

        SignUpTerms(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp, start = 25.dp),
            description = stringResource(R.string.signup_terms_all_agree),
            checked = allTermsState,
            bold = true,
            onCheckedChange = {
                allTermsState = allTermsState.not()
                serviceTermsState = allTermsState
                locationTermsState = allTermsState
                notificationTermsState = allTermsState
                marketingTermsState = allTermsState
            }
        )

        Spacer(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 22.dp)
                .padding(top = 24.dp)
                .height(1.dp)
                .background(Anti_Flash_White)
        )

        SignUpTerms(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 25.dp, end = 22.dp),
            description = stringResource(R.string.signup_terms_service),
            checked = serviceTermsState,
            onCheckedChange = {
                serviceTermsState = serviceTermsState.not()
                allTermsState = listOf(marketingTermsState, locationTermsState, notificationTermsState, marketingTermsState).all { it }
            }
        )

        SignUpTerms(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 25.dp, end = 22.dp),
            description = stringResource(R.string.signup_terms_location),
            checked = locationTermsState,
            onCheckedChange = {
                locationTermsState = locationTermsState.not()
                allTermsState = listOf(marketingTermsState, locationTermsState, notificationTermsState, marketingTermsState).all { it }
            }
        )

        SignUpTerms(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 25.dp, end = 22.dp),
            description = stringResource(R.string.signup_terms_notification),
            checked = notificationTermsState,
            onCheckedChange = {
                notificationTermsState = notificationTermsState.not()
                allTermsState = listOf(marketingTermsState, locationTermsState, notificationTermsState, marketingTermsState).all { it }
            }
        )

        SignUpTerms(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 25.dp, end = 22.dp),
            description = stringResource(R.string.signup_terms_marketing),
            checked = marketingTermsState,
            onCheckedChange = {
                marketingTermsState = marketingTermsState.not()
                allTermsState = listOf(marketingTermsState, locationTermsState, notificationTermsState, marketingTermsState).all { it }
            }
        )
    }
}

@Composable
private fun SignUpTerms(
    modifier: Modifier = Modifier,
    description: String,
    checked: Boolean = false,
    bold: Boolean = false,
    onCheckedChange: () -> Unit = {},
    onClickDown: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCheckedChange,
            modifier = Modifier
                .background(if (checked) Me_Pink else Cultured, CircleShape)
                .size(20.dp, 20.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = description,
                modifier = Modifier.size(14.dp, 14.dp),
                tint = White
            )
        }

        Text(
            text = description,
            modifier = Modifier.padding(start = 12.dp),
            color = if (bold) Chinese_Black else Charcoal,
            fontSize = 16.sp,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )

        Spacer(Modifier.weight(1f))

        if (bold.not()) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = description,
                modifier = Modifier.size(24.dp, 24.dp)
                    .clickable { onClickDown() }
            )
        }
    }
}
