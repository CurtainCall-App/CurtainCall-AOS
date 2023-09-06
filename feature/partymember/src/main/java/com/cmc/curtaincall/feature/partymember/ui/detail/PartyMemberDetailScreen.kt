package com.cmc.curtaincall.feature.partymember.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.DottedLine
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithDelete
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithReportAction
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.component.dialog.CurtainCallBasicDialog
import com.cmc.curtaincall.common.design.component.dialog.CurtainCallConfirmDialog
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*
import com.cmc.curtaincall.common.utility.extensions.toChangeDate
import com.cmc.curtaincall.common.utility.extensions.toChangeFullDate
import com.cmc.curtaincall.common.utility.extensions.toTime
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyMemberDetailScreen(
    partyMemberDetailViewModel: PartyMemberDetailViewModel = hiltViewModel(),
    partyId: Int,
    isParticipation: Boolean = false,
    myWriting: Boolean = false,
    fromRecruitment: Boolean = false,
    fromParticipation: Boolean = false,
    partyType: PartyType,
    onNavigateReport: (Int, String) -> Unit,
    onNavigateLiveTalk: () -> Unit,
    onBack: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(White)

    var isShowDialog by remember { mutableStateOf(false) }
    var isShowRemoveDialog by remember { mutableStateOf(false) }
    var isParticipationState by remember { mutableStateOf(isParticipation) }
    val partyMemberDetailState by partyMemberDetailViewModel.uiState.collectAsStateWithLifecycle()

    if (isShowDialog) {
        CurtainCallBasicDialog(
            title = stringResource(R.string.dialog_partymember_participate_title),
            description = stringResource(R.string.dialog_partymember_participate_description),
            dismissText = stringResource(R.string.dialog_partymember_participate_dismiss),
            positiveText = stringResource(R.string.dialog_partymember_participate_positive),
            onDismiss = {
                isShowDialog = false
            },
            onPositive = {
                partyMemberDetailViewModel.participateParty(partyId)
                isShowDialog = false
            }
        )
    }

    if (isShowRemoveDialog) {
        CurtainCallConfirmDialog(
            title = stringResource(R.string.dialog_performance_review_remove_title),
            description = stringResource(R.string.dialog_partymember_detail_remove_description),
            positiveText = stringResource(R.string.dialog_partymember_detail_remove_positive_text),
            onDismiss = { isShowRemoveDialog = false },
            onPositive = {
                partyMemberDetailViewModel.deleteParty(partyId)
                isShowRemoveDialog = false
            }
        )
    }

    LaunchedEffect(partyMemberDetailViewModel) {
        partyMemberDetailViewModel.requestPartyDetail(partyId)
        partyMemberDetailViewModel.effects.collectLatest {
            when (it) {
                PartyMemberDetailSideEffect.SuccessDelete -> {
                    onBack()
                }

                PartyMemberDetailSideEffect.SuccessParticipation -> {
                    isParticipationState = true
                }
            }
        }
    }

    Scaffold(
        topBar = {
            if (myWriting) {
                TopAppBarWithDelete(
                    title = stringResource(
                        if (fromRecruitment) {
                            R.string.mypage_my_gathering_tab
                        } else if (fromParticipation) {
                            R.string.mypage_my_participation_tab
                        } else {
                            when (partyType) {
                                PartyType.PERFORMANCE -> R.string.partymember_performance_title
                                PartyType.MEAL -> R.string.partymember_restaurant_title
                                PartyType.ETC -> R.string.partymember_etc_title
                            }
                        }
                    ),
                    containerColor = White,
                    contentColor = Nero,
                    deleteColor = Black_Coral,
                    onBack = onBack,
                    onDelete = { isShowRemoveDialog = true }
                )
            } else {
                TopAppBarWithReportAction(
                    title = stringResource(
                        if (fromRecruitment) {
                            R.string.mypage_my_gathering_tab
                        } else if (fromParticipation) {
                            R.string.mypage_my_participation_tab
                        } else {
                            when (partyType) {
                                PartyType.PERFORMANCE -> R.string.partymember_performance_title
                                PartyType.MEAL -> R.string.partymember_restaurant_title
                                PartyType.ETC -> R.string.partymember_etc_title
                            }
                        }
                    ),
                    containerColor = White,
                    contentColor = Nero,
                    onBack = onBack,
                    onAction = {
                        onNavigateReport(
                            partyId,
                            "PARTY"
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            if (isParticipationState) {
                CurtainCallRoundedTextButton(
                    onClick = onNavigateLiveTalk,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(52.dp),
                    title = stringResource(
                        if (partyMemberDetailState.partyDetailModel.curMemberNum == partyMemberDetailState.partyDetailModel.maxMemberNum) {
                            R.string.partymember_detail_enter_livetalk
                        } else {
                            R.string.partymember_detail_joined
                        }
                    ),
                    fontSize = 16.dp.toSp(),
                    enabled = partyMemberDetailState.partyDetailModel.curMemberNum == partyMemberDetailState.partyDetailModel.maxMemberNum,
                    containerColor = if (partyMemberDetailState.partyDetailModel.curMemberNum == partyMemberDetailState.partyDetailModel.maxMemberNum) Me_Pink else Bright_Gray,
                    contentColor = if (partyMemberDetailState.partyDetailModel.curMemberNum == partyMemberDetailState.partyDetailModel.maxMemberNum) White else Silver_Sand
                )
            } else {
                CurtainCallRoundedTextButton(
                    onClick = { isShowDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(52.dp),
                    title = stringResource(
                        if (partyMemberDetailState.partyDetailModel.curMemberNum == partyMemberDetailState.partyDetailModel.maxMemberNum) {
                            R.string.partymember_detail_cant_join
                        } else {
                            R.string.partymember_detail_join
                        }
                    ),
                    fontSize = 16.dp.toSp(),
                    enabled = partyMemberDetailState.partyDetailModel.curMemberNum < partyMemberDetailState.partyDetailModel.maxMemberNum,
                    containerColor = if (partyMemberDetailState.partyDetailModel.curMemberNum < partyMemberDetailState.partyDetailModel.maxMemberNum) Me_Pink else Bright_Gray,
                    contentColor = if (partyMemberDetailState.partyDetailModel.curMemberNum < partyMemberDetailState.partyDetailModel.maxMemberNum) White else Silver_Sand
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        PartyMemberDetailContent(
            partyMemberDetailViewModel = partyMemberDetailViewModel,
            partyType = partyType,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White)
        )
    }
}

@Composable
private fun PartyMemberDetailContent(
    partyMemberDetailViewModel: PartyMemberDetailViewModel,
    partyType: PartyType,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val partyMemberDetailUiState by partyMemberDetailViewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier.verticalScroll(scrollState)) {
        PartyMemberDetailHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp),
            nickname = partyMemberDetailUiState.partyDetailModel.creatorNickname,
            createAtDate = partyMemberDetailUiState.partyDetailModel.createdAt.toChangeDate(),
            createAtTime = partyMemberDetailUiState.partyDetailModel.createdAt.toTime(),
            title = partyMemberDetailUiState.partyDetailModel.title,
            description = partyMemberDetailUiState.partyDetailModel.content
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Cultured)
        )
        PartyMemberDetailBody(
            partyType = partyType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp),
            showName = partyMemberDetailUiState.partyDetailModel.showName ?: "",
            curMemberNum = partyMemberDetailUiState.partyDetailModel.curMemberNum,
            maxMemberNum = partyMemberDetailUiState.partyDetailModel.maxMemberNum,
            showAt = partyMemberDetailUiState.partyDetailModel.showAt ?: "",
            facilityName = partyMemberDetailUiState.partyDetailModel.facilityName ?: ""
        )
    }
}

@Composable
fun PartyMemberDetailHeader(
    modifier: Modifier = Modifier,
    profileUrl: String? = null,
    nickname: String,
    createAtDate: String,
    createAtTime: String,
    title: String,
    description: String
) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = profileUrl,
                contentDescription = null,
                error = painterResource(R.drawable.ic_default_profile),
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = nickname,
                    color = Chinese_Black,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = String.format("%s %s", createAtTime, createAtDate),
                    color = Silver_Sand,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        Text(
            text = title,
            modifier = Modifier.padding(top = 26.dp),
            color = Nero,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 18.dp, bottom = 44.dp),
            color = Black_Coral,
            fontSize = 15.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 23.dp.toSp()
        )
    }
}

