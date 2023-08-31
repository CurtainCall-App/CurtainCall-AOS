package com.cmc.curtaincall.common.design.component.items

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.custom.RatingBar
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Black_Pearl
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Nero
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyReviewWriteItem(
    modifier: Modifier = Modifier,
    title: String,
    rating: Int,
    description: String,
    createdAtDate: String,
    createAtTime: String,
    onChangeWriting: () -> Unit = {},
    onRemoveWriting: () -> Unit = {}
) {
    var isClickMoreVert by remember { mutableStateOf(false) }
    var isTouchChangeButton by remember { mutableStateOf(false) }
    var isTouchRemoveButton by remember { mutableStateOf(false) }
    Box(modifier) {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = Nero,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Column(
                modifier = Modifier.zIndex(if (isClickMoreVert) 1f else 0f),
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_more_vert),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { isClickMoreVert = isClickMoreVert.not() },
                    tint = Nero
                )
                if (isClickMoreVert) {
                    Card(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(73.dp, 88.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(
                                    color = if (isTouchChangeButton) Cultured else White,
                                    RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                )
                                .pointerInteropFilter {
                                    when (it.action) {
                                        MotionEvent.ACTION_DOWN -> {
                                            isTouchChangeButton = true
                                        }

                                        MotionEvent.ACTION_MOVE,
                                        MotionEvent.ACTION_UP -> {
                                            isTouchChangeButton = false
                                            isClickMoreVert = false
                                            onChangeWriting()
                                        }

                                        else -> return@pointerInteropFilter false
                                    }
                                    true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.mypage_writing_edit),
                                color = Black_Pearl,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(
                                    color = if (isTouchRemoveButton) Cultured else White,
                                    RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                                )
                                .pointerInteropFilter {
                                    when (it.action) {
                                        MotionEvent.ACTION_DOWN -> {
                                            isTouchRemoveButton = true
                                        }

                                        MotionEvent.ACTION_MOVE,
                                        MotionEvent.ACTION_UP -> {
                                            isTouchRemoveButton = false
                                            isClickMoreVert = false
                                            onRemoveWriting()
                                        }

                                        else -> return@pointerInteropFilter false
                                    }
                                    true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.mypage_writing_remove),
                                color = Black_Pearl,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                }
            }
        }
        RatingBar(
            modifier = Modifier
                .padding(top = 36.dp)
                .size(14.dp),
            rating = rating
        )
        Text(
            text = description,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth(),
            color = Nero,
            fontSize = 14.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 22.dp.toSp(),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "$createdAtDate $createAtTime",
            modifier = Modifier.padding(top = 12.dp),
            color = Roman_Silver,
            fontSize = 12.dp.toSp(),
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo
        )
    }
}
