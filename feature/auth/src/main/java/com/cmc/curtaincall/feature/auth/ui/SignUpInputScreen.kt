package com.cmc.curtaincall.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.theme.*

private const val INPUT_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{0,15}$"
private const val INPUT_CHECK_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{6,15}$"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignUpInputScreen(
    onNavigateWelcome: () -> Unit,
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
                .background(White),
            onNavigateWelcome = onNavigateWelcome
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpInputContent(
    modifier: Modifier = Modifier,
    onNavigateWelcome: () -> Unit
) {
    var nicknameState by remember { mutableStateOf("") }
    var nicknameCheckState by remember { mutableStateOf(false) }

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

        TextField(
            value = nicknameState,
            onValueChange = {
                if (Regex(INPUT_REGEX).matches(it)) {
                    nicknameState = it
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp)
                .padding(horizontal = 22.dp)
                .height(57.dp),
            placeholder = {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.signup_input_nickname),
                        color = Cadet_Grey,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Cadet_Grey,
                containerColor = Cultured,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Button(
            onClick = {
                // TODO 서버 중복체크
                nicknameCheckState = true
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 15.dp, end = 22.dp)
                .wrapContentWidth()
                .height(30.dp),
            enabled = Regex(INPUT_CHECK_REGEX).matches(nicknameState),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState)) Me_Pink else Platinum,
                disabledContainerColor = Platinum
            )
        ) {
            Text(
                text = stringResource(R.string.signup_input_double_check),
                color = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState)) White else Cadet_Grey,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onNavigateWelcome,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 15.dp)
                .padding(horizontal = 22.dp)
                .height(52.dp),
            enabled = nicknameCheckState,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (nicknameCheckState) Me_Pink else Gainsboro,
                disabledContainerColor = Gainsboro
            )
        ) {
            Text(
                text = stringResource(R.string.signup_input_next),
                color = if (nicknameCheckState) White else Cadet_Grey,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                textAlign = TextAlign.Center
            )
        }
    }
}
