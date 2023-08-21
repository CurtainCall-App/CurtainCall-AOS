package com.cmc.curtaincall.feature.partymember.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.component.content.card.PartyType
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Bright_Gray
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.Silver_Sand
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.partymember.ui.detail.PartyMemberDetailBody

@Composable
internal fun PartyMemberEditScreen(
    fromRecruitment: Boolean,
    fromParticipation: Boolean,
    partyType: PartyType,
    onBack: () -> Unit
) {
    var titleState by remember { mutableStateOf("") }
    var contentState by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBarWithBack(
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
                onClick = onBack
            )
        },
        floatingActionButton = {
            CurtainCallRoundedTextButton(
                onClick = { onBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                title = stringResource(R.string.mypage_writing_edit_complete),
                fontSize = 16.dp.toSp(),
                enabled = titleState.isNotEmpty() && contentState.isNotEmpty(),
                containerColor = if (titleState.isNotEmpty() && contentState.isNotEmpty()) Me_Pink else Bright_Gray,
                contentColor = if (titleState.isNotEmpty() && contentState.isNotEmpty()) White else Silver_Sand
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        PartyMemberEditContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            partyType = partyType,
            title = titleState,
            content = contentState,
            onTitleChange = { titleState = it },
            onContentChange = { contentState = it }
        )
    }
}

@Composable
private fun PartyMemberEditContent(
    modifier: Modifier = Modifier,
    partyType: PartyType,
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = null,
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
                        text = "고라파덕",
                        color = Chinese_Black,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = String.format("%s %s", "2023.06.07", "12:37"),
                        color = Silver_Sand,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            CurtainCallSingleLineTextField(
                value = title,
                onValueChange = { if (it.length <= 20) onTitleChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                fontSize = 15.dp.toSp(),
                shape = RoundedCornerShape(10.dp),
                containerColor = Cultured,
                contentColor = Nero,
                contentModifier = Modifier.padding(horizontal = 18.dp, vertical = 9.dp),
                placeholder = stringResource(R.string.partymember_create_write_title),
                placeholderColor = Silver_Sand
            )
            Text(
                text = String.format(
                    stringResource(R.string.partymember_create_write_number_of_text),
                    title.length,
                    20
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End),
                color = Roman_Silver,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
        ) {
            CurtainCallMultiLineTextField(
                value = content,
                onValueChange = { if (it.length <= 500) onContentChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 227.dp),
                fontSize = 15.dp.toSp(),
                shape = RoundedCornerShape(10.dp),
                containerColor = Cultured,
                contentColor = Nero,
                contentModifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                placeholder = stringResource(R.string.partymember_create_write_content),
                placeholderColor = Silver_Sand
            )
            Text(
                text = String.format(
                    stringResource(R.string.partymember_create_write_number_of_text),
                    content.length,
                    500
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End),
                color = Roman_Silver,
                fontSize = 12.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp, bottom = 24.dp)
                .height(12.dp)
                .background(Cultured)
        )
        PartyMemberDetailBody(
            partyType = partyType,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(Modifier.size(100.dp))
    }
}
