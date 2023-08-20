package com.cmc.curtaincall.common.design.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun CurtainCallBasicDialog(
    title: String,
    description: String = "",
    dismissText: String,
    positiveText: String,
    onDismiss: () -> Unit = {},
    onPositive: () -> Unit = {}
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
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
