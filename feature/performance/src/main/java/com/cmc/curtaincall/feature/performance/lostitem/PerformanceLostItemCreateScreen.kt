package com.cmc.curtaincall.feature.performance.lostitem

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.design.R
import com.cmc.curtaincall.common.design.component.basic.CurtainCallDropDownButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.design.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.design.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.design.component.basic.TopAppBarWithClose
import com.cmc.curtaincall.common.design.component.custom.CurtainCallTimePicker
import com.cmc.curtaincall.common.design.component.custom.SelectedDateCalender
import com.cmc.curtaincall.common.design.extensions.toSp
import com.cmc.curtaincall.common.design.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PerformanceLostItemCreateScreen(
    fromMyPage: Boolean = false,
    onNavigateUpload: () -> Unit,
    onBack: () -> Unit
) {
    var completeState by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBarWithClose(
                title = if (fromMyPage) {
                    stringResource(R.string.mypage_writing_find_lost_item_edit)
                } else {
                    stringResource(R.string.performance_find_lost_item_create)
                },
                containerColor = White,
                contentColor = Nero,
                onClose = onBack
            )
        },
        floatingActionButton = {
            CurtainCallRoundedTextButton(
                onClick = { onNavigateUpload() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                title = stringResource(R.string.performance_find_lost_item_create_write_complete),
                fontSize = 16.dp.toSp(),
                enabled = completeState,
                containerColor = if (completeState) Me_Pink else Bright_Gray,
                contentColor = if (completeState) White else Silver_Sand
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        PerformanceLostItemCreateContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            onCompleteChange = { completeState = it },
            onNavigateUpload = onNavigateUpload
        )
    }
}

@Composable
private fun PerformanceLostItemCreateContent(
    modifier: Modifier = Modifier,
    onCompleteChange: (Boolean) -> Unit,
    onNavigateUpload: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var lostItemName by remember { mutableStateOf("") } // 필수
    var lostItemType by remember { mutableStateOf("") } // 필수
    var detailPlaceAcquisition by remember { mutableStateOf("") }
    var dateAcquisition by remember { mutableStateOf("") } // 필수
    var timeAcquisition by remember { mutableStateOf("") }
    var significant by remember { mutableStateOf("") }

    var isSelectAttachment by remember { mutableStateOf(false) }
    var isClickLostItemType by remember { mutableStateOf(false) }
    var isClickDateAcquisition by remember { mutableStateOf(false) }
    var isClickTimeAcquisition by remember { mutableStateOf(false) }
    var isClickAttachmentFile by remember { mutableStateOf(false) }

    LaunchedEffect(lostItemName, lostItemType, dateAcquisition, isSelectAttachment) {
        onCompleteChange(
            listOf(lostItemName, lostItemType, dateAcquisition).all { it.isNotEmpty() } && isSelectAttachment
        )
    }

    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.padding(top = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(Modifier.width(84.dp)) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create_location),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(Me_Pink.copy(0.1f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "LG아트센터 서울",
                    color = Me_Pink,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo
                )
            }
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp),
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
                .padding(top = 144.dp),
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
                .padding(top = 198.dp),
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
                .padding(top = 252.dp),
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
                        "%d.%d.%d",
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
                .padding(top = 306.dp),
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
                        it.minute
                    )
                    isClickTimeAcquisition = false
                }
            )
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 360.dp),
            title = stringResource(R.string.performance_find_lost_item_create_significant),
            placeholder = stringResource(R.string.performance_find_lost_item_create_significant_placeholder),
            value = significant,
            onValueChange = { significant = it },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickTimeAcquisition = false
                isClickAttachmentFile = false
            },
            isSingleLine = false
        )
        LostItemAttachmentDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 546.dp),
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
            isSelectAttachment = isSelectAttachment,
            onSelectChange = { isSelectAttachment = it }
        )
    }
}

