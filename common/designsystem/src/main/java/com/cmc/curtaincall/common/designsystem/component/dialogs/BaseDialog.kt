package com.cmc.curtaincall.common.designsystem.component.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.component.buttons.common.CurtainCallFilledButton
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.Grey6
import com.cmc.curtaincall.common.designsystem.theme.Grey8
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver

sealed class DialogType(
    open val title: String,
    open val action: () -> Unit
) {
    data class Select(
        override val title: String,
        val description: String? = null,
        val cancelButtonText: String,
        val actionButtonText: String,
        override val action: () -> Unit,
        val cancel: () -> Unit = {}
    ) : DialogType(title, action)

    data class Confirm(
        override val title: String,
        val description: String? = null,
        val actionText: String,
        override val action: () -> Unit,
    ) : DialogType(title, action)
}

private val DialogOuterPadding = 30.dp
private val DialogTopPadding = 40.dp
private val DialogHorizontalPadding = 16.dp
private val DialogBottomPadding = 16.dp

@Composable
fun BaseDialog(
    type: DialogType
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DialogOuterPadding),
        shape = RoundedCornerShape(Paddings.xlarge),
        colors = CardDefaults.cardColors(contentColor = CurtainCallTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = DialogTopPadding)
                .padding(horizontal = DialogHorizontalPadding)
                .padding(bottom = DialogBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = type.title,
                modifier = Modifier.fillMaxWidth(),
                style = CurtainCallTheme.typography.subTitle3.copy(color = Black),
                textAlign = TextAlign.Center
            )
            when (type) {
                is DialogType.Select -> {
                    if (type.description != null) {
                        Text(
                            text = type.description,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            style = CurtainCallTheme.typography.body4.copy(color = Roman_Silver),
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = if (type.description == null) 40.dp else 30.dp)
                            .fillMaxWidth()
                            .height(46.dp)
                    ) {
                        CurtainCallFilledButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            text = type.cancelButtonText,
                            containerColor = Grey8,
                            contentColor = Grey6,
                            textStyle = CurtainCallTheme.typography.body2,
                            onClick = type.cancel
                        )
                        CurtainCallFilledButton(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                                .fillMaxHeight(),
                            text = type.actionButtonText,
                            containerColor = CurtainCallTheme.colors.primary,
                            contentColor = CurtainCallTheme.colors.onPrimary,
                            textStyle = CurtainCallTheme.typography.body2,
                            onClick = type.action
                        )
                    }
                }

                is DialogType.Confirm -> {
                    if (type.description != null) {
                        Text(
                            text = type.description,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            style = CurtainCallTheme.typography.body4.copy(color = Roman_Silver)
                        )
                    }
                    CurtainCallFilledButton(
                        modifier = Modifier
                            .padding(top = if (type.description == null) 40.dp else 30.dp)
                            .fillMaxWidth()
                            .height(46.dp),
                        text = type.actionText,
                        containerColor = CurtainCallTheme.colors.primary,
                        contentColor = CurtainCallTheme.colors.onPrimary,
                        textStyle = CurtainCallTheme.typography.body2,
                        onClick = type.action
                    )
                }
            }
        }
    }
}
