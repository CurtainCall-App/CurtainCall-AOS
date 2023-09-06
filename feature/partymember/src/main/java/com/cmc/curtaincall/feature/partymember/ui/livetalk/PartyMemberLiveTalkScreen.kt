package com.cmc.curtaincall.feature.partymember.ui.livetalk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cmc.curtaincall.common.design.theme.White
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

@Composable
fun PartyMemberLiveTalkScreen(
    partyMemberLiveTalkViewModel: PartyMemberLiveTalkViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val user by partyMemberLiveTalkViewModel.userState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val offlinePluginFactory = StreamOfflinePluginFactory(
        config = Config(
            backgroundSyncEnabled = true,
            userPresence = true,
            persistenceEnabled = true,
            uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
        ),
        appContext = context
    )
    val client = ChatClient.Builder("a9ttf54djcw5", context)
        .withPlugin(offlinePluginFactory)
        .logLevel(ChatLogLevel.ALL)
        .build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
    }
}
