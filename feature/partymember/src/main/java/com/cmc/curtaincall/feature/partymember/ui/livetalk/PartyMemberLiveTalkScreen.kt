package com.cmc.curtaincall.feature.partymember.ui.livetalk

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.partymember.BuildConfig
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.compose.state.messages.list.MessageItemState
import io.getstream.chat.android.compose.ui.components.composer.MessageInput
import io.getstream.chat.android.compose.ui.messages.composer.MessageComposer
import io.getstream.chat.android.compose.ui.messages.header.MessageListHeader
import io.getstream.chat.android.compose.ui.messages.list.MessageList
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.theme.StreamColors
import io.getstream.chat.android.compose.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun PartyMemberLiveTalkScreen(
    partyMemberLiveTalkViewModel: PartyMemberLiveTalkViewModel = hiltViewModel(),
    chatClient: ChatClient,
    partyId: Int,
    onBack: () -> Unit
) {
    val user by partyMemberLiveTalkViewModel.userState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        partyMemberLiveTalkViewModel.tokenState.collectLatest {
            if(it.isNotEmpty()){
                chatClient.connectUser(
                    user = user,
                    token = it
                ).enqueue()
            }
        }
    }
    ChatTheme(
        isInDarkMode = false,
        colors = StreamColors.defaultColors().copy(
            appBackground = Cultured,
            barsBackground = Cultured,
            ownMessagesBackground = Color.Transparent,
            otherMessagesBackground = Color.Transparent
        )
    ) {
        PartyMemberLiveTalkContent(
            partyId = partyId,
            user = user,
            chatClient = chatClient,
            onBack = onBack
        )
    }
}

@Composable
private fun PartyMemberLiveTalkContent(
    partyId: Int,
    user: User,
    chatClient: ChatClient,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val messageFactory by lazy {
        MessagesViewModelFactory(
            context = context,
            channelId = "messaging:party-$partyId",
            chatClient = chatClient
        )
    }
    val messageListViewModel = composableViewModel<MessageListViewModel>(factory = messageFactory)
    val messageComposerViewModel = composableViewModel<MessageComposerViewModel>(factory = messageFactory)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Cultured),
        bottomBar = {
            MessageComposer(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 74.dp),
                viewModel = messageComposerViewModel,
                headerContent = {},
                footerContent = {},
                mentionPopupContent = {},
                commandPopupContent = {},
                integrations = {},
                input = { inputState ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                    ) {
                        CurtainCallMultiLineTextField(
                            value = inputState.inputValue,
                            onValueChange = { messageComposerViewModel.setMessageInput(it) },
                            modifier = Modifier.weight(1f),
                            fontSize = 14.dp.toSp(),
                            shape = RoundedCornerShape(20.dp),
                            containerColor = White,
                            contentColor = Cetacean_Blue,
                            contentModifier = Modifier.padding(horizontal = 18.dp, vertical = 7.dp),
                            placeholder = "메세지 입력..."
                        )
                        IconButton(
                            onClick = {
                                messageComposerViewModel.sendMessage(
                                    messageComposerViewModel.buildNewMessage(
                                        messageComposerViewModel.messageComposerState.value.inputValue
                                    )
                                )
                            },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(36.dp)
                                .background(White, CircleShape)
                        ) {
                            Icon(imageVector = Icons.Filled.Send, contentDescription = null)
                        }
                    }
                },
                trailingContent = {}
            )
        }
    ) { paddingValues ->
        PartyMemberLiveTalkMessageList(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            messageListViewModel = messageListViewModel,
            partyId = partyId,
            user = user,
            onBack = onBack
        )
    }
}

@Composable
private fun PartyMemberLiveTalkMessageList(
    modifier: Modifier = Modifier,
    messageListViewModel: MessageListViewModel,
    partyId: Int,
    user: User,
    onBack: () -> Unit
) {
    Column(modifier) {
        MessageListHeader(
            channel = Channel(
                cid = "messaging:party-$partyId",
                id = "party-$partyId"
            ),
            currentUser = user,
            modifier = Modifier.fillMaxWidth(),
            color = Cultured,
            leadingContent = {},
            centerContent = {
                Column(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 18.dp)
                            .padding(horizontal = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { onBack() },
                            tint = Nero
                        )
                        Column(
                            modifier = Modifier.padding(start = 6.dp, top = 1.dp)
                        ) {
                            Text(
                                text = "리어왕 밥메이트",
                                modifier = Modifier.padding(start = 6.dp),
                                color = Chinese_Black,
                                fontSize = 17.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                            Text(
                                text = "일시 | 2023.06.24(수) 오후 7:30",
                                color = Roman_Silver,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                }
            },
            trailingContent = {}
        )
        MessageList(
            viewModel = messageListViewModel,
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            emptyContent = {},
            loadingContent = {}
        ) {
            if (it is MessageItemState) {
                val message = it.message
                if (message.user.name == user.name) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .padding(end = 20.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = message.createdAt.toString(),
                                color = Roman_Silver,
                                fontSize = 10.dp.toSp()
                            )
                            Box(
                                modifier = Modifier
                                    .background(Me_Pink, RoundedCornerShape(6.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = message.text,
                                    color = White,
                                    fontSize = 14.dp.toSp(),
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = spoqahansanseeo
                                )
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .padding(start = 20.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        AsyncImage(
                            model = message.user.image,
                            contentDescription = null,
                            error = painterResource(R.drawable.ic_default_profile),
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Column(Modifier.padding(start = 12.dp)) {
                            Text(
                                text = message.user.name,
                                modifier = Modifier.padding(top = 5.dp),
                                color = Chinese_Black,
                                fontSize = 13.dp.toSp()
                            )
                            Row(
                                modifier = Modifier.padding(top = 4.dp),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(Cultured, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 12.dp, vertical = 6.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        text = message.text,
                                        color = Nero,
                                        fontSize = 14.dp.toSp()
                                    )
                                }
                                Text(
                                    text = message.createdAt.toString(),
                                    modifier = Modifier.padding(start = 8.dp),
                                    color = Roman_Silver,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified VM : ViewModel> composableViewModel(
    key: String? = null,
    factory: ViewModelProvider.Factory? = null
): VM = viewModel(
    VM::class.java,
    LocalContext.current as ComponentActivity,
    key,
    factory
)