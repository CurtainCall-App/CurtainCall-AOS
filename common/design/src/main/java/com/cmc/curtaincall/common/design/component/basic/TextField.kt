package com.cmc.curtaincall.common.design.component.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cultured
import com.cmc.curtaincall.common.design.theme.Roman_Silver
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
fun CurtainCallMultiLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    contentModifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: Color = contentColor,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(containerColor, shape)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        textStyle = TextStyle(
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo,
            lineHeight = 16.dp.toSp()
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = contentModifier,
                contentAlignment = Alignment.TopStart
            ) {
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    )
}

@Composable
fun CurtainCallSingleLineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    contentModifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: Color = contentColor,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.background(containerColor, shape),
        textStyle = TextStyle(
            color = contentColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            fontFamily = spoqahansanseeo

        ),
        singleLine = true,
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = contentModifier,
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CurtainCallSingleLineTextFieldPreview() {
    CurtainCallSingleLineTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        fontSize = 16.dp.toSp(),
        shape = RoundedCornerShape(10.dp),
        containerColor = Cultured,
        contentColor = Roman_Silver,
        contentModifier = Modifier.padding(horizontal = 20.dp),
        placeholder = stringResource(R.string.signup_input_nickname)
    )
}
