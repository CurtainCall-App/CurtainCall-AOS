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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyPageSettingScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                title = stringResource(R.string.mypage_setting),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageSettingContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageSettingContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.mypage_setting_account),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .padding(top = 30.dp),
            color = Silver_Sand,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        SettingItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.mypage_setting_logout),
            onClick = {}
        )
        Spacer(
            modifier = Modifier
                .padding(vertical = 25.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Bright_Gray)
        )
        Text(
            text = stringResource(R.string.mypage_setting_information),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            color = Silver_Sand,
            fontSize = 13.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        SettingItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 23.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.mypage_setting_privacy_information_policy),
            onClick = {}
        )
        SettingItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.mypage_setting_service_use_terms),
            onClick = {}
        )
    }
}

@Composable
private fun SettingItem(
    modifier: Modifier = Modifier,
    title: String,
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
        Icon(
            painter = painterResource(R.drawable.ic_arrow_right_pink),
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = Arsenic
        )
    }
}
