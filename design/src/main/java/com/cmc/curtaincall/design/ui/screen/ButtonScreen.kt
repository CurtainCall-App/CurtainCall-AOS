package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.basic.CurtainCallBorderTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallDropDownButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSelectTypeButton
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White

@Composable
internal fun ButtonScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = "Button",
                containerColor = Cetacean_Blue,
                contentColor = White,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        ButtonContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue)
        )
    }
}

@Composable
private fun ButtonContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        CurtainCallRoundedTextButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(52.dp),
            title = "커튼콜",
            fontSize = 16.dp.toSp(),
            containerColor = Bright_Gray,
            contentColor = Silver_Sand
        )
        CurtainCallRoundedTextButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(52.dp),
            title = "커튼콜",
            fontSize = 16.dp.toSp(),
            containerColor = Me_Pink,
            contentColor = White
        )
        CurtainCallSelectTypeButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(45.dp),
            firstType = "연극",
            lastType = "뮤지컬",
            isCheckFirstType = true,
            onTypeChange = { }
        )
        CurtainCallBorderTextButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(52.dp),
            title = "더보기",
            fontSize = 16.dp.toSp(),
            containerColor = Color.Transparent,
            contentColor = Me_Pink,
            borderColor = Me_Pink,
            radiusSize = 12.dp
        )
        CurtainCallDropDownButton(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(38.dp),
            title = "물건 종류 선택",
            fontSize = 14.dp.toSp(),
            containerColor = Cultured,
            contentColor = Silver_Sand,
            borderColor = Roman_Silver,
            radiusSize = 6.dp,
            contentModifier = Modifier
                .padding(vertical = 9.dp)
                .padding(start = 12.dp, end = 8.dp)
        )
    }
}
