package com.cmc.curtaincall.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cmc.curtaincall.AppNavHost
import com.cmc.curtaincall.BuildConfig
import com.cmc.curtaincall.common.design.theme.CurtainCallTheme
import com.cmc.curtaincall.common.design.theme.NoRippleTheme
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CurtainCallApp(chatClient = createChatClient(this))
        }
    }
}

@Composable
private fun CurtainCallApp(chatClient: ChatClient) {
    CurtainCallTheme {
        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(chatClient = chatClient)
            }
        }
    }
}

private fun createChatClient(context: Context): ChatClient {
    val pluginFactory = StreamOfflinePluginFactory(
        config = Config(
            backgroundSyncEnabled = true,
            userPresence = true,
            persistenceEnabled = true,
            uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING
        ),
        appContext = context
    )

    return ChatClient.Builder(BuildConfig.GET_STREAM_API_KEY, context)
        .withPlugin(pluginFactory)
        .logLevel(if (BuildConfig.DEBUG) ChatLogLevel.DEBUG else ChatLogLevel.ERROR)
        .build()
}
