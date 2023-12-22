package com.cmc.curtaincall.feature.auth.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.SystemUiStatusBar
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Cetacean_Blue
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
internal fun WelComeScreen(
    onNavigateHome: () -> Unit
) {
    SystemUiStatusBar(Cetacean_Blue)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cetacean_Blue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(232f))
        Image(
            painter = painterResource(R.drawable.ic_welcome_anim),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(R.string.signup_welcome_description),
            modifier = Modifier.padding(top = 14.dp),
            color = Me_Pink,
            fontSize = 20.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo,
            lineHeight = 32.dp.toSp(),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(259f))
        CurtainCallRoundedTextButton(
            onClick = onNavigateHome,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 19.dp)
                .padding(horizontal = 20.dp)
                .height(52.dp),
            title = stringResource(R.string.signup_welcome_start),
            fontSize = 16.dp.toSp(),
            containerColor = Me_Pink,
            contentColor = White
        )
    }
}