@Composable
private fun LostItemAttachmentDropDown(
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    onClickChange: (Boolean) -> Unit,
    isSelectAttachment: Boolean,
    onSelectChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var attachmentFile by remember { mutableStateOf<Bitmap?>(null) }
    val takePhotoFromAlbum = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { uri ->
            attachmentFile = uri.parseBitmap(context)
            onSelectChange(true)
        }
    }
    val takePhotoFromCameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        attachmentFile = bitmap
        onSelectChange(true)
    }

    Row(modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(84.dp)
        ) {
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
        Column(Modifier.fillMaxWidth()) {
            Box {
                if (isSelectAttachment) {
                    Box(Modifier.size(83.dp)) {
                        AsyncImage(
                            model = attachmentFile,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(22.dp)
                                .clickable { onSelectChange(false) },
                            tint = Color.Unspecified
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .background(Cultured, RoundedCornerShape(8.dp))
                            .border(BorderStroke(1.dp, if (isClicked) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp))
                            .padding(horizontal = 14.dp)
                            .clickable { onClickChange(isClicked.not()) },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = stringResource(R.string.performance_find_lost_item_create_attachment_placeholder),
                            color = Silver_Sand,
                            fontSize = 14.dp.toSp(),
                            fontWeight = FontWeight.Medium,
                            fontFamily = spoqahansanseeo
                        )
                    }
                    Text(
                        text = stringResource(R.string.performance_find_lost_item_create_attachment_limit),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 52.dp),
                        color = Roman_Silver,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
                    )
                    if (isClicked) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 52.dp)
                        ) {
                            Card(
                                modifier = Modifier.height(112.dp),
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
                                            onClick = { takePhotoFromAlbum.launch("image/*") },
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
                                            onClick = { takePhotoFromCameraLauncher.launch() },
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
            CurtainCallDropDownButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .clickable { onClickChange(isClicked.not()) },
                isClicked = isClicked,
                title = value.ifEmpty { placeholder },
                fontSize = 14.dp.toSp(),
                containerColor = Cultured,
                contentColor = if (value.isEmpty()) Silver_Sand else Nero,
                borderColor = Roman_Silver,
                radiusSize = 8.dp,
                contentModifier = Modifier
                    .padding(start = 14.dp, end = 9.dp)
                    .padding(vertical = 9.dp)
            )
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
    isSingleLine: Boolean = true
) {
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = if (isSingleLine) Alignment.CenterVertically else Alignment.Top
    ) {
        Row(
            modifier = Modifier
                .padding(top = if (isSingleLine) 0.dp else 12.dp)
                .width(84.dp)
        ) {
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
        if (isSingleLine) {
            CurtainCallSingleLineTextField(
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
                    .border(BorderStroke(1.dp, if (isFocused) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp)),
                fontSize = 14.dp.toSp(),
                shape = RoundedCornerShape(8.dp),
                containerColor = Cultured,
                contentColor = Nero,
                contentModifier = Modifier.padding(horizontal = 14.dp),
                placeholder = placeholder,
                placeholderColor = Silver_Sand
            )
        } else {
            CurtainCallMultiLineTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                        if (it.isFocused) onFocused()
                    }
                    .fillMaxWidth()
                    .height(174.dp)
                    .border(BorderStroke(1.dp, if (isFocused) Roman_Silver else Color.Transparent), RoundedCornerShape(8.dp)),
                fontSize = 14.dp.toSp(),
                shape = RoundedCornerShape(8.dp),
                containerColor = Cultured,
                contentColor = Nero,
                contentModifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                placeholder = placeholder,
                placeholderColor = Silver_Sand
            )
        }
    }
}

@Suppress("DEPRECATION", "NewApi")
private fun Uri.parseBitmap(context: Context): Bitmap {
    return when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) { // 28
        true -> {
            val source = ImageDecoder.createSource(context.contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        }

        else -> {
            MediaStore.Images.Media.getBitmap(context.contentResolver, this)
        }
    }
}
