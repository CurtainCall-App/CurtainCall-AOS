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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Coral
import com.cmc.curtaincall.common.designsystem.theme.Chinese_Black
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.feature.home.guide.HomeGuideConstants.audience_words
import com.cmc.curtaincall.feature.home.guide.HomeGuideConstants.etc_words
import com.cmc.curtaincall.feature.home.guide.HomeGuideConstants.performance_words
import com.cmc.curtaincall.feature.home.guide.HomeGuideConstants.theater_words
import com.cmc.curtaincall.feature.home.guide.HomeGuideConstants.ticketing_words

@Composable
internal fun HomeGuideTotalExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        val totalList = (ticketing_words + performance_words + theater_words + audience_words + etc_words)
        totalList.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = totalList[key] ?: ""
            )
            if (index < totalList.keys.toList().lastIndex) {
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
internal fun HomeGuideTicketingExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        ticketing_words.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = ticketing_words[key] ?: ""
            )
            if (index < ticketing_words.keys.toList().lastIndex) {
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
internal fun HomeGuidePerformanceExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        performance_words.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = performance_words[key] ?: ""
            )
            if (index < performance_words.keys.toList().lastIndex) {
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
internal fun HomeGuideTheaterExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        theater_words.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = theater_words[key] ?: ""
            )
            if (index < theater_words.keys.toList().lastIndex) {
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
internal fun HomeGuideAudienceExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        audience_words.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = audience_words[key] ?: ""
            )
            if (index < audience_words.keys.toList().lastIndex) {
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
internal fun HomeGuideEtcExpressionContent() {
    Column(Modifier.fillMaxSize()) {
        etc_words.keys.toList().forEachIndexed { index, key ->
            GuidExpression(
                modifier = Modifier.fillMaxWidth(),
                num = index + 1,
                title = key,
                description = etc_words[key] ?: ""
            )
            if (index < etc_words.keys.toList().lastIndex) {
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
private fun GuidExpression(
    modifier: Modifier,
    num: Int,
    title: String,
    description: String
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
        Row(Modifier.padding(top = 8.dp)) {
            Spacer(Modifier.width(42.dp))
            Text(
                text = description,
                modifier = Modifier.fillMaxWidth(),
                color = Black_Coral,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Normal,
                fontFamily = spoqahansanseeo,
                lineHeight = 20.dp.toSp()
            )
        }
    }
}
