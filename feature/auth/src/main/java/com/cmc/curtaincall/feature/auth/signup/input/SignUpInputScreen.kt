package com.cmc.curtaincall.feature.auth.signup.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cheery_Paddle_Pop
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Green
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import kotlinx.coroutines.flow.collectLatest

private const val INPUT_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{0,10}$"
private const val INPUT_CHECK_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$"

@Composable
internal fun SignUpInputScreen(
    onNavigateWelcome: () -> Unit = {},
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

@Composable
private fun SignUpInputContent(
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpInputViewModel = hiltViewModel(),
    onNavigateWelcome: () -> Unit = {}
) {
    val nickNameCheck by signUpViewModel.nickNameCheck.collectAsStateWithLifecycle()
    var nicknameState by remember { mutableStateOf("") }

    LaunchedEffect(signUpViewModel) {
        signUpViewModel.signUpCompleted.collectLatest { isCompleted ->
            if (isCompleted) onNavigateWelcome()
        }
    }

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
                    signUpViewModel.initNicknameCheck()
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
            borderColor = if (nickNameCheck == NickNameCheck.Duplicate) Cheery_Paddle_Pop else Color.Transparent,
            contentModifier = Modifier.padding(horizontal = 20.dp),
            placeholder = stringResource(R.string.signup_input_nickname)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            if (nickNameCheck != NickNameCheck.None) {
                Text(
                    text = stringResource(
                        if (nickNameCheck == NickNameCheck.Validate) {
                            R.string.signup_nickname_validate
                        } else {
                            R.string.signup_nickname_duplicate
                        }
                    ),
                    color = if (nickNameCheck == NickNameCheck.Validate) Green else Cheery_Paddle_Pop,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 16.dp.toSp()
                )
            }
            Spacer(Modifier.weight(1f))
            CurtainCallRoundedTextButton(
                onClick = { signUpViewModel.checkDuplicateNickname(nicknameState) },
                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
                title = stringResource(R.string.signup_input_double_check),
                fontSize = 13.dp.toSp(),
                enabled = Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (nickNameCheck == NickNameCheck.None),
                containerColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (nickNameCheck == NickNameCheck.None)) Me_Pink else Bright_Gray,
                contentColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (nickNameCheck == NickNameCheck.None)) White else Silver_Sand,
                radiusSize = 8.dp
            )
        }
        Spacer(Modifier.weight(1f))
        CurtainCallRoundedTextButton(
            onClick = { signUpViewModel.createMember(nicknameState) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 19.dp)
                .height(52.dp),
            title = stringResource(R.string.signup_nickname_setting_complete),
            fontSize = 16.dp.toSp(),
            enabled = nickNameCheck == NickNameCheck.Validate,
            containerColor = if (nickNameCheck == NickNameCheck.Validate) Me_Pink else Bright_Gray,
            contentColor = if (nickNameCheck == NickNameCheck.Validate) White else Silver_Sand
        )
    }
}
