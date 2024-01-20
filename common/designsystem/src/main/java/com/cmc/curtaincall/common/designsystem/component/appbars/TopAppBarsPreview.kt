package com.cmc.curtaincall.common.designsystem.component.appbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Preview
@Composable
private fun DefaultTopAppBarPreview() {
    CurtainCallTheme {
        CurtainCallDefaultTopAppBar(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun TopAppBarWithBackPreview() {
    CurtainCallTheme {
        CurtainCallTopAppBarWithBack(
            title = "타이틀",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
