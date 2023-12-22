package com.cmc.curtaincall.feature.show.lostproperty.create

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.common.designsystem.component.show.lostproperty.LostPropertyType
import com.cmc.curtaincall.common.navigation.destination.DEFAULT_LOST_PROPERTY_ID
import com.cmc.curtaincall.core.base.BaseViewModel
import com.cmc.curtaincall.domain.repository.ImageRepository
import com.cmc.curtaincall.domain.repository.LostPropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ShowLostPropertyCreateViewModel @Inject constructor(
    private val lostItemRepository: LostPropertyRepository,
    private val imageRepository: ImageRepository
) : BaseViewModel<ShowLostPropertyCreateUiState, ShowLostPropertyCreateEvent, Nothing>(
    initialState = ShowLostPropertyCreateUiState()
) {
    private val _isCompleted = MutableSharedFlow<Boolean>()
    val isCompleted = _isCompleted.asSharedFlow()

    private val _lostPropertyId = MutableStateFlow(DEFAULT_LOST_PROPERTY_ID)
    val lostPropertyId = _lostPropertyId.asStateFlow()

    private var _attachedImage = MutableStateFlow<Bitmap?>(null)
    val attachedImage = _attachedImage.asStateFlow()

    override fun reduceState(currentState: ShowLostPropertyCreateUiState, event: ShowLostPropertyCreateEvent): ShowLostPropertyCreateUiState = when (event) {
        is ShowLostPropertyCreateEvent.SetTitle -> currentState.copy(title = event.title)
        is ShowLostPropertyCreateEvent.SetPropertyType -> currentState.copy(lostPropertyType = event.lostPropertyType)
        is ShowLostPropertyCreateEvent.SetFoundPlaceDetail -> currentState.copy(foundPlaceDetail = event.foundPlaceDetail)
        is ShowLostPropertyCreateEvent.SetFoundDate -> currentState.copy(foundDate = event.foundDate)
        is ShowLostPropertyCreateEvent.SetFoundTime -> currentState.copy(foundTime = event.foundTime)
        is ShowLostPropertyCreateEvent.SetParticulars -> currentState.copy(particulars = event.particulars)
        is ShowLostPropertyCreateEvent.SetImageId -> currentState.copy(imageId = event.imageId)
    }

    fun setTitle(title: String) {
        sendAction(ShowLostPropertyCreateEvent.SetTitle(title = title))
    }

    fun setPropertyType(lostPropertyType: LostPropertyType) {
        sendAction(ShowLostPropertyCreateEvent.SetPropertyType(lostPropertyType = lostPropertyType))
    }

    fun setFoundPlaceDetail(foundPlaceDetail: String) {
        sendAction(ShowLostPropertyCreateEvent.SetFoundPlaceDetail(foundPlaceDetail = foundPlaceDetail))
    }

    fun setFoundDate(foundDate: String) {
        sendAction(ShowLostPropertyCreateEvent.SetFoundDate(foundDate = foundDate))
    }

    fun setFoundTime(foundTime: String) {
        sendAction(ShowLostPropertyCreateEvent.SetFoundTime(foundTime = foundTime))
    }

    fun setParticulars(particulars: String) {
        sendAction(ShowLostPropertyCreateEvent.SetParticulars(particulars = particulars))
    }

    fun setImageId(imageId: Int) {
        sendAction(ShowLostPropertyCreateEvent.SetImageId(imageId = imageId))
    }

    fun setAttachImage(bitmap: Bitmap?) {
        _attachedImage.value = bitmap
    }

    fun uploadImage(inputStream: InputStream, bitmap: Bitmap) {
        imageRepository.uploadGalleryImage(inputStream)
            .onEach {
                setImageId(it.id)
                setAttachImage(bitmap)
            }.launchIn(viewModelScope)
    }

    fun createLostItem(facilityId: String) {
        lostItemRepository.createLostProperty(
            title = uiState.value.title,
            type = uiState.value.lostPropertyType.code,
            facilityId = facilityId,
            foundPlaceDetail = uiState.value.foundPlaceDetail,
            foundDate = uiState.value.foundDate,
            foundTime = uiState.value.foundTime,
            particulars = uiState.value.particulars,
            imageId = uiState.value.imageId
        ).onEach {
            _lostPropertyId.value = it.id
        }.onCompletion {
            _isCompleted.emit(true)
        }.launchIn(viewModelScope)
    }

    fun updateLostItem(lostItemId: Int) {
        lostItemRepository.updateLostProperty(
            lostItemId = lostItemId,
            title = uiState.value.title,
            type = uiState.value.lostPropertyType.code,
            foundPlaceDetail = uiState.value.foundPlaceDetail,
            foundDate = uiState.value.foundDate,
            foundTime = uiState.value.foundTime,
            particulars = uiState.value.particulars,
            imageId = uiState.value.imageId
        ).onCompletion {
            _isCompleted.emit(true)
        }.launchIn(viewModelScope)
    }
}
