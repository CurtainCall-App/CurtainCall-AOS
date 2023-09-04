package com.cmc.curtaincall.feature.mypage.question

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Arsenic
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun MyPageQuestionScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = stringResource(R.string.mypage_frequentyl_asked_question),
                containerColor = White,
                contentColor = Nero,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        MyPageQuestionContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun MyPageQuestionContent(
    modifier: Modifier = Modifier
) {
    var curIndex by remember { mutableStateOf(0) }
    val verticalScrollState = rememberScrollState()
    Column(modifier.verticalScroll(verticalScrollState)) {
        MyPageQuestionTab(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            curIndex = curIndex,
            onChangeIndex = { curIndex = it }
        )
        when (curIndex) {
            0 -> {
                PerformanceSearchContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            1 -> {
                PartyMemberContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            2 -> {
                LiveTalkContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            3 -> {
                UsageInquiryContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }

            4 -> {
                EtcContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )
            }
        }
    }
}

@Composable
private fun UsageInquiryContent(
    modifier: Modifier = Modifier
) {
    var click1 by remember { mutableStateOf(false) }
    var click2 by remember { mutableStateOf(false) }
    var click3 by remember { mutableStateOf(false) }

    Column(modifier) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "소셜 로그인 변경은 어떻게 하나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click1) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click1 = click1.not() },
                    tint = Color.Unspecified
                )
            }
            if (click1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "현재 커튼콜의 로그인 변경은 어려워요. 추후 앱 업데이트 시에 추가될 예정이에요. 조금만 기다려 주세요!",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "앱 오류가 떠요.",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click2) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click2 = click2.not() },
                    tint = Color.Unspecified
                )
            }
            if (click2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "네트워크나 서버의 문제일 수 있어요. 먼저 네트워크가 원활한 지 확인하고, 앱 업데이트 최신 버전으로 되었는지 확인해주세요. 서버의 문제일 경우 빠르게 조치할 예정이니 조금만 기다려주세요!\n" +
                                "\n" +
                                "추가적으로 궁금한 점이 있다면 아래 메일로 문의 주시면 빠르게 확인 후 답변 드릴게요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[커튼콜 고객센터]\n" +
                                "curtaincall.official2023@gmail.com",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "문의는 어디에서 할 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click3) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click3 = click3.not() },
                    tint = Color.Unspecified
                )
            }
            if (click3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "문의 사항이 있으시다면 커튼콜 메일로 문의해주세요.\n" +
                                "아래 메일 주소로 문의해 주시면, 빠르게 안내해 드릴게요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[커튼콜 고객센터]\n" +
                                "curtaincall.official2023@gmail.com",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun EtcContent(
    modifier: Modifier = Modifier
) {
    var click1 by remember { mutableStateOf(false) }
    var click2 by remember { mutableStateOf(false) }

    Column(modifier) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "커튼콜은 어떤 서비스인가요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click1) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click1 = click1.not() },
                    tint = Color.Unspecified
                )
            }
            if (click1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "커튼콜은 연극과 뮤지컬에 관한 정보를 공유하고 사용자 간 네트워크를 형성하는 커뮤니티 앱 서비스예요.\n" +
                            "\n" +
                            "커튼콜은 크게 세 가지의 기능을 제공해요.\n" +
                            "\n" +
                            "여러 작품들에 대한 정보를 알 수 있는 작품 탐색 기능, 무대 위의 감동을 공유하고자 하는 이들을 위한 파티원 모집 기능, 그리고 실시간으로 공연에 대한 기대감 혹은 후기를 나눌 수 있는 라이브톡 기능을 제공하고 있어요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "연뮤 가이드가 무엇인가요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click2) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click2 = click2.not() },
                    tint = Color.Unspecified
                )
            }
            if (click2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "연극과 뮤지컬에 막 입문한 이들을 위한 초보 가이드예요. 연극과 뮤지컬의 매력에 빠져 공연에 관한 다양한 정보들을 얻고 싶은 분들을 위해 커튼콜이 여러 정보들을 모아 놓았어요.\n" +
                                "\n" +
                                "총 세 가지 가이드가 있어요. 이럴 때 추천해요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[알쏭달쏭 용어 사전]\n" +
                                "그동안 알기 어려웠던 연뮤덕들의 용어를 알고 싶을 때 추천",
                            modifier = Modifier.padding(top = 24.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[티켓팅 안내]\n" +
                                "티켓팅을 어떻게 하면 더 쉽게 성공할 수 있는지 알고 싶다면 추천",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[할인 꿀팁]\n" +
                                "비싼 티켓을 할인 받아서 살 수 있는 방법이 궁금하다면 추천",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun LiveTalkContent(
    modifier: Modifier = Modifier
) {
    var click1 by remember { mutableStateOf(false) }
    var click2 by remember { mutableStateOf(false) }

    Column(modifier) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "라이브톡은 어떤 기능인가요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click1) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click1 = click1.not() },
                    tint = Color.Unspecified
                )
            }
            if (click1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "공연 전후에 실시간으로 사람들과 대화를 할 수 있는 기능이에요. 공연 전 공연에 대한 기대감을 공유하고, 공연 후 공연에 대한 실시간 후기를 공유할 수 있어요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "지난 공연의 라이브톡을 볼 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click2) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click2 = click2.not() },
                    tint = Color.Unspecified
                )
            }
            if (click2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "네, 가능해요. 지난 공연과 현재 상영 중인 공연에 대한 실시간 대화들을 모두 확인할 수 있어요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun PartyMemberContent(
    modifier: Modifier = Modifier
) {
    var click1 by remember { mutableStateOf(false) }
    var click2 by remember { mutableStateOf(false) }
    var click3 by remember { mutableStateOf(false) }
    var click4 by remember { mutableStateOf(false) }
    var click5 by remember { mutableStateOf(false) }
    var click6 by remember { mutableStateOf(false) }
    var click7 by remember { mutableStateOf(false) }
    var click8 by remember { mutableStateOf(false) }
    var click9 by remember { mutableStateOf(false) }

    Column(modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원은 어떤 기능인가요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click1) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click1 = click1.not() },
                    tint = Color.Unspecified
                )
            }
            if (click1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "공연 관람 시와 전후에 공연을 함께 즐길 사람을 구하는 기능이에요.\n\n총 세 가지 카테고리가 있어요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[공연 관람] 둘이서 혹은 여럿이 함께 공연을 관람할 파티원을 구할 수 있어요.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[식사/카페] 공연 전후에 근처 맛집이나 카페에서 사람들과 관심 있는 공연 혹은 배우에 관한 이야기를 나눌 파티원을 구할 수 있어요.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[기타] 이외에 공연 혹은 배우 관련 이벤트나 굿즈 제작 등 자유롭게 주제를 정해서 필요한 파티원을 구할 수 있어요.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원은 어떻게 모집하고 참여하나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click2) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click2 = click2.not() },
                    tint = Color.Unspecified
                )
            }
            if (click2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "(1) 만약 새로운 파티원을 모집하고 싶다면, 아래의 경로로 모집할 수 있어요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[파티원] - [원하는 주제 클릭] - [글쓰기 아이콘 클릭]",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "(2) 만약 기존 파티원에 참여하고 싶다면, 아래의 경로로 참여할 수 있어요.",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[파티원] - [원하는 주제 클릭] - [검색 or 리스트 스크롤] - [원하는 모집 글 클릭] - [참여하기]",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원 간 소통은 어떻게 할 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click3) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click3 = click3.not() },
                    tint = Color.Unspecified
                )
            }
            if (click3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "하나의 모집 글에 참여 인원이 모두 차게 되면 자동으로 파티원 간 톡방이 개설돼요. 이때 해당 글에 ‘TALK 입장’ 버튼이 생기는데 여기에서 파티원들이 서로 대화할 수 있어요.\n" +
                                "\n" +
                                "이후에 자신의 모집/참여 톡방을 보려면, 아래의 경로로 확인할 수 있어요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[MY] - [MY 모집 or MY 참여] - [해당하는 글의 ‘TALK 입장’ 클릭]",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "MY 모집의 톡방을 직접 개설 또는 삭제할 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click4) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click4 = click4.not() },
                    tint = Color.Unspecified
                )
            }
            if (click4) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "파티원 모집 톡방의 경우 직접 개설이나 삭제는 불가능해요.\n" +
                            "단, 파티원 모집 인원이 모두 찼을 때 톡방이 자동으로 개설되고, 모집 글을 삭제하면 자동으로 해당 톡방이 없어져요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "MY 모집 글을 삭제해도 톡방은 유지되나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click5) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click5 = click5.not() },
                    tint = Color.Unspecified
                )
            }
            if (click5) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "아니요, 모집 글을 삭제하면 해당 모집 글의 톡방은 자동으로 사라져요. 글 삭제 시 톡방이 함께 삭제되니 이 점 유의해주세요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원 톡방이 뜨지 않아요. 어떻게 참여할 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click6) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click6 = click6.not() },
                    tint = Color.Unspecified
                )
            }
            if (click6) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "파티원 모집 글에 기재된 인원 수가 다 모이지 않으면 톡방이 개설되지 않아요. 인원이 다 찰 때까지 조금만 더 기다려주세요!\n" +
                                "\n" +
                                "현재 참여 중인 톡방의 경우, 아래의 경로로 확인할 수 있어요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[MY] - [MY 모집 or MY 참여] - [해당하는 글의 ‘TALK 입장’ 클릭]",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원 모집에 참여했다가 취소하기가 가능한가요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click7) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click7 = click7.not() },
                    tint = Color.Unspecified
                )
            }
            if (click7) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "아니요, 한 번 파티원 모집에 참여하면 되돌아가기가 불가능해요. 따라서 신중하게 생각하고 모집 글에 참여해주세요!",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "파티원 모집 인원이 다 차지 않으면 어떻게 되나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click8) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click8 = click8.not() },
                    tint = Color.Unspecified
                )
            }
            if (click8) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "파티원 모집 인원이 모두 모이지 않으면 파티원 간 톡방이 개설되지 않아요. 인원이 다 찰 때까지 조금만 더 기다려주세요!",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "특정 글을 신고할 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click9) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click9 = click9.not() },
                    tint = Color.Unspecified
                )
            }
            if (click9) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "네, 가능해요. 한 줄 리뷰, 분실물 정보, 파티원 모집 글의 우측 상단에서 [신고] 기능을 통해 부적절한 글을 신고할 수 있어요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun PerformanceSearchContent(
    modifier: Modifier = Modifier
) {
    var click1 by remember { mutableStateOf(false) }
    var click2 by remember { mutableStateOf(false) }
    var click3 by remember { mutableStateOf(false) }
    var click4 by remember { mutableStateOf(false) }

    Column(modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "새로운 작품은 업데이트 언제 업데이트 되나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click1) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click1 = click1.not() },
                    tint = Color.Unspecified
                )
            }
            if (click1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "커튼콜은 공연예술통합전산망(KOPIS)에서 제공하는 OPEN API를 통해 데이터를 제공하고 있어요. KOPIS가 제공하는 업데이트 일시에 따라 다음 날 오전 9시에 업데이트 돼요.",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "한 줄 리뷰를 수정하고 싶어요. 어떻게 해야 하나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click2) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click2 = click2.not() },
                    tint = Color.Unspecified
                )
            }
            if (click2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "[MY] - [내가 쓴 글] - [한 줄 리뷰] - [더보기 클릭] - [수정 or 삭제]",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "저장한 작품 정보는 어디에서 확인하나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click3) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click3 = click3.not() },
                    tint = Color.Unspecified
                )
            }
            if (click3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "[MY] - [저장된 작품 목록]",
                        color = Arsenic,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 22.dp.toSp()
                    )
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Q.",
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Text(
                    text = "분실물 정보는 어떻게 올리고 찾을 수 있나요?",
                    modifier = Modifier
                        .padding(start = 5.dp, end = 18.dp)
                        .weight(1f),
                    color = Nero,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 22.dp.toSp()
                )
                Icon(
                    painter = painterResource(
                        if (click4) {
                            R.drawable.ic_arrow_up
                        } else {
                            R.drawable.ic_arrow_down
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { click4 = click4.not() },
                    tint = Color.Unspecified
                )
            }
            if (click4) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .background(Cultured)
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(
                            text = "(1) 만약 물건을 발견했다면, 아래의 경로로 올릴 수 있어요.",
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[작품 탐색] - [해당 공연 페이지] - [분실물] - [모두 보기] - [글쓰기 아이콘 클릭]",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "(2) 만약 물건을 분실했다면, 아래의 경로로 확인할 수 있어요.",
                            modifier = Modifier.padding(top = 30.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                        Text(
                            text = "[작품 탐색] - [해당 공연 페이지] - [분실물] - [모두 보기] - [잃어버린 날짜/분류 검색]",
                            modifier = Modifier.padding(top = 10.dp),
                            color = Arsenic,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 22.dp.toSp()
                        )
                    }
                }
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp)
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun MyPageQuestionTab(
    modifier: Modifier = Modifier,
    curIndex: Int,
    onChangeIndex: (Int) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    Row(modifier.horizontalScroll(horizontalScrollState)) {
        Box(
            modifier = Modifier
                .clickable { onChangeIndex(0) }
                .padding(start = 20.dp, end = 8.dp)
                .background(if (curIndex == 0) Cetacean_Blue else Cultured, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_question_performnace_search),
                color = if (curIndex == 0) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .clickable { onChangeIndex(1) }
                .padding(end = 8.dp)
                .background(if (curIndex == 1) Cetacean_Blue else Cultured, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_question_partymember),
                color = if (curIndex == 1) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .clickable { onChangeIndex(2) }
                .padding(end = 8.dp)
                .background(if (curIndex == 2) Cetacean_Blue else Cultured, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_question_livetalk),
                color = if (curIndex == 2) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .clickable { onChangeIndex(3) }
                .padding(end = 8.dp)
                .background(if (curIndex == 3) Cetacean_Blue else Cultured, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_question_usage_inquiry),
                color = if (curIndex == 3) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Box(
            modifier = Modifier
                .clickable { onChangeIndex(4) }
                .padding(end = 20.dp)
                .background(if (curIndex == 4) Cetacean_Blue else Cultured, RoundedCornerShape(20.dp))
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.mypage_question_etc),
                color = if (curIndex == 4) White else Cetacean_Blue,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
    }
}
