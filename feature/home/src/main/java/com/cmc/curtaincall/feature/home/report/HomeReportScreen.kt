package com.cmc.curtaincall.feature.home.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithClose
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.domain.type.ReportType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeReportScreen(
    homeReportViewModel: HomeReportViewModel = hiltViewModel(),
    reportId: Int?,
    reportType: ReportType?,
    onNavigateHome: () -> Unit,
    onBack: () -> Unit
) {
    requireNotNull(reportId)
    requireNotNull(reportType)

    var step by remember { mutableIntStateOf(0) }
    val reportReason by homeReportViewModel.reportReason.collectAsStateWithLifecycle()

    LaunchedEffect(homeReportViewModel) {
        homeReportViewModel.isSuccessReport.collectLatest { isSuccessReport ->
            if (isSuccessReport) step++
        }
    }

    Scaffold(
        topBar = {
            if (step < 2) {
                TopAppBarWithClose(
                    title = stringResource(R.string.appbar_report_title),
                    containerColor = White,
                    contentColor = Nero,
                    onClose = {
                        if (step == 0) {
                            onBack()
                        } else {
                            step--
                        }
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CurtainCallRoundedTextButton(
                onClick = {
                    if (step == 0) {
                        step++
                    } else if (step == 1) {
                        homeReportViewModel.requestReport(
                            reportId = reportId,
                            type = reportType
                        )
                    } else {
                        onNavigateHome()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                title = when (step) {
                    0 -> "다음"
                    1 -> "신고하기"
                    else -> "홈으로 이동"
                },
                fontSize = 16.dp.toSp(),
                enabled = when (step) {
                    0 -> reportReason != HomeReportReason.NONE
                    else -> true
                },
                containerColor = when (step) {
                    0 -> if (reportReason == HomeReportReason.NONE) Bright_Gray else Me_Pink
                    else -> Me_Pink
                },
                contentColor = when (step) {
                    0 -> if (reportReason == HomeReportReason.NONE) Silver_Sand else White
                    else -> White
                }
            )
        }
    ) { paddingValues ->
        if (step < 2) {
            HomeReportContent(
                homeReportViewModel = homeReportViewModel,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                reportReason = reportReason,
                step = step
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(280f))
                Icon(
                    painter = painterResource(R.drawable.ic_complete),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(R.string.home_report_complete),
                    modifier = Modifier.padding(top = 8.dp),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                Spacer(Modifier.weight(360f))
            }
        }
    }
}

@Composable
private fun HomeReportContent(
    homeReportViewModel: HomeReportViewModel,
    modifier: Modifier = Modifier,
    reportReason: HomeReportReason,
    step: Int
) {
    val verticalScrollState = rememberScrollState()
    val content by homeReportViewModel.content.collectAsStateWithLifecycle()
    Column(modifier.verticalScroll(verticalScrollState)) {
        if (step == 0) {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.home_report_reason_title),
                    color = Chinese_Black,
                    fontSize = 18.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    lineHeight = 26.dp.toSp()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.SPAM) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.SPAM) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.SPAM) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.SPAM.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.HATE_SPEECH) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.HATE_SPEECH) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.HATE_SPEECH) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.HATE_SPEECH.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.ILLEGAL) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.ILLEGAL) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.ILLEGAL) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.ILLEGAL.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.BAD_MANNERS) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.BAD_MANNERS) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.BAD_MANNERS) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.BAD_MANNERS.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.HARMFUL_TO_TEENAGER) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.HARMFUL_TO_TEENAGER) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.HARMFUL_TO_TEENAGER) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.HARMFUL_TO_TEENAGER.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.PERSONAL_INFORMATION_DISCLOSURE) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.PERSONAL_INFORMATION_DISCLOSURE) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.PERSONAL_INFORMATION_DISCLOSURE) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.PERSONAL_INFORMATION_DISCLOSURE.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 19.dp)
                        .clickable { homeReportViewModel.changeReportReason(HomeReportReason.ETC) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { homeReportViewModel.changeReportReason(HomeReportReason.ETC) },
                        modifier = Modifier
                            .background(if (reportReason == HomeReportReason.ETC) Me_Pink else Bright_Gray, CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = HomeReportReason.ETC.value,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Black_Coral,
                        fontSize = 15.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.home_report_reason_description_title),
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Chinese_Black,
                    fontSize = 18.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
                CurtainCallMultiLineTextField(
                    value = content,
                    onValueChange = {
                        if (it.length <= 400) homeReportViewModel.setContent(it)
                    },
                    modifier = Modifier
                        .padding(top = 17.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .heightIn(min = 222.dp),
                    fontSize = 15.dp.toSp(),
                    shape = RoundedCornerShape(10.dp),
                    containerColor = Cultured,
                    contentColor = Nero,
                    contentModifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                    placeholder = stringResource(R.string.mypage_setting_remove_member_reason_example),
                    placeholderColor = Silver_Sand
                )
                Text(
                    text = String.format(
                        stringResource(R.string.mypage_setting_remove_member_reason_text_count),
                        content.length,
                        400
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    color = Roman_Silver,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    textAlign = TextAlign.End
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .fillMaxWidth()
                        .height(7.dp)
                        .background(Cultured)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error_report),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = stringResource(R.string.home_report_rule1),
                        modifier = Modifier.padding(start = 8.dp),
                        color = Black_Coral,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 20.dp.toSp()
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error_report),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = stringResource(R.string.home_report_rule2),
                        modifier = Modifier.padding(start = 8.dp),
                        color = Black_Coral,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 20.dp.toSp()
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_error_report),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = stringResource(R.string.home_report_rule3),
                        modifier = Modifier.padding(start = 8.dp),
                        color = Black_Coral,
                        fontSize = 13.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo,
                        lineHeight = 20.dp.toSp()
                    )
                }
            }
        }
    }
}
