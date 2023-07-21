package com.cmc.curtaincall.feature.partymember.ui.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.theme.*

fun LazyGridScope.showThirdStep(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    onChangeTitle: (String) -> Unit,
    onChangeContent: (String) -> Unit
) {
    item(span = { GridItemSpan(3) }) {
        Column(modifier = modifier) {
            TitleTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .padding(top = 15.dp),
                text = title,
                onChangeText = onChangeTitle
            )

            ContentTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .padding(top = 40.dp),
                text = content,
                onChangeText = onChangeContent
            )
        }
    }
}

@Composable
private fun ContentTextField(
    modifier: Modifier = Modifier,
    text: String,
    onChangeText: (String) -> Unit = {}
) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.partymember_create_write_content),
                color = Black_Pearl,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp, 22.dp)
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(20.dp))
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_essential),
                    color = Me_Pink,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        BasicTextField(
            value = text,
            onValueChange = onChangeText,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(227.dp)
                .background(Cultured, RoundedCornerShape(10.dp)),
            textStyle = TextStyle(
                color = Nero,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            ),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                        .padding(top = 12.dp),
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.partymember_create_write_content_placeholder),
                            color = Roman_Silver,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
private fun TitleTextField(
    modifier: Modifier = Modifier,
    text: String,
    onChangeText: (String) -> Unit = {}
) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.partymember_create_write_title),
                color = Black_Pearl,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp, 22.dp)
                    .border(BorderStroke(1.dp, Me_Pink), RoundedCornerShape(20.dp))
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.partymember_create_essential),
                    color = Me_Pink,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }

        BasicTextField(
            value = text,
            onValueChange = onChangeText,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(42.dp)
                .background(Cultured, RoundedCornerShape(10.dp)),
            textStyle = TextStyle(
                color = Nero,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            ),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(R.string.partymember_create_write_title_placeholder),
                            color = Roman_Silver,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
