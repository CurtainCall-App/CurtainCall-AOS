package com.cmc.curtaincall.feature.mypage.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.TopAppBarOnlyAction
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Black
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(
    onNavigateRecruitment: () -> Unit,
    onNavigateParticipant: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    Scaffold(
        topBar = {
            TopAppBarOnlyAction(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageContent(
    modifier: Modifier = Modifier
) {
    val verticalScrollState = rememberScrollState()
    Column(modifier.verticalScroll(verticalScrollState)) {
        MyPageProfile(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 6.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            MyPageContentItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                icon = painterResource(R.drawable.ic_edit_square),
                title = stringResource(R.string.mypage_my_writing)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Cultured)
            )
            MyPageContentItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                icon = painterResource(R.drawable.ic_border_all),
                title = stringResource(R.string.mypage_saved_performance_list)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Cultured)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            MyPageInfoItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                title = stringResource(R.string.mypage_announcement)
            )
            MyPageInfoItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                title = stringResource(R.string.mypage_the_most_frequently_question)
            )
            Spacer(
                modifier = Modifier.height(48.dp)
            )
        }
        MyPageFooter(
            modifier = Modifier
                .fillMaxWidth()
                .background(Cultured)
        )
    }
}

@Composable
private fun MyPageFooter(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.mypage_curtaincall_customer_center),
            modifier = Modifier.padding(top = 24.dp),
            color = Arsenic,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = stringResource(R.string.mypage_curtaincall_email),
            modifier = Modifier.padding(top = 4.dp),
            color = Arsenic,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = stringResource(R.string.mypage_can_consult_time),
            modifier = Modifier.padding(top = 14.dp),
            color = Black_Coral,
            fontSize = 10.dp.toSp(),
            fontWeight = FontWeight.Normal,
            fontFamily = spoqahansanseeo
        )
        Spacer(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 18.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Bright_Gray)
        )
        Text(
            text = stringResource(R.string.mypage_source_of_performance_information),
            modifier = Modifier.padding(bottom = 50.dp),
            color = Roman_Silver,
            fontSize = 10.dp.toSp(),
            fontWeight = FontWeight.Normal,
            fontFamily = spoqahansanseeo,
            lineHeight = 18.dp.toSp()
        )
    }
}

@Composable
private fun MyPageInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Roman_Silver,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Icon(
            painter = painterResource(R.drawable.ic_arrow_right_pink),
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = Arsenic
        )
    }
}

@Composable
private fun MyPageContentItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp)
                .size(24.dp),
            tint = Color.Unspecified
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 12.dp),
            color = Nero,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun MyPageProfile(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.mypage_title),
            color = Black,
            fontSize = 24.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Box(Modifier.size(92.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img_example_detail),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(80.dp)
                            .clip(RoundedCornerShape(27.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .background(Cetacean_Blue, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_pen),
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Text(
                    text = "고라파덕님",
                    modifier = Modifier.padding(top = 10.dp),
                    color = Black,
                    fontSize = 18.dp.toSp(),
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Cultured, RoundedCornerShape(15.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 18.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.mypage_my_gathering_tab),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "2",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Me_Pink,
                    fontSize = 24.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Silver_Sand)
                    .size(1.dp, 30.dp)

            )
            Column(
                modifier = Modifier
                    .padding(vertical = 18.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.mypage_my_participation_tab),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "2",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Me_Pink,
                    fontSize = 24.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}
