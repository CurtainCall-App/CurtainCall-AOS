package com.cmc.curtaincall.common.designsystem.component.buttons.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme

@Preview
@Composable
fun CurtainCallFilledButtonPreview() {
    CurtainCallTheme {
        CurtainCallFilledButton(
            modifier = Modifier.fillMaxWidth(),
            text = "텍스트",
            containerColor = CurtainCallTheme.colors.secondary
        )
    }
}

@Preview
@Composable
fun CurtainCallOutlinedButtonPreview() {
    CurtainCallTheme {
        CurtainCallOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "텍스트"
        )
    }
}
