package com.cmc.curtaincall.feature.performance.ui.lostitem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.CurtainCallTimePicker
import com.cmc.curtaincall.common.design.component.SelectedDateCalender
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemCreateScreen(
    onNavigateUpload: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithClose(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                onClose = onBack
            )
        }
    ) { paddingValues ->
        PerformanceLostItemCreateContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            onNavigateUpload = onNavigateUpload
        )
    }
}

@Composable
private fun PerformanceLostItemCreateContent(
    modifier: Modifier = Modifier,
    onNavigateUpload: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var lostItemName by remember { mutableStateOf("") }
    var lostItemType by remember { mutableStateOf("") }
    var isClickLostItemType by remember { mutableStateOf(false) }
    var placeAcquisition by remember { mutableStateOf("") }
    var detailPlaceAcquisition by remember { mutableStateOf("") }
    var dateAcquisition by remember { mutableStateOf("") }
    var isClickDateAcquisition by remember { mutableStateOf(false) }
    var timeAcquisition by remember { mutableStateOf("") }
    var isClickTimeAcquisition by remember { mutableStateOf(false) }
    var significant by remember { mutableStateOf("") }
    var attachmentFile by remember { mutableStateOf("") }
    var isClickAttachmentFile by remember { mutableStateOf(false) }
    val isValidation = lostItemName.isNotEmpty() && lostItemType.isNotEmpty() && placeAcquisition.isNotEmpty() && dateAcquisition.isNotEmpty()

    Box(modifier) {
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.performance_find_lost_item_create_title),
            placeholder = stringResource(R.string.performance_find_lost_item_create_title_placeholder),
            isEssential = true,
            value = lostItemName,
            onValueChange = { lostItemName = it },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickAttachmentFile = false
                isClickTimeAcquisition = false
            }
        )
        LostItemTypeDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp)
                .padding(horizontal = 20.dp),
            isClicked = isClickLostItemType,
            onClickChange = { isClick ->
                isClickLostItemType = isClick
                if (isClick) {
                    focusManager.clearFocus()
                    isClickDateAcquisition = false
                    isClickAttachmentFile = false
                    isClickTimeAcquisition = false
                }
            },
            title = stringResource(R.string.performance_find_lost_item_create_classification),
            placeholder = stringResource(R.string.performance_find_lost_item_create_classification_placeholder),
            isEssential = true,
            value = lostItemType
        ) {
            LostItemTypeGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(328.dp)
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                itemModifier = Modifier.size(48.dp, 72.dp),
                onTypeChange = {
                    lostItemType = it.label
                    isClickLostItemType = false
                }
            )
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 144.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.performance_find_lost_item_create_place_of_acquisition),
            placeholder = stringResource(R.string.performance_find_lost_item_create_place_of_acquisition_placeholder),
            isEssential = true,
            value = placeAcquisition,
            onValueChange = { placeAcquisition = it },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickTimeAcquisition = false
                isClickAttachmentFile = false
            }
        )
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 198.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.performance_find_lost_item_create_place),
            placeholder = stringResource(R.string.performance_find_lost_item_create_place_placeholder),
            value = detailPlaceAcquisition,
            onValueChange = { detailPlaceAcquisition = it },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickTimeAcquisition = false
                isClickAttachmentFile = false
            }
        )
        LostItemTypeDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 252.dp)
                .padding(horizontal = 20.dp),
            isClicked = isClickDateAcquisition,
            onClickChange = { isClick ->
                isClickDateAcquisition = isClick
                if (isClick) {
                    focusManager.clearFocus()
                    isClickLostItemType = false
                    isClickTimeAcquisition = false
                    isClickAttachmentFile = false
                }
            },
            title = stringResource(R.string.performance_find_lost_item_create_acquistion_date),
            placeholder = stringResource(R.string.performance_find_lost_item_create_acquistion_date_placeholder),
            isEssential = true,
            value = dateAcquisition
        ) {
            SelectedDateCalender(
                modifier = Modifier.padding(top = 10.dp),
                onDateClick = {
                    dateAcquisition = String.format(
                        context.getString(R.string.partymember_create_calendar_date_format),
                        it.date.year,
                        it.date.month.value,
                        it.date.dayOfMonth
                    )
                    isClickDateAcquisition = false
                }
            )
        }
        LostItemTypeDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 306.dp)
                .padding(horizontal = 20.dp),
            isClicked = isClickTimeAcquisition,
            onClickChange = { isClick ->
                isClickTimeAcquisition = isClick
                if (isClick) {
                    focusManager.clearFocus()
                    isClickLostItemType = false
                    isClickDateAcquisition = false
                    isClickAttachmentFile = false
                }
            },
            title = stringResource(R.string.performance_find_lost_item_create_acquistion_time),
            placeholder = stringResource(R.string.performance_find_lost_item_create_acquistion_time_placeholder),
            value = timeAcquisition
        ) {
            CurtainCallTimePicker(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    timeAcquisition = String.format(
                        context.getString(R.string.performance_find_lost_item_create_time_format),
                        it.hour,
                        it.minute,
                        it.meridiem
                    )
                    isClickTimeAcquisition = false
                }
            )
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 360.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.performance_find_lost_item_create_significant),
            placeholder = stringResource(R.string.performance_find_lost_item_create_significant_placeholder),
            value = significant,
            onValueChange = { significant = it },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickTimeAcquisition = false
                isClickAttachmentFile = false
            }
        )
        LostItemAttachmentDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 414.dp)
                .padding(horizontal = 20.dp),
            isClicked = isClickAttachmentFile,
            onClickChange = { isClick ->
                isClickAttachmentFile = isClick
                if (isClick) {
                    focusManager.clearFocus()
                    isClickLostItemType = false
                    isClickDateAcquisition = false
                    isClickTimeAcquisition = false
                }
            },
            value = attachmentFile,
            onValueChange = { attachmentFile = it }
        )
        Button(
            onClick = { onNavigateUpload() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 19.dp)
                .height(52.dp),
            enabled = lostItemName.isNotEmpty() && lostItemType.isNotEmpty() &&
                placeAcquisition.isNotEmpty() && dateAcquisition.isNotEmpty(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isValidation) Me_Pink else Philippine_Gray
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create_write_complete),
                    color = if (isValidation) White else Silver_Sand,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
        }
    }
}

