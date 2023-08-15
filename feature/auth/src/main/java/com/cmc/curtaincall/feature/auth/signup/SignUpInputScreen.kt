package com.cmc.curtaincall.feature.auth.signup

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.design.component.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
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
                title = stringResource(R.string.signup_setting_nickname),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        SignUpInputContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
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

    Column(modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(R.string.signup_input_description),
            modifier = Modifier.padding(top = 40.dp),
            color = Black,
            fontSize = 22.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            lineHeight = 32.dp.toSp()
        )
        Text(
            text = stringResource(R.string.signup_input_sub_description),
            modifier = Modifier.padding(top = 14.dp),
            color = Roman_Silver,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp()
        )
        CurtainCallSingleLineTextField(
            value = nicknameState,
            onValueChange = {
                if (Regex(INPUT_REGEX).matches(it)) {
                    nicknameState = it
                }
            },
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(60.dp),
            fontSize = 16.dp.toSp(),
            shape = RoundedCornerShape(10.dp),
            containerColor = Cultured,
            contentColor = Roman_Silver,
            contentModifier = Modifier.padding(horizontal = 20.dp),
            placeholder = stringResource(R.string.signup_input_nickname)
        )
        CurtainCallRoundedTextButton(
            onClick = {
                nicknameCheckState = true
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp)
                .wrapContentWidth()
                .height(32.dp),
            title = stringResource(R.string.signup_input_double_check),
            fontSize = 13.dp.toSp(),
            enabled = Regex(INPUT_CHECK_REGEX).matches(nicknameState),
            containerColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState)) Me_Pink else Bright_Gray,
            contentColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState)) White else Silver_Sand,
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(Modifier.weight(1f))
        CurtainCallRoundedTextButton(
            onClick = onNavigateWelcome,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 19.dp)
                .height(52.dp),
            title = stringResource(R.string.signup_nickname_setting_complete),
            fontSize = 16.dp.toSp(),
            enabled = nicknameCheckState,
            containerColor = if (nicknameCheckState) Me_Pink else Bright_Gray,
            contentColor = if (nicknameCheckState) White else Silver_Sand
        )
    }
}
