package com.cmc.curtaincall.common.design.component.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun CurtainCallIconText(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    containerColor: Color,
    contentColor: Color,
    fontSize: TextUnit,
    iconModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(containerColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = iconModifier,
            tint = contentColor
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 6.dp),
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CurtainCallRoundedText(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color,
    fontSize: TextUnit,
    radiusSize: Dp,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(containerColor, RoundedCornerShape(radiusSize))
            .then(modifier)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
fun CurtainCallBorderText(
    modifier: Modifier = Modifier,
    text: String,
    borderColor: Color,
    contentColor: Color,
    fontSize: TextUnit,
    radiusSize: Dp
) {
    Box(
        modifier = Modifier
            .border(1.dp, borderColor, RoundedCornerShape(radiusSize))
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