@Composable
fun PartyMemberDetailBody(
    partyType: PartyType,
    modifier: Modifier = Modifier,
    showName: String,
    curMemberNum: Int,
    maxMemberNum: Int,
    showAt: String,
    facilityName: String
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.partymember_detail),
            color = Gunmetal,
            fontSize = 16.dp.toSp(),
            fontWeight = FontWeight.Bold,
            fontFamily = spoqahansanseeo
        )
        if (partyType == PartyType.ETC) {
            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 20.dp),
                icon = painterResource(R.drawable.ic_calendar),
                category = stringResource(R.string.partymember_detail_date),
                description = showAt.ifEmpty { "날짜미정" }
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            PartyMemberDetailInfo(
                icon = painterResource(R.drawable.ic_party_state),
                category = stringResource(R.string.partymember_detail_join_state),
                description = "$curMemberNum/$maxMemberNum"
            )
        } else {
            PartyMemberDetailInfo(
                modifier = Modifier.padding(top = 20.dp),
                icon = painterResource(R.drawable.ic_dns),
                category = stringResource(R.string.partymember_detail_performance_title),
                description = showName
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            PartyMemberDetailInfo(
                icon = painterResource(R.drawable.ic_party_state),
                category = stringResource(R.string.partymember_detail_join_state),
                description = "$curMemberNum/$maxMemberNum"
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            PartyMemberDetailInfo(
                icon = painterResource(R.drawable.ic_calendar),
                category = stringResource(R.string.partymember_detail_performance_date),
                description = showAt.toChangeFullDate()
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            PartyMemberDetailInfo(
                icon = painterResource(R.drawable.ic_clock),
                category = stringResource(R.string.partymember_detail_performance_time),
                description = showAt.toTime()
            )
            DottedLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            PartyMemberDetailInfo(
                icon = painterResource(R.drawable.ic_location),
                category = stringResource(R.string.partymember_detail_performance_location),
                description = facilityName
            )
        }
    }
}

@Composable
private fun PartyMemberDetailInfo(
    modifier: Modifier = Modifier,
    icon: Painter,
    category: String,
    description: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Cultured, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified
            )
        }
        Text(
            text = category,
            modifier = Modifier
                .padding(start = 8.dp)
                .width(70.dp),
            color = Black_Coral,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
        Text(
            text = description,
            modifier = Modifier.weight(1f),
            color = Nero,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
