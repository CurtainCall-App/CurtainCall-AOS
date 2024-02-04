package com.cmc.curtaincall.common.designsystem.component.appbars

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Preview
@Composable
private fun CurtainCallTitleTopAppBarPreview() {
    CurtainCallTheme {
        CurtainCallTitleTopAppBar()
    }
}

@Preview
@Composable
private fun CurtainCallCenterTopAppBarWithBackPreview() {
    CurtainCallTheme {
        CurtainCallCenterTopAppBarWithBack(title = "타이틀")
    }
}

@Preview
@Composable
private fun CurtainCallSearchTitleTopAppBarPreview() {
    var text = remember { mutableStateOf("") }
    CurtainCallTheme {
        CurtainCallSearchTitleTopAppBar(
            title = "작품",
            searchAppBarType = SearchAppBarType(
                searchText = text,
                onTextChange = { text.value = it }
            )
        )
    }
}
