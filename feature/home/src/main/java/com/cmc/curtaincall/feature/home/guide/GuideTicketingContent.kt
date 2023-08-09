package com.cmc.curtaincall.feature.home.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Coral
import com.cmc.curtaincall.common.design.theme.Chinese_Black
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.home.guide.GuideConstants.after_ticketing_words
import com.cmc.curtaincall.feature.home.guide.GuideConstants.before_ticketing_words

@Composable
internal fun GuideBeforeTicketingContent() {
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState)
    ) {
        before_ticketing_words.keys.toList().forEachIndexed { index, key ->
            GuideTicketing(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                descriptions = before_ticketing_words[key] ?: listOf()
            )
            if (index < before_ticketing_words.keys.toList().lastIndex) {
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
internal fun GuideAfterTicketingContent() {
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState)
    ) {
        after_ticketing_words.keys.toList().forEachIndexed { index, key ->
            GuideTicketing(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                descriptions = after_ticketing_words[key] ?: listOf()
            )
            if (index < before_ticketing_words.keys.toList().lastIndex) {
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Cultured)
                )
            }
        }
    }
}

@Composable
private fun GuideTicketing(
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
        Row(Modifier.padding(top = 6.dp)) {
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
