package com.cmc.curtaincall.feature.auth.signup.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.feature.auth.signup.CheckState
import com.cmc.curtaincall.feature.auth.signup.SignUpSideEffect
import com.cmc.curtaincall.feature.auth.signup.SignUpViewModel
import kotlinx.coroutines.flow.collectLatest

private const val INPUT_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{0,10}$"
private const val INPUT_CHECK_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SignUpInputScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
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
            signUpViewModel = signUpViewModel,
            onNavigateWelcome = onNavigateWelcome
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpInputContent(
    signUpViewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
    onNavigateWelcome: () -> Unit
) {
    var nicknameState by remember { mutableStateOf("") }
    val signUpState by signUpViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(signUpViewModel) {
        signUpViewModel.effects.collectLatest { sideEffect ->
            when (sideEffect) {
                is SignUpSideEffect.CreateMember -> {
                    onNavigateWelcome()
                }

                else -> Unit
            }
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
                    signUpViewModel.changeCheckState(CheckState.None)
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
            borderColor = if (signUpState.checkState == CheckState.Duplicate) Cheery_Paddle_Pop else Color.Transparent,
            contentModifier = Modifier.padding(horizontal = 20.dp),
            placeholder = stringResource(R.string.signup_input_nickname)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            if (signUpState.checkState != CheckState.None) {
                Text(
                    text = stringResource(
                        if (signUpState.checkState == CheckState.Validate) {
                            R.string.signup_nickname_validate
                        } else {
                            R.string.signup_nickname_duplicate
                        }
                    ),
                    color = if (signUpState.checkState == CheckState.Validate) Green else Cheery_Paddle_Pop,
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
                enabled = Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (signUpState.checkState == CheckState.None),
                containerColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (signUpState.checkState == CheckState.None)) Me_Pink else Bright_Gray,
                contentColor = if (Regex(INPUT_CHECK_REGEX).matches(nicknameState) && (signUpState.checkState == CheckState.None)) White else Silver_Sand,
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
            enabled = signUpState.checkState == CheckState.Validate,
            containerColor = if (signUpState.checkState == CheckState.Validate) Me_Pink else Bright_Gray,
            contentColor = if (signUpState.checkState == CheckState.Validate) White else Silver_Sand
        )
    }
}
