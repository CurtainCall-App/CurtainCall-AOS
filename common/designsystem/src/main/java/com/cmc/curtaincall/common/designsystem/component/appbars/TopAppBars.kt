package com.cmc.curtaincall.common.designsystem.component.appbars

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.dimension.Paddings
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black
import com.cmc.curtaincall.common.designsystem.theme.CurtainCallTheme
import com.cmc.curtaincall.common.designsystem.theme.avenirnext

private const val TopAppBarBackIconDescription = "Go Back"
private val TopAppBarBackIconSize = 24.dp
private val TopAppBarBackLeftPadding = 10.dp
private val TopAppBarBackRightPadding = 12.dp

private sealed class TopAppBarType(
    open val title: String
) {
    data class Default(
        override val title: String
    ) : TopAppBarType(title)

    data class Back(
        override val title: String,
        val onBack: () -> Unit = {}
    ) : TopAppBarType(title)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    type: TopAppBarType
) {
    if (type is TopAppBarType.Default) {
        TopAppBar(
            title = {
                Text(
                    text = type.title,
                    color = Black,
                    fontSize = 17.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = avenirnext
                )
            },
            modifier = modifier,
            windowInsets = WindowInsets(left = Paddings.small)
        )
    } else {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = type.title,
                    style = CurtainCallTheme.typography.subTitle3
                )
            },
            modifier = modifier,
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = TopAppBarBackIconDescription,
                    modifier = Modifier.size(TopAppBarBackIconSize),
                    tint = Color.Unspecified
                )
            },
            actions = {},
            windowInsets = WindowInsets(
                left = TopAppBarBackLeftPadding,
                right = TopAppBarBackRightPadding
            )
        )
    }
}

@Composable
fun DefaultTopAppBar(
    modifier: Modifier = Modifier
) {
    BaseTopAppBar(
        modifier = modifier,
        type = TopAppBarType.Default(
            title = stringResource(R.string.default_top_appbar_title)
        )
    )
}

@Composable
fun TopAppBarWithBack(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit = {}
) {
    BaseTopAppBar(
        modifier = modifier,
        type = TopAppBarType.Back(
            title = title,
            onBack = onBack
        )
    )
}
