package com.cmc.curtaincall.feature.auth.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*

@Composable
internal fun WelComeScreen(
    onNavigateHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_welcome_anim),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 201.dp)
                .size(100.dp)
        )

        Text(
            text = stringResource(R.string.signup_welcome_description),
            modifier = Modifier.padding(top = 13.dp),
            color = Gunmetal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            lineHeight = 32.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onNavigateHome,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 15.dp)
                .padding(horizontal = 22.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Me_Pink)
        ) {
            Text(
                text = stringResource(R.string.signup_welcome_start),
                color = White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo,
                textAlign = TextAlign.Center
            )
        }
    }
}
