package com.cmc.curtaincall.common.designsystem.component.appbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.avenirnext

private const val TopAppBarBackIconDescription = "Go Back"
private val TopAppBarBackIconSize = 24.dp
private val TopAppBarBackLeftPadding = 10.dp
private val TopAppBarBackRightPadding = 12.dp
private val TopAppBarHeight = 56.dp

private sealed class TopAppBarType(
    open val title: String,
    open val onBack: (() -> Unit)? = null
) {
    data class Default(
        override val title: String
    ) : TopAppBarType(title)

    data class Back(
        override val title: String,
        override val onBack: (() -> Unit)?
    ) : TopAppBarType(title, onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaseTopAppBar(
    type: TopAppBarType
) {
    if (type is TopAppBarType.Default) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TopAppBarHeight),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = type.title,
                        style = CurtainCallTheme.typography.subTitle3.copy(
                            fontFamily = avenirnext
                        )
                    )
                }
            },
            windowInsets = WindowInsets(left = Paddings.small),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = CurtainCallTheme.colors.background
            )
        )
    } else {
        CenterAlignedTopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TopAppBarHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = type.title,
                        style = CurtainCallTheme.typography.subTitle3
                    )
                }
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = TopAppBarBackIconDescription,
                    modifier = Modifier
                        .size(TopAppBarBackIconSize)
                        .clickable { type.onBack?.invoke() },
                    tint = Color.Unspecified
                )
            },
            actions = {},
            windowInsets = WindowInsets(
                left = TopAppBarBackLeftPadding,
                right = TopAppBarBackRightPadding
            ),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = CurtainCallTheme.colors.background
            )
        )
    }
}

@Composable
fun CurtainCallDefaultTopAppBar() {
    BaseTopAppBar(
        type = TopAppBarType.Default(
            title = stringResource(R.string.default_top_appbar_title)
        )
    )
}

@Composable
fun CurtainCallTopAppBarWithBack(
    title: String,
    onBack: () -> Unit = {}
) {
    BaseTopAppBar(
        type = TopAppBarType.Back(
            title = title,
            onBack = onBack
        )
    )
}
