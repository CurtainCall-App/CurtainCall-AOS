package com.cmc.curtaincall.feature.show.lostproperty.create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cmc.curtaincall.common.designsystem.R
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallDropDownButton
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallMultiLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallRoundedTextButton
import com.cmc.curtaincall.common.designsystem.component.basic.CurtainCallSingleLineTextField
import com.cmc.curtaincall.common.designsystem.component.basic.TopAppBarWithClose
import com.cmc.curtaincall.common.designsystem.component.custom.CurtainCallTimePicker
import com.cmc.curtaincall.common.designsystem.component.custom.PreviousDateCalendar
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyTypeGrid
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyTypeItem
import com.cmc.curtaincall.common.designsystem.extensions.toSp
import com.cmc.curtaincall.common.designsystem.theme.Black_Pearl
import com.cmc.curtaincall.common.designsystem.theme.Bright_Gray
import com.cmc.curtaincall.common.designsystem.theme.Cultured
import com.cmc.curtaincall.common.designsystem.theme.Me_Pink
import com.cmc.curtaincall.common.designsystem.theme.Nero
import com.cmc.curtaincall.common.designsystem.theme.Roman_Silver
import com.cmc.curtaincall.common.designsystem.theme.Silver_Sand
import com.cmc.curtaincall.common.designsystem.theme.White
import com.cmc.curtaincall.common.designsystem.theme.spoqahansanseeo
import com.cmc.curtaincall.common.navigation.destination.DEFAULT_LOST_PROPERTY_ID
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@Composable
internal fun ShowLostPropertyCreateScreen(
    showLostPropertyCreateViewModel: ShowLostPropertyCreateViewModel = hiltViewModel(),
    lostPropertyId: Int?,
    facilityId: String?,
    facilityName: String?,
    onNavigateDetail: (Int, Boolean) -> Unit = { _, _ -> },
    onBack: () -> Unit = {}
) {
    requireNotNull(lostPropertyId)
    requireNotNull(facilityId)
    requireNotNull(facilityName)

    val isEditMode = lostPropertyId != DEFAULT_LOST_PROPERTY_ID
    var completeState by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBarWithClose(
                title = if (isEditMode) {
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
                onClick = {
                    if (isEditMode) {
                        showLostPropertyCreateViewModel.updateLostItem(lostPropertyId)
                    } else {
                        showLostPropertyCreateViewModel.createLostItem(facilityId)
                    }
                },
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
        ShowLostPropertyCreateContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(White),
            facilityName = facilityName,
            onCompleteChange = { completeState = it }
        )
    }

    LaunchedEffect(Unit) {
        showLostPropertyCreateViewModel.isCompleted.collectLatest { isComplete ->
            if (isComplete && isEditMode.not()) {
                onNavigateDetail(showLostPropertyCreateViewModel.lostPropertyId.value, true)
            }
        }
    }
}

