package com.cmc.curtaincall.common.designsystem.component.buttons.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

private val ButtonRadius = 10.dp
private val OutlinedButtonBorderSize = 1.dp

@Composable
fun CurtainCallFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    containerColor: Color = CurtainCallTheme.colors.primary,
    contentColor: Color = CurtainCallTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(ButtonRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
    ) {
        Text(
            text = text,
            style = CurtainCallTheme.typography.subTitle4.copy(
                color = contentColor
            )
        )
    }
}

@Composable
fun CurtainCallOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    containerColor: Color = CurtainCallTheme.colors.background,
    contentColor: Color = CurtainCallTheme.colors.primary,
    borderColor: Color = CurtainCallTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(ButtonRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        border = BorderStroke(OutlinedButtonBorderSize, borderColor)
    ) {
        Text(
            text = text,
            style = CurtainCallTheme.typography.subTitle4.copy(
                color = contentColor
            )
        )
    }
}
