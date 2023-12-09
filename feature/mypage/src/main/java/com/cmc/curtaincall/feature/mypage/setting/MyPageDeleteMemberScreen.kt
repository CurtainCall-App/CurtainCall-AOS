package com.cmc.curtaincall.feature.mypage.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyPageDeleteMemberScreen(
    myPageDeleteMemberViewModel: MyPageDeleteMemberViewModel = hiltViewModel(),
    onDeleteMember: () -> Unit,
    onBack: () -> Unit
) {
    var step by remember { mutableStateOf(0) }
    val deleteReason by myPageDeleteMemberViewModel.deleteReason.collectAsStateWithLifecycle()

    LaunchedEffect(myPageDeleteMemberViewModel) {
        myPageDeleteMemberViewModel.isSuccessDelete.collectLatest { isSuccessDelete ->
            if (isSuccessDelete) step++
        }
    }

    Scaffold(
        topBar = {
            if (step < 2) {
                TopAppBarWithBack(
                    title = stringResource(R.string.mypage_setting_remove_member),
                    containerColor = White,
                    contentColor = Nero,
                    onClick = {
                        if (step == 0) {
                            onBack()
                        } else {
                            step--
                        }
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CurtainCallRoundedTextButton(
                onClick = {
                    if (step == 0) {
                        step++
                    } else if (step == 1) {
                        myPageDeleteMemberViewModel.deleteMember()
                    } else {
                        onDeleteMember()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                title = stringResource(
                    if (step < 2) {
                        R.string.mypage_setting_remove_member_next_step
                    } else {
                        R.string.mypage_setting_remove_member_complete
                    }
                ),
                fontSize = 16.dp.toSp(),
                enabled = when (step) {
                    0 -> deleteReason != DeleteReason.NONE
                    else -> true
                },
                containerColor = when (step) {
                    0 -> if (deleteReason == DeleteReason.NONE) Bright_Gray else Me_Pink
                    else -> Me_Pink
                },
                contentColor = when (step) {
                    0 -> if (deleteReason == DeleteReason.NONE) Silver_Sand else White
                    else -> White
                }
            )
        }
    ) { paddingValues ->
        if (step < 2) {
            MyPageDeleteMemberContent(
                myPageDeleteMemberViewModel = myPageDeleteMemberViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                step = step
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(256f))
                Image(
                    painter = painterResource(R.drawable.ic_complete),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp, 70.dp)
                )
                Text(
                    text = stringResource(R.string.mypage_setting_remove_member_finish),
                    modifier = Modifier.padding(top = 18.dp),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Spacer(Modifier.weight(360f))
            }
        }
    }
}

@Composable
private fun MyPageDeleteMemberContent(
    myPageDeleteMemberViewModel: MyPageDeleteMemberViewModel,
    modifier: Modifier = Modifier,
    step: Int
) {
    val deleteReason by myPageDeleteMemberViewModel.deleteReason.collectAsStateWithLifecycle()
    val content by myPageDeleteMemberViewModel.content.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .padding(horizontal = 20.dp)
    ) {
        if (step == 0) {
            Text(
                text = stringResource(R.string.mypage_setting_remove_member_title),
                color = Black,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = stringResource(R.string.mypage_setting_remove_member_reason),
                modifier = Modifier.padding(top = 6.dp),
                color = Roman_Silver,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.RECORD_DELETION) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.RECORD_DELETION) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.RECORD_DELETION) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.RECORD_DELETION.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.INCONVENIENCE_FREQUENT_ERROR) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.INCONVENIENCE_FREQUENT_ERROR) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.INCONVENIENCE_FREQUENT_ERROR) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.INCONVENIENCE_FREQUENT_ERROR.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.BETTER_OTHER_SERVICE) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.BETTER_OTHER_SERVICE) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.BETTER_OTHER_SERVICE) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.BETTER_OTHER_SERVICE.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.LOW_USAGE_FREQUENCY) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.LOW_USAGE_FREQUENCY) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.LOW_USAGE_FREQUENCY) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.LOW_USAGE_FREQUENCY.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.NOT_USEFUL) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.NOT_USEFUL) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.NOT_USEFUL) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.NOT_USEFUL.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
                    .clickable { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.ETC) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { myPageDeleteMemberViewModel.setDeleteReason(DeleteReason.ETC) },
                    modifier = Modifier
                        .background(if (deleteReason == DeleteReason.ETC) Me_Pink else Bright_Gray, CircleShape)
                        .size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = White
                    )
                }
                Text(
                    text = DeleteReason.ETC.value,
                    modifier = Modifier.padding(start = 10.dp),
                    color = Black_Coral,
                    fontSize = 15.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }

            if (deleteReason == DeleteReason.ETC) {
                CurtainCallMultiLineTextField(
                    value = content,
                    onValueChange = {
                        if (it.length <= 500) {
                            myPageDeleteMemberViewModel.setContent(it)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .heightIn(min = 145.dp),
                    fontSize = 15.dp.toSp(),
                    shape = RoundedCornerShape(10.dp),
                    containerColor = Cultured,
                    contentColor = Nero,
                    contentModifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                    placeholder = stringResource(R.string.mypage_setting_remove_member_reason_example),
                    placeholderColor = Silver_Sand
                )
                Text(
                    text = String.format(
                        stringResource(R.string.mypage_setting_remove_member_reason_text_count),
                        content.length,
                        500
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    color = Roman_Silver,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    textAlign = TextAlign.End
                )
            }
        } else {
            Text(
                text = stringResource(R.string.mypage_setting_remove_member_confirm),
                color = Black,
                fontSize = 22.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = stringResource(R.string.mypage_setting_remove_member_if),
                modifier = Modifier.padding(top = 6.dp),
                color = Roman_Silver,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .size(4.dp)
                        .background(Black_Coral, CircleShape)
                )
                Text(
                    text = stringResource(R.string.mypage_setting_remove_member_notice1),
                    modifier = Modifier.padding(start = 6.dp),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .size(4.dp)
                        .background(Black_Coral, CircleShape)
                )
                Text(
                    text = stringResource(R.string.mypage_setting_remove_member_notice2),
                    modifier = Modifier.padding(start = 6.dp),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 9.dp)
                        .size(4.dp)
                        .background(Black_Coral, CircleShape)
                )
                Text(
                    text = stringResource(R.string.mypage_setting_remove_member_notice3),
                    modifier = Modifier.padding(start = 6.dp),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(4.dp)
                        .background(Black_Coral, CircleShape)
                )
                Text(
                    text = stringResource(R.string.mypage_setting_remove_member_notice4),
                    modifier = Modifier.padding(start = 6.dp),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
            }
        }
    }
}
