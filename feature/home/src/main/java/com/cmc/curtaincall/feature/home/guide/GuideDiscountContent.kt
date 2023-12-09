package com.cmc.curtaincall.feature.home.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Arsenic
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo

@Composable
internal fun GuideDiscountContent(
    modifier: Modifier = Modifier
) {
    Column(modifier.background(White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))) {
        ReservationSiteGuide()
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        MobileDiscountGuide()
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 3,
            title = "카드사 할인",
            descriptions = listOf(
                "블루스퀘어 신한카드 홀: 신한카드 소지 회원 대상 최대 50% 할인",
                "그 외 예매 사이트 별 상이"
            )
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 4,
            title = "청소년 할인",
            descriptions = listOf("극장별 만 24세 이하의 청소년 할인 (학생증, 신분증, 재학증명서 등 증빙서류 필요)")
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 5,
            title = "아이겟 APP",
            descriptions = listOf("뮤지컬 할인 예매 사이트 (공연 공동 구매•경매)")
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 6,
            title = "유니켓 APP",
            descriptions = listOf(
                "대학생을 위한 공연 티켓 사이트",
                "대학교 인증코드 통해 유니켓 사이트 접속 시 할인 가격 제공"
            )
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 7,
            title = "제작사 SNS 알림",
            descriptions = listOf("제작사 SNS 할인 정보 제공")
        )
        Spacer(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Cultured)
        )
        GuideDiscount(
            modifier = Modifier.fillMaxWidth(),
            num = 8,
            title = "현장 할인점",
            descriptions = listOf("서울 연극센터 티켓판매소: 당일 티켓 최대 50% 할인")
        )
        Spacer(
            modifier = Modifier
                .padding(top = 94.dp)
                .fillMaxWidth()
                .height(12.dp)
                .background(Cultured)
        )
        Text(
            text = "* 위의 정보들은 공연/현장별로 실제 할인 정보와 상이할 수 있습니다.\n자세한 정보는 사이트 방문 후 확인하시길 바랍니다.",
            modifier = Modifier
                .padding(vertical = 24.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            color = Black_Coral,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Normal,
            fontFamily = spoqahansanseeo
        )
    }
}

@Composable
private fun MobileDiscountGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Me_Pink, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    color = White,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }

            Text(
                text = "통신사 할인",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                color = Chinese_Black,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 11.dp)) {
            Spacer(Modifier.width(42.dp))
            Column {
                GuideDiscountWithTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = "SKT",
                    descriptions = listOf(
                        "T멤버십 APP - 모든 혜택 - 컬처 - 공연 선택 - 예매",
                        "연극, 뮤지컬, 콘서트, 공연 최대 50% 할인"
                    )
                )
                GuideDiscountWithTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    title = "KT",
                    descriptions = listOf(
                        "KT멤버십 APP - 메뉴 - 공연 예매 - 공연 선택 - 예매",
                        "연극, 뮤지컬, 콘서트, 전시 최대 50% 할인"
                    )
                )
                GuideDiscountWithTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    title = "LG U+",
                    descriptions = listOf(
                        "뮤지컬 티켓 예매 시 최대 40% 할인"
                    )
                )
            }
        }
    }
}

@Composable
private fun ReservationSiteGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Me_Pink, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    color = White,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }

            Text(
                text = "예매 사이트",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                color = Chinese_Black,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 11.dp)) {
            Spacer(Modifier.width(42.dp))
            Column {
                GuideDiscountWithTitle(
                    modifier = Modifier.fillMaxWidth(),
                    title = "인터파크",
                    descriptions = listOf(
                        "유료 멤버십(토핑) 회원 대상 할인",
                        "제휴 카드 할인"
                    )
                )
                GuideDiscountWithTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    title = "예스24",
                    descriptions = listOf(
                        "소규모 공연 기간 제한 할인(엔젤 티켓)",
                        "제휴 결제 수단 할인",
                        "쿠폰 할인"
                    )
                )
                GuideDiscountWithTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    title = "타임티켓",
                    descriptions = listOf(
                        "마감 임박 가격 할인",
                        "매월 회차당 선착순 2~3매 특별 할인"
                    )
                )
                GuideDiscountWithTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    title = "기타",
                    descriptions = listOf("위메프, 티몬, 쿠팡 할인 등")
                )
            }
        }
    }
}

@Composable
private fun GuideDiscount(
    modifier: Modifier = Modifier,
    num: Int,
    title: String,
    descriptions: List<String>
) {
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Me_Pink, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = num.toString(),
                    color = White,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }

            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth(),
                color = Chinese_Black,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        }
        Row(Modifier.padding(top = 11.dp)) {
            Spacer(Modifier.width(42.dp))
            Column(Modifier.fillMaxWidth()) {
                descriptions.forEach { description ->
                    Row(Modifier.padding(top = 6.dp)) {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .size(4.dp)
                                .background(Black_Coral, CircleShape)
                        )
                        Text(
                            text = description,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxWidth(),
                            color = Black_Coral,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Normal,
                            fontFamily = spoqahansanseeo,
                            lineHeight = 20.dp.toSp()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GuideDiscountWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    descriptions: List<String>
) {
    Column(modifier) {
        Text(
            text = String.format("[%s]", title),
            modifier = Modifier.padding(bottom = 2.dp),
            color = Arsenic,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        descriptions.forEach { description ->
            Row(
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(4.dp)
                        .background(Black_Coral, CircleShape)
                )
                Text(
                    text = description,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth(),
                    color = Black_Coral,
                    fontSize = 14.dp.toSp(),
                    fontWeight = FontWeight.Normal,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 20.dp.toSp()
                )
            }
        }
    }
}
