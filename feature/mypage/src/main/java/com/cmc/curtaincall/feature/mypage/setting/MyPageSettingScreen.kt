package com.cmc.curtaincall.feature.mypage.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.dialog.CurtainCallBasicDialog
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageSettingScreen(
    myPageSettingViewModel: MyPageSettingViewModel = hiltViewModel(),
    onLogout: () -> Unit,
    onNavigateDeleteMember: () -> Unit,
    onNavigatePrivacyTerms: () -> Unit,
    onNavigateServiceTerms: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.mypage_setting),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageSettingContent(
            myPageSettingViewModel = myPageSettingViewModel,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onLogout = onLogout,
            onNavigateDeleteMember = onNavigateDeleteMember,
            onNavigatePrivacyTerms = onNavigatePrivacyTerms,
            onNavigateServiceTerms = onNavigateServiceTerms
        )
    }
}

@Composable
private fun MyPageSettingContent(
    myPageSettingViewModel: MyPageSettingViewModel,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onNavigateDeleteMember: () -> Unit,
    onNavigatePrivacyTerms: () -> Unit,
    onNavigateServiceTerms: () -> Unit
) {
    var isShowDialog by remember { mutableStateOf(false) }
    if (isShowDialog) {
        CurtainCallBasicDialog(
            title = stringResource(R.string.dialog_logout_title),
            dismissText = stringResource(R.string.dialog_logout_dismiss),
            positiveText = stringResource(R.string.dialog_logout_positive),
            onDismiss = { isShowDialog = false },
            onPositive = {
                myPageSettingViewModel.memberLogout()
                isShowDialog = false
            }
        )
    }

    LaunchedEffect(myPageSettingViewModel) {
        myPageSettingViewModel.isLogout.collectLatest { isLogout ->
            if (isLogout) onLogout()
        }
    }

    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.mypage_setting_account),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                color = Silver_Sand,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                title = stringResource(R.string.mypage_setting_logout),
                isShowIcon = false,
                onClick = { isShowDialog = true }
            )
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                title = stringResource(R.string.mypage_setting_remove_member),
                onClick = onNavigateDeleteMember
            )
            Spacer(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Cultured)
            )
            Text(
                text = stringResource(R.string.mypage_setting_information),
                modifier = Modifier.fillMaxWidth(),
                color = Silver_Sand,
                fontSize = 13.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                title = stringResource(R.string.mypage_setting_privacy_information_policy),
                onClick = { onNavigatePrivacyTerms() }
            )
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp),
                title = stringResource(R.string.mypage_setting_service_use_terms),
                onClick = { onNavigateServiceTerms() }
            )
        }
    }
}

@Composable
private fun SettingItem(
    modifier: Modifier = Modifier,
    title: String,
    isShowIcon: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Nero,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        if (isShowIcon) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right_pink),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = Arsenic
            )
        }
    }
}
