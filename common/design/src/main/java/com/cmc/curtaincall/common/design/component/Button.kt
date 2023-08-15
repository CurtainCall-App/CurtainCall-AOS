package com.cmc.curtaincall.common.design.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun CurtainCallRoundedTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    containerColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(
            text = title,
            color = contentColor,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Preview
@Composable
private fun CurtainCallRoundedTextButtonPreview() {
    CurtainCallRoundedTextButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        title = "로그인하기",
        containerColor = Me_Pink,
        contentColor = Cetacean_Blue
    )
}
