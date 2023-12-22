package com.cmc.curtaincall.common.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
fun CurtainCallBasicDialog(
    title: String,
    description: String = "",
    dismissText: String,
    positiveText: String,
    onDismiss: () -> Unit = {},
    onPositive: () -> Unit = {}
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(15.dp))
                .padding(top = 34.dp, bottom = 20.dp)
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    modifier = Modifier.padding(top = 10.dp),
                    color = Roman_Silver,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp(),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .height(46.dp)
            ) {
                CurtainCallRoundedTextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    title = dismissText,
                    fontSize = 14.dp.toSp(),
                    containerColor = Bright_Gray,
                    contentColor = Silver_Sand,
                    radiusSize = 10.dp
                )
                CurtainCallRoundedTextButton(
                    onClick = onPositive,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                        .fillMaxHeight(),
                    title = positiveText,
                    fontSize = 14.dp.toSp(),
                    containerColor = Me_Pink,
                    contentColor = White,
                    radiusSize = 10.dp
                )
            }
        }
    }
}

@Composable
fun CurtainCallConfirmDialog(
    title: String,
    description: String = "",
    positiveText: String = "",
    onDismiss: () -> Unit = {},
    onPositive: () -> Unit = {}
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(15.dp))
                .padding(top = 34.dp, bottom = 20.dp)
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Chinese_Black,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Text(
                text = description,
                modifier = Modifier.padding(top = 10.dp),
                color = Roman_Silver,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                lineHeight = 22.dp.toSp(),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(46.dp)
            ) {
                CurtainCallRoundedTextButton(
                    onClick = onPositive,
                    modifier = Modifier.fillMaxSize(),
                    title = positiveText,
                    fontSize = 16.dp.toSp(),
                    containerColor = Me_Pink,
                    contentColor = White,
                    radiusSize = 10.dp
                )
            }
        }
    }
}
