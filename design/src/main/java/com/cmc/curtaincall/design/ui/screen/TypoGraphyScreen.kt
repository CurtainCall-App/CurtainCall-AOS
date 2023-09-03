package com.cmc.curtaincall.design.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithBack
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.Cetacean_Blue
import com.cmc.curtaincall.common.design.theme.Me_Pink
import com.cmc.curtaincall.common.design.theme.White
import com.cmc.curtaincall.common.design.theme.spoqahansanseeo

@Composable
internal fun TypoGraphyScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBack(
                title = "Typography",
                containerColor = Cetacean_Blue,
                contentColor = White,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        TypoGraphyContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Cetacean_Blue)
        )
    }
}

@Composable
private fun TypoGraphyContent(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            item {
                Column {
                    Text(
                        text = "Spoqa Han Sans Neo",
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp),
                        color = White,
                        fontSize = 22.dp.toSp(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = spoqahansanseeo
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Me_Pink, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 6.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Style",
                                    color = White,
                                    fontSize = 14.dp.toSp(),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = spoqahansanseeo
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Me_Pink, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 6.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Weight",
                                    color = White,
                                    fontSize = 14.dp.toSp(),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = spoqahansanseeo
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(Me_Pink, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 6.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Size",
                                    color = White,
                                    fontSize = 14.dp.toSp(),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = spoqahansanseeo
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Heading 1",
                                color = White,
                                fontSize = 24.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 24.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "24",
                                color = White,
                                fontSize = 24.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Heading 2",
                                color = White,
                                fontSize = 22.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 22.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "22",
                                color = White,
                                fontSize = 22.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 1",
                                color = White,
                                fontSize = 20.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 20.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "20",
                                color = White,
                                fontSize = 20.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 1",
                                color = White,
                                fontSize = 18.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 18.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "18",
                                color = White,
                                fontSize = 18.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 2",
                                color = White,
                                fontSize = 17.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 17.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "17",
                                color = White,
                                fontSize = 17.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 3",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bold",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "16",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 4",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "16",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Body 1",
                                color = White,
                                fontSize = 15.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "16",
                                color = White,
                                fontSize = 16.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Body 2",
                                color = White,
                                fontSize = 15.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 15.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "15",
                                color = White,
                                fontSize = 15.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Body3",
                                color = White,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "14",
                                color = White,
                                fontSize = 14.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Body 4",
                                color = White,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "13",
                                color = White,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Sub Title 4",
                                color = White,
                                fontSize = 12.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Medium",
                                color = White,
                                fontSize = 12.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "12",
                                color = White,
                                fontSize = 12.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(2f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "Caption",
                                color = White,
                                fontSize = 10.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Normal",
                                color = White,
                                fontSize = 10.dp.toSp(),
                                fontWeight = FontWeight.Normal,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "10",
                                color = White,
                                fontSize = 10.dp.toSp(),
                                fontWeight = FontWeight.Bold,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                }
            }
        }
    }
}
