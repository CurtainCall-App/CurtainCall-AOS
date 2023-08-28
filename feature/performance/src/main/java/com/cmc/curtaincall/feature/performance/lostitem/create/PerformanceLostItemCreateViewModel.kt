package com.cmc.curtaincall.feature.performance.lostitem.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmc.curtaincall.common.utility.extensions.toChangeServerDate
import com.cmc.curtaincall.domain.repository.ImageRepository
import com.cmc.curtaincall.domain.repository.LostItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class PerformanceLostItemCreateViewModel @Inject constructor(
    private val lostItemRepository: LostItemRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _lostCreateInfo = MutableStateFlow<PerformanceLostItemCreateUiState>(
        PerformanceLostItemCreateUiState()
    )
    val lostCreateInfo = _lostCreateInfo.asStateFlow()

    private val _completeEffect = MutableSharedFlow<Boolean>()
    val completeEffect = _completeEffect.asSharedFlow()

    fun setTitle(title: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            title = title
        )
    }

    fun setItemType(type: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            type = type
        )
    }

    fun setFoundPlaceDetail(foundPlaceDetail: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundPlaceDetail = foundPlaceDetail
        )
    }

    fun setFoundDate(foundDate: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundDate = foundDate.toChangeServerDate()
        )
    }

    fun setFoundTime(foundTime: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            foundTime = foundTime
        )
    }

    fun setParticulars(particulars: String) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            particulars = particulars
        )
    }

    fun setImageId(imageId: Int) {
        _lostCreateInfo.value = _lostCreateInfo.value.copy(
            imageId = imageId
        )
    }

    fun uploadImage(inputStream: InputStream) {
        imageRepository.uploadGalleryImage(inputStream)
            .onEach { setImageId(it.id) }
            .launchIn(viewModelScope)
    }

    fun createLostItem(facilityId: String) {
        lostItemRepository.createLostItem(
            title = lostCreateInfo.value.title,
            type = lostCreateInfo.value.type,
            facilityId = facilityId,
            foundPlaceDetail = lostCreateInfo.value.foundPlaceDetail,
            foundDate = lostCreateInfo.value.foundDate,
            foundTime = lostCreateInfo.value.foundTime,
            particulars = lostCreateInfo.value.particulars,
            imageId = lostCreateInfo.value.imageId
        ).onEach { _completeEffect.emit(true) }
            .launchIn(viewModelScope)
    }
}
