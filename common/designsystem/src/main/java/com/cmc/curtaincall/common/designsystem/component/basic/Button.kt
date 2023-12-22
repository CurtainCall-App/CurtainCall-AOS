package com.cmc.curtaincall.common.designsystem.component.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
fun CurtainCallBorderTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    fontSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    radiusSize: Dp
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(radiusSize),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(
            text = title,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
fun CurtainCallRoundedTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    title: String,
    fontSize: TextUnit,
    enabled: Boolean = true,
    containerColor: Color,
    contentColor: Color,
    radiusSize: Dp = 12.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier.then(contentModifier),
        enabled = enabled,
        shape = RoundedCornerShape(radiusSize),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Text(
            text = title,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
fun CurtainCallSelectTypeButton(
    modifier: Modifier = Modifier,
    firstType: String,
    lastType: String,
    isCheckFirstType: Boolean = true,
    onTypeChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier.background(Cultured, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 4.dp)
                .padding(vertical = 4.dp)
                .background(if (isCheckFirstType) Me_Pink else Cultured, RoundedCornerShape(8.dp))
                .clickable { onTypeChange(true) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstType,
                color = if (isCheckFirstType) White else Silver_Sand,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 4.dp)
                .padding(vertical = 4.dp)
                .background(if (isCheckFirstType) Cultured else Me_Pink, RoundedCornerShape(8.dp))
                .clickable { onTypeChange(false) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.partymember_create_classification_musical),
                color = if (isCheckFirstType) Silver_Sand else White,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
    }
}

@Composable
fun CurtainCallDropDownButton(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    title: String,
    fontSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    radiusSize: Dp,
    contentModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(containerColor, RoundedCornerShape(radiusSize))
            .border(BorderStroke(1.dp, if (isClicked) borderColor else Color.Transparent), RoundedCornerShape(radiusSize))
            .then(contentModifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Icon(
            painter = painterResource(R.drawable.ic_dropdown),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            tint = Color.Unspecified
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
        fontSize = 16.dp.toSp(),
        containerColor = Me_Pink,
        contentColor = Cetacean_Blue
    )
}

@Preview
@Composable
private fun CurtainCallSelectTypeButtonPreview() {
    CurtainCallSelectTypeButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        firstType = "연극",
        lastType = "뮤지컬"
    )
}

@Preview
@Composable
private fun CurtainCallBorderTextButtonPreview() {
    CurtainCallBorderTextButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        title = stringResource(R.string.performance_detail_more_view),
        fontSize = 16.dp.toSp(),
        containerColor = White,
        contentColor = Me_Pink,
        borderColor = Me_Pink,
        radiusSize = 12.dp
    )
}
