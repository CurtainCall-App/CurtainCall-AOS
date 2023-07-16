package com.cmc.curtaincall.feature.partymember.ui

import androidx.compose.runtime.Composable
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.feature.partymember.PartyType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun PartyMemberCreateScreen(
    partyType: PartyType,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)
}
