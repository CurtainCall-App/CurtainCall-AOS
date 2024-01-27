package com.cmc.curtaincall.common.designsystem.component.appbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    CurtainCallTheme {
        CurtainCallDefaultTopAppBar()
    }
}

@Preview
@Composable
private fun TopAppBarWithBackPreview() {
    CurtainCallTheme {
        CurtainCallTopAppBarWithBack(
            title = "타이틀"
        )
    }
}
