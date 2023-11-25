package com.cmc.curtaincall.common.design.component.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Grey
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun CurtainCallSnackbarHost(
    snackbarHostState: SnackbarHostState,
    content: @Composable BoxScope.(SnackbarData) -> Unit
) {
    SnackbarHost(
        hostState = snackbarHostState
    ) { snackbarData ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            content(snackbarData)
        }
    }
}

@Composable
fun BoxScope.CurtainCallSnackbar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData
) {
    Box(
        modifier = modifier
            .padding(bottom = 19.dp)
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .background(Bright_Grey.copy(0.9f), RoundedCornerShape(12.dp))
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_complete_green),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                text = snackbarData.visuals.message,
                modifier = Modifier.padding(start = 10.dp),
                color = White,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
