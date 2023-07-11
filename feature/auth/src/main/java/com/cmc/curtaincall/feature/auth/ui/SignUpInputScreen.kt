package com.cmc.curtaincall.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Slate_Gray
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignUpInputScreen(
    onNavigateHome: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.signup),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                onClick = onBack
            )
        }
    ) { paddingValues ->
        SignUpInputContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(White)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpInputContent(
    modifier: Modifier = Modifier
) {
    var nicknameState by remember { mutableStateOf("") }

    Column(modifier) {
        Text(
            text = stringResource(R.string.signup_input_description),
            modifier = Modifier.padding(top = 30.dp, start = 22.dp),
            color = Chinese_Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            lineHeight = 34.sp
        )

        Text(
            text = stringResource(R.string.signup_input_sub_description),
            modifier = Modifier.padding(top = 11.dp, start = 22.dp),
            color = Slate_Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 20.sp
        )
    }
}