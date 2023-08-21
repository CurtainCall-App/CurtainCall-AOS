package com.cmc.curtaincall.common.design.component.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOnlyAction(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { },
        modifier = modifier,
        actions = {
            IconButton(
                onClick = onClick,
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBack(
    title: String,
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    containerColor: Color,
    contentColor: Color,
    placeholder: String = "",
    onClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = contentColor
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Cultured, RoundedCornerShape(8.dp))
                        .padding(start = 12.dp)
                        .height(36.dp),
                    textStyle = TextStyle(
                        color = Nero,
                        fontSize = 16.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    ),
                    singleLine = true,
                    maxLines = 1,
                    decorationBox = { innerTextField ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                                if (value.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        color = Silver_Sand,
                                        fontSize = 16.dp.toSp(),
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = spoqahansanseeo
                                    )
                                }
                            }
                            IconButton(onClick = { onValueChange("") }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_close),
                                    contentDescription = null,
                                    modifier = Modifier.size(22.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 13.dp, bottom = 5.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarOnlySearch(
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBarWithBack(
    title: String,
    containerColor: Color,
    contentColor: Color,
    tint: Color,
    onBack: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreTopAppBarWithBack(
    title: String,
    containerColor: Color,
    contentColor: Color,
    tint: Color,
    onBack: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_more_vert),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = tint
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearch(
    title: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 18.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = avenirnext
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        actions = {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithReportAction(
    title: String,
    containerColor: Color,
    contentColor: Color,
    onBack: () -> Unit = {},
    onAction: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        actions = {
            IconButton(onClick = onAction) {
                Icon(
                    painter = painterResource(R.drawable.ic_report),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp, 24.dp),
                    tint = Silver_Sand
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithClose(
    title: String,
    containerColor: Color,
    contentColor: Color,
    onClose: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                fontSize = 17.dp.toSp(),
                fontWeight = FontWeight.Bold,
                fontFamily = spoqahansanseeo
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(containerColor)
            .padding(top = 20.dp, bottom = 10.dp),
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(R.drawable.ic_appbar_close),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = containerColor)
    )
}