@Composable
private fun LostItemAttachmentDropDown(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    onClickChange: (Boolean) -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(Modifier.width(84.dp)) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create_attachment),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                Spacer(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(4.dp)
                        .background(Me_Pink, CircleShape)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .clickable { onClickChange(isClicked.not()) }
                    .border(BorderStroke(1.dp, if (isClicked) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp))
                    .background(Cultured, RoundedCornerShape(8.dp))
                    .padding(start = 14.dp, end = 9.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = value.ifEmpty { stringResource(R.string.performance_find_lost_item_create_attachment_placeholder) },
                        modifier = Modifier.weight(1f),
                        color = if (value.isNotEmpty()) Nero else Silver_Sand,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_dropdown),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
        if (isClicked) {
            Row {
                Spacer(Modifier.width(84.dp))
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .height(112.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape),
                                colors = IconButtonDefaults.iconButtonColors(containerColor = Cultured)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_select_image),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                            Text(
                                text = stringResource(R.string.performance_find_lost_item_create_select_image),
                                modifier = Modifier.padding(top = 8.dp),
                                color = Black_Pearl,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape),
                                colors = IconButtonDefaults.iconButtonColors(containerColor = Cultured)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_capture),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }
                            Text(
                                text = stringResource(R.string.performance_find_lost_item_create_capture),
                                modifier = Modifier.padding(top = 8.dp),
                                color = Black_Pearl,
                                fontSize = 13.dp.toSp(),
                                fontWeight = FontWeight.Medium,
                                fontFamily = spoqahansanseeo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LostItemTypeDropDown(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    onClickChange: (Boolean) -> Unit,
    title: String,
    placeholder: String,
    isEssential: Boolean = false,
    value: String,
    content: @Composable () -> Unit,
) {
    Column(modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(Modifier.width(84.dp)) {
                Text(
                    text = title,
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                if (isEssential) {
                    Spacer(
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .size(4.dp)
                            .background(Me_Pink, CircleShape)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .clickable { onClickChange(isClicked.not()) }
                    .border(BorderStroke(1.dp, if (isClicked) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp))
                    .background(Cultured, RoundedCornerShape(8.dp))
                    .padding(start = 14.dp, end = 9.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = value.ifEmpty { placeholder },
                        modifier = Modifier.weight(1f),
                        color = if (value.isNotEmpty()) Nero else Silver_Sand,
                        fontSize = 14.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_dropdown),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
        if (isClicked) {
            Row {
                Spacer(Modifier.width(84.dp))
                content()
            }
        }
    }
}

@Composable
private fun LostItemInfoTextField(
    modifier: Modifier = Modifier,
    title: String,
    placeholder: String,
    isEssential: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    onFocused: () -> Unit,
) {
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(Modifier.width(84.dp)) {
            Text(
                text = title,
                color = Nero,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            if (isEssential) {
                Spacer(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .size(4.dp)
                        .background(Me_Pink, CircleShape)
                )
            }
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (it.isFocused) onFocused()
                }
                .fillMaxWidth()
                .height(42.dp)
                .border(BorderStroke(1.dp, if (isFocused) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp))
                .background(Cultured, RoundedCornerShape(8.dp))
                .padding(horizontal = 14.dp),
            textStyle = TextStyle(
                color = Nero,
                fontSize = 14.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            ),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Silver_Sand,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarWithClose(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create),
                    color = Nero,
                    fontSize = 17.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp)
                    .clickable { onClose() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_appbar_close),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }
    )
}
