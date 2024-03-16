package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.component.card.PartyType
import com.cmc.curtaincall.common.designsystem.theme.Grey8
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.appbars.CurtainCallSearchTitleTopAppBarWithCalendar
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Composable
fun PartyMemberScreen(
    partyMemberViewModel: PartyMemberViewModel = hiltViewModel(),
    onNavigateList: (PartyType) -> Unit
) {
    SystemUiStatusBar(Grey8)
    Scaffold(
        topBar = {
            CurtainCallSearchTitleTopAppBarWithCalendar(
                title = stringResource(R.string.party_member),
                containerColor = Grey8,
                onClick = {
                    // TODO 캘린더 클릭
                }
            )
        },
        floatingActionButton = {
            CurtainCallFilledButton(
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(52.dp),
                text = stringResource(R.string.party_member_recruitment),
                textStyle = CurtainCallTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                onClick = {
                    // TODO 파티원 모집
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        PartyMemberContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Grey8)
        )
    }
}

@Composable
private fun PartyMemberContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(modifier.verticalScroll(scrollState)) {
    }
}
