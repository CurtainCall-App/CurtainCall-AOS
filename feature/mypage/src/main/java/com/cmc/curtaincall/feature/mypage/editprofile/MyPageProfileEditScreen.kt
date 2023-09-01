package com.cmc.curtaincall.feature.mypage.editprofile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cheery_Paddle_Pop
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Green
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import kotlinx.coroutines.flow.collectLatest

private const val INPUT_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{0,10}$"
private const val INPUT_CHECK_REGEX = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageProfileEditScreen(
    myPageProfileEditViewModel: MyPageProfileEditViewModel = hiltViewModel(),
    profileUrl: String?,
    onBack: () -> Unit
) {
    Log.d("msg", "MyPageProfileEditScreen $profileUrl")
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_profile_edit_appbar_title),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageProfileEditContent(
            myPageProfileEditViewModel = myPageProfileEditViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            profileUrl = profileUrl,
            onBack = onBack
        )
    }
}

@Composable
private fun MyPageProfileEditContent(
    myPageProfileEditViewModel: MyPageProfileEditViewModel,
    modifier: Modifier = Modifier,
    profileUrl: String?,
    onBack: () -> Unit = {}
) {
    val nickname by myPageProfileEditViewModel.nicknameState.collectAsStateWithLifecycle()
    val validate by myPageProfileEditViewModel.validateState.collectAsStateWithLifecycle()

    LaunchedEffect(myPageProfileEditViewModel) {
        myPageProfileEditViewModel.completeUpdate.collectLatest { check ->
            if (check) onBack()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyPageProfile(
            myPageProfileEditViewModel = myPageProfileEditViewModel,
            profileUrl = profileUrl
        )
        MyPageNickName(
            myPageProfileEditViewModel = myPageProfileEditViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            nickname = nickname,
            validate = validate
        )
        Spacer(Modifier.weight(1f))
        CurtainCallRoundedTextButton(
            onClick = { myPageProfileEditViewModel.updateMember() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 19.dp)
                .height(52.dp),
            title = stringResource(R.string.signup_nickname_setting_complete),
            fontSize = 16.dp.toSp(),
            enabled = validate == CheckState.Validate,
            containerColor = if (validate == CheckState.Validate) Me_Pink else Bright_Gray,
            contentColor = if (validate == CheckState.Validate) White else Silver_Sand
        )
    }
}

@Composable
private fun MyPageNickName(
    myPageProfileEditViewModel: MyPageProfileEditViewModel,
    modifier: Modifier = Modifier,
    nickname: String,
    validate: CheckState
) {
    Column(modifier) {
        CurtainCallSingleLineTextField(
            value = nickname,
            onValueChange = {
                if (Regex(INPUT_REGEX).matches(it)) {
                    myPageProfileEditViewModel.changeValidateState(CheckState.None)
                    myPageProfileEditViewModel.setNickname(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Cultured, RoundedCornerShape(10.dp)),
            fontSize = 16.dp.toSp(),
            shape = RoundedCornerShape(10.dp),
            containerColor = Cultured,
            contentColor = Nero,
            borderColor = if (validate == CheckState.Duplicate) Cheery_Paddle_Pop else Color.Transparent,
            contentModifier = Modifier.padding(horizontal = 20.dp),
            placeholder = stringResource(R.string.signup_input_nickname)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            if (validate != CheckState.None) {
                Text(
                    text = stringResource(
                        if (validate == CheckState.Validate) {
                            R.string.signup_nickname_validate
                        } else {
                            R.string.signup_nickname_duplicate
                        }
                    ),
                    color = if (validate == CheckState.Validate) Green else Cheery_Paddle_Pop,
                    fontSize = 13.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 16.dp.toSp()
                )
            }
            Spacer(Modifier.weight(1f))
            CurtainCallRoundedTextButton(
                onClick = { myPageProfileEditViewModel.checkDuplicateNickname(nickname) },
                modifier = Modifier
                    .wrapContentWidth()
                    .height(32.dp),
                title = stringResource(R.string.signup_input_double_check),
                fontSize = 13.dp.toSp(),
                enabled = Regex(INPUT_CHECK_REGEX).matches(nickname) && (validate == CheckState.None),
                containerColor = if (Regex(INPUT_CHECK_REGEX).matches(nickname) && (validate == CheckState.None)) Me_Pink else Bright_Gray,
                contentColor = if (Regex(INPUT_CHECK_REGEX).matches(nickname) && (validate == CheckState.None)) White else Silver_Sand,
                radiusSize = 8.dp
            )
        }
    }
}

@Composable
private fun MyPageProfile(
    myPageProfileEditViewModel: MyPageProfileEditViewModel,
    profileUrl: String?
) {
    val context = LocalContext.current
    var profileUri by remember { mutableStateOf<Uri?>(null) }
    val takePhotoFromAlbum = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { uri ->
            profileUri = uri
            context.contentResolver.openInputStream(uri)?.let { inputStream ->
                myPageProfileEditViewModel.uploadImage(inputStream)
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 75.dp, bottom = 50.dp)
            .size(92.dp)
    ) {
        AsyncImage(
            model = if (profileUri == null) profileUrl else profileUri,
            contentDescription = null,
            error = painterResource(R.drawable.ic_default_profile),
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(80.dp)
                .clip(RoundedCornerShape(27.dp)),
            contentScale = ContentScale.FillBounds
        )
        IconButton(
            onClick = { takePhotoFromAlbum.launch("image/*") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Cetacean_Blue, CircleShape)
                .size(32.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_photo_library),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified
            )
        }
    }
}