@Composable
private fun ShowLostPropertyCreateContent(
    modifier: Modifier = Modifier,
    showLostPropertyCreateViewModel: ShowLostPropertyCreateViewModel = hiltViewModel(),
    facilityName: String,
    onCompleteChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val lostItemCreateUiState by showLostPropertyCreateViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(lostItemCreateUiState) {
        onCompleteChange(
            listOf(
                lostItemCreateUiState.title,
                lostItemCreateUiState.lostPropertyType.code,
                lostItemCreateUiState.foundDate
            ).all { it.isNotEmpty() } && lostItemCreateUiState.imageId != Int.MIN_VALUE
        )
    }

    var isSelectAttachment by remember { mutableStateOf(false) }
    var isClickLostItemType by remember { mutableStateOf(false) }
    var isClickDateAcquisition by remember { mutableStateOf(false) }
    var isClickTimeAcquisition by remember { mutableStateOf(false) }
    var isClickAttachmentFile by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(Modifier.width(76.dp)) {
                Text(
                    text = stringResource(R.string.performance_find_lost_item_create_location),
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                    text = facilityName,
                    color = Me_Pink,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = spoqahansanseeo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 86.dp),
            title = stringResource(R.string.performance_find_lost_item_create_title),
            placeholder = stringResource(R.string.performance_find_lost_item_create_title_placeholder),
            value = lostItemCreateUiState.title,
            onValueChange = {
                if (it.length < 20) showLostPropertyCreateViewModel.setTitle(it)
            },
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
                .padding(top = 142.dp),
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
            value = when (lostItemCreateUiState.lostPropertyType) {
                LostPropertyType.BAG -> LostPropertyType.BAG.label
                LostPropertyType.WALLET -> LostPropertyType.WALLET.label
                LostPropertyType.MONEY -> LostPropertyType.MONEY.label
                LostPropertyType.CARD -> LostPropertyType.CARD.label
                LostPropertyType.JEWELRY -> LostPropertyType.JEWELRY.label
                LostPropertyType.ELECTRONICS -> LostPropertyType.ELECTRONICS.label
                LostPropertyType.BOOKS -> LostPropertyType.BOOKS.label
                LostPropertyType.CLOTHES -> LostPropertyType.CLOTHES.label
                LostPropertyType.ETC -> LostPropertyType.ETC.label
                else -> ""
            }
        ) {
            LostPropertyTypeGrid(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(431.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 23.dp),
                verticalArrangement = Arrangement.spacedBy(27.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                LostPropertyTypeItem(
                    modifier = Modifier.size(50.dp, 76.dp),
                    lostPropertyType = it,
                    onTypeChange = { lostPropertyType ->
                        showLostPropertyCreateViewModel.setPropertyType(lostPropertyType)
                        isClickLostItemType = false
                    }
                )
            }
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 198.dp),
            title = stringResource(R.string.performance_find_lost_item_create_place),
            placeholder = stringResource(R.string.performance_find_lost_item_create_place_placeholder),
            isEssential = false,
            value = lostItemCreateUiState.foundPlaceDetail,
            onValueChange = {
                if (it.length < 30) showLostPropertyCreateViewModel.setFoundPlaceDetail(it)
            },
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
                .padding(top = 254.dp),
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
            value = lostItemCreateUiState.foundDate
        ) {
            PreviousDateCalendar(
                modifier = Modifier.padding(top = 10.dp),
                onDateClick = {
                    showLostPropertyCreateViewModel.setFoundDate(
                        foundDate = String.format(
                            "%d-%02d-%02d",
                            it.date.year,
                            it.date.month.value,
                            it.date.dayOfMonth
                        )
                    )
                    isClickDateAcquisition = false
                }
            )
        }
        LostItemTypeDropDown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 310.dp),
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
            isEssential = false,
            placeholder = stringResource(R.string.performance_find_lost_item_create_acquistion_time_placeholder),
            value = lostItemCreateUiState.foundTime ?: ""
        ) {
            CurtainCallTimePicker(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    showLostPropertyCreateViewModel.setFoundTime(
                        String.format(
                            context.getString(R.string.performance_find_lost_item_create_time_format),
                            it.hour,
                            it.minute
                        )
                    )
                    isClickTimeAcquisition = false
                }
            )
        }
        LostItemInfoTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 366.dp),
            title = stringResource(R.string.performance_find_lost_item_create_significant),
            placeholder = stringResource(R.string.performance_find_lost_item_create_significant_placeholder),
            isEssential = false,
            value = lostItemCreateUiState.particulars,
            onValueChange = {
                if (it.length <= 100) showLostPropertyCreateViewModel.setParticulars(it)
            },
            onFocused = {
                isClickLostItemType = false
                isClickDateAcquisition = false
                isClickTimeAcquisition = false
                isClickAttachmentFile = false
            },
            isSingleLine = false
        )
        LostItemAttachmentDropDown(
            showLostPropertyCreateViewModel = showLostPropertyCreateViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 532.dp),
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
    showLostPropertyCreateViewModel: ShowLostPropertyCreateViewModel,
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    onClickChange: (Boolean) -> Unit,
    isSelectAttachment: Boolean,
    onSelectChange: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val launcherPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            Timber.d("권한 동의")
        } else {
            Timber.d("권한 거부")
        }
    }

    val attachmentFile by showLostPropertyCreateViewModel.attachedImage.collectAsStateWithLifecycle()
    val takePhotoFromAlbum = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let { uri ->
            context.contentResolver.openInputStream(uri)?.let { inputStream ->
                showLostPropertyCreateViewModel.uploadImage(inputStream, uri.parseBitmap(context))
            }
            onSelectChange(true)
        }
    }
    val takePhotoFromCameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let { bitmap ->
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val inputStream = ByteArrayInputStream(bytes.toByteArray())
            showLostPropertyCreateViewModel.uploadImage(inputStream, bitmap)
            onSelectChange(true)
        }
    }

    Row(modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(76.dp)
        ) {
            Text(
                text = stringResource(R.string.performance_find_lost_item_create_attachment),
                color = Nero,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
        }
        Column(Modifier.fillMaxWidth()) {
            Box {
                if (isSelectAttachment) {
                    Box(Modifier.size(76.dp)) {
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
                                .clickable {
                                    showLostPropertyCreateViewModel.setImageId(Int.MIN_VALUE)
                                    showLostPropertyCreateViewModel.setAttachImage(null)
                                    onSelectChange(false)
                                },
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
                                            onClick = {
                                                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                                                    takePhotoFromAlbum.launch("image/*")
                                                } else {
                                                    launcherPermission.launch(permission)
                                                }
                                            },
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
    isEssential: Boolean = true,
    value: String,
    content: @Composable () -> Unit,
) {
    Column(modifier.zIndex(if (isClicked) 1f else 0f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.width(76.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = Nero,
                    fontSize = 16.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
                )
                if (isEssential.not()) {
                    Text(
                        text = "(선택)",
                        color = Roman_Silver,
                        fontSize = 12.dp.toSp(),
                        fontWeight = FontWeight.Medium,
                        fontFamily = spoqahansanseeo
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
    isEssential: Boolean = true,
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
        Column(
            modifier = Modifier
                .padding(top = if (isSingleLine) 0.dp else 12.dp)
                .width(76.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = Nero,
                fontSize = 16.dp.toSp(),
                fontWeight = FontWeight.Medium,
                fontFamily = spoqahansanseeo
            )
            if (isEssential.not()) {
                Text(
                    text = "(선택)",
                    color = Roman_Silver,
                    fontSize = 12.dp.toSp(),
                    fontWeight = FontWeight.Medium,
                    fontFamily = spoqahansanseeo
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
                    .height(152.dp)
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
